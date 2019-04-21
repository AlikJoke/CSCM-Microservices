package ru.project.cscm.integration.scheduling.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import ru.project.cscm.integration.dao.StatisticsDao;
import ru.project.cscm.integration.model.Statistics;
import ru.project.cscm.integration.model.enums.StatisticsType;
import ru.project.cscm.integration.scheduling.StatisticsUploader;

import com.google.common.collect.Lists;

@Component
public class AtmStatisticsUploader implements StatisticsUploader {

    private static final Logger logger = LoggerFactory.getLogger(AtmStatisticsUploader.class);

    @Autowired
    private RestTemplate restTemplate;

    private final Environment env;

    @Autowired
    private StatisticsDao statsDao;

    private final String uploadUrl;
	
	@Autowired
	private AtmStatisticsUploader(final Environment env) {
		this.env = env;
		this.uploadUrl = env.getRequiredProperty("cscm.gateway.url") + "/monitoring-service/integration/atm/stats";
	}

    private final JacksonJsonParser parser = new JacksonJsonParser();

    private final Lock lock = new ReentrantLock();

    @Transactional
    @Scheduled(initialDelay = 10_000, fixedRate = 20_000)
    @Override
    public void upload() {

        if (!this.lock.tryLock()) {
            logger.debug("Another thread already execute uploading");

            return;
        }

        try {

            this.uploadIfPossible();
        } finally {
            this.lock.unlock();
        }
    }

    private void uploadIfPossible() {

        if (this.statsDao.count() == 0 || !this.isUploadPossible()) {

            logger.debug("Uploading is not allowed: maybe CSCM is unreachable or statistics to upload is empty, try next time");
            return;
        }

        this.executeUpload();
    }

    private void executeUpload() {

        final HttpEntity<MultiValueMap<String, FileSystemResource>> entity = new HttpEntity<>(createMultipartBody(), createHeaders());

        try {
            final ResponseEntity<String> uploadingResult = this.restTemplate
                    .exchange(this.uploadUrl, HttpMethod.POST, entity, String.class);

            this.processUploadingResult(uploadingResult);
        } finally {
            entity.getBody().entrySet().stream().flatMap(e -> e.getValue().stream()).forEach(f -> f.getFile().delete());
        }
    }

    private void processUploadingResult(final ResponseEntity<String> uploadingResult) {

        if (uploadingResult.getStatusCode() != HttpStatus.CREATED) {

            logger.error("Failed to load statistics to CSCM: response code = {}", uploadingResult.getStatusCodeValue());
        } else {

            final List<Object> processedStats = this.parser.parseList(uploadingResult.getBody());

            processedStats.stream().map(id -> Integer.valueOf(id.toString())).forEach(id -> this.statsDao.deleteById(id));

            logger.debug("Statistics successfully loaded to CSCM");
        }
    }

    private boolean isUploadPossible() {

        Set<HttpMethod> allowedMethods = Collections.emptySet();
        try {

            if (this.uploadUrl != null) {
                allowedMethods = this.restTemplate.optionsForAllow(this.uploadUrl);
            }
        } catch (ResourceAccessException ex) {
            logger.debug("Cscm application is unreachable", ex);
        }

        return allowedMethods.contains(HttpMethod.POST);
    }

    private MultiValueMap<String, FileSystemResource> createMultipartBody() {

        final List<Statistics> stats = Lists.newArrayList(this.statsDao.findAllByType(StatisticsType.MONITORING_STATISTICS));

        final MultiValueMap<String, FileSystemResource> bodyMap = new LinkedMultiValueMap<>(stats.size());
        stats.forEach(s -> {

            final FileSystemResource file = new FileSystemResource(this.createFile(s.getStats()));
            bodyMap.add(String.valueOf(s.getId()), file);
        });

        return bodyMap;
    }

    private File createFile(final byte[] bytes) {

        final File tempFile = new File(UUID.randomUUID().toString());
        try (final FileOutputStream fileOuputStream = new FileOutputStream(tempFile)) {
            fileOuputStream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            tempFile.deleteOnExit();
        }

        return tempFile;
    }

    private HttpHeaders createHeaders() {

        final HttpHeaders headers = new HttpHeaders();
        headers.add(
                "Authorization",
                generateAuthHeader(this.env.getRequiredProperty("cscm.credentials.user"),
                        this.env.getRequiredProperty("cscm.credentials.password")));

        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        return headers;
    }

    private String generateAuthHeader(String login, String password) {

        try {
            String encoding = new String(Base64.encodeBase64((login + ":" + password).getBytes("UTF-8")));
            return "Basic " + encoding;
        } catch (Exception e) {
            logger.error("Exception while generate auth header", e);

            throw new RuntimeException("Exception while generate auth header");
        }
    }
}
