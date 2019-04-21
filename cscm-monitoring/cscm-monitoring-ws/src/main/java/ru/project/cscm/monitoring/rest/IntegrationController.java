package ru.project.cscm.monitoring.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ru.project.cscm.monitoring.dao.core.MonitoringIntegrationService;
import ru.project.cscm.monitoring.integration.model.AtmMonitoringStatistics;
import ru.project.cscm.monitoring.rest.utils.XmlDataConverter;
import ru.project.cscm.ws_base.core.ControllerWithExceptionHandler;

@RestController
@RequestMapping(IntegrationController.PATH)
public class IntegrationController extends ControllerWithExceptionHandler {

    static final String PATH = "/monitoring-service/integration/atm/stats";

    @Autowired
    private MonitoringIntegrationService integrationService;

    @Autowired
    private XmlDataConverter converter;

    @Override
    @RequestMapping(method = RequestMethod.OPTIONS)
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) {

        response.setHeader("Allow", "POST, OPTIONS");
        if (request.getHeader("Origin") != null) {
            response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Integer> loadStats(final HttpServletRequest request) {

        final Map<String, MultipartFile> files = this.getMultipartFilesFromRequest(request);
        final List<String> filesIdx = files.entrySet().stream().sorted(Comparator.comparingInt(e -> Integer.valueOf(e.getKey())))
                .map(e -> e.getKey()).collect(Collectors.toList());

        final List<Integer> processedIds = new ArrayList<>(filesIdx.size());
        for (final String fileId : filesIdx) {

            final AtmMonitoringStatistics stats = this.deserialize(files.get(fileId));
            if (!this.integrationService.load(stats)) {
                break;
            }

            processedIds.add(Integer.valueOf(fileId));
        }

        return processedIds;
    }

    private AtmMonitoringStatistics deserialize(final MultipartFile mpf) {

        return this.converter.convert(this.extractInputStream(mpf), AtmMonitoringStatistics.class);
    }

    private Map<String, MultipartFile> getMultipartFilesFromRequest(final HttpServletRequest request) {

        final MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;

        return mr.getFileMap();
    }

    private InputStream extractInputStream(final MultipartFile multipartFile) {

        try {
            return multipartFile.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
