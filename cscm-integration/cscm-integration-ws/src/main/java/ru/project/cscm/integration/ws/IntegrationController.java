package ru.project.cscm.integration.ws;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import ru.project.cscm.integration.dao.StatisticsDao;
import ru.project.cscm.integration.model.DeliveryReport;
import ru.project.cscm.integration.model.ObjectFactory;
import ru.project.cscm.integration.model.Statistics;
import ru.project.cscm.integration.model.enums.StatisticsType;
import ru.project.cscm.integration.validation.XmlValidator;

@RestController
@RequestMapping(IntegrationController.BASE_PATH)
public class IntegrationController extends ControllerBase {

    static final String BASE_PATH = "/integration-service";

    private static final String PATH_ATM_STATS = "/atm/statistics";
    private static final String PATH_MONITORING_STATS = "/monitoring/statistics";

    @Autowired
    private StatisticsDao statsDao;

    @Autowired
    private XmlValidator validator;

    @RequestMapping(value = { PATH_MONITORING_STATS, PATH_ATM_STATS }, method = RequestMethod.OPTIONS)
    public void doOptions(HttpServletRequest request, HttpServletResponse response) {

        response.setHeader("Allow", "POST, OPTIONS");
        if (request.getHeader("Origin") != null) {
            response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        }
    }

    @RequestMapping(value = { PATH_ATM_STATS, PATH_MONITORING_STATS }, method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE)
    public DeliveryReport loadStats(@RequestPart(value = "file", required = false) MultipartFile file, InputStream binaryData,
            HttpServletResponse response, HttpServletRequest request) throws IOException {

        if (file == null && binaryData == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        final byte[] bytesStat = file == null ? IOUtils.toByteArray(binaryData) : file.getBytes();

        return processRequest(bytesStat, response, getStatsType(request));
    }

    private StatisticsType getStatsType(final HttpServletRequest request) {

        if (request.getRequestURI().endsWith(PATH_MONITORING_STATS)) {
            return StatisticsType.MONITORING_STATISTICS;
        } else if (request.getRequestURI().endsWith(PATH_ATM_STATS)) {
            return StatisticsType.ATM_STATISTICS;
        }

        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    private DeliveryReport processRequest(final byte[] stats, final HttpServletResponse response, final StatisticsType type) {

        final ByteArrayInputStream is = new ByteArrayInputStream(stats);

        final XmlValidator.ValidationResult validationResult = this.validator.validate(is, type.getXsdPath());

        final DeliveryReport report = this.createReport(validationResult);

        if (validationResult.success()) {
            this.statsDao.save(new Statistics(type, stats));
        }

        this.handleResponseStatus(validationResult.success(), response);

        return report;
    }

    private void handleResponseStatus(final boolean success, final HttpServletResponse response) {

        if (success) {
            response.setStatus(HttpStatus.CREATED.value());
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    private DeliveryReport createReport(final XmlValidator.ValidationResult validationResult) {

        if (validationResult.success()) {

            return ObjectFactory.get().createSuccessDeliveryReport();
        } else {

            return ObjectFactory.get().createErrorDeliveryReport(validationResult.addInfo());
        }
    }
}
