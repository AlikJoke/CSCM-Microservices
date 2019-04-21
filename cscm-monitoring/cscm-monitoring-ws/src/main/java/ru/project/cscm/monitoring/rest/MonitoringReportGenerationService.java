package ru.project.cscm.monitoring.rest;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.project.cscm.monitoring.dao.core.AtmActualStateService;
import ru.project.cscm.monitoring.integration.model.MonitoringFilter;
import ru.project.cscm.reports.ReportBeanCollection;
import ru.project.cscm.reports.ReportBuilder;
import ru.project.cscm.ws_base.core.ReportGenerationService;

@RestController
@RequestMapping(MonitoringReportGenerationService.PATH)
public class MonitoringReportGenerationService extends ReportGenerationService {

    static final String PATH = "/monitoring-service"  + ReportGenerationService.PATH;

    @Autowired
    private AtmActualStateService dao;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> generate(@RequestBody MonitoringFilter filter) throws IOException, JRException {

        final ReportBeanCollection<?> actualStateColl = creator.createCollection("actualState.jrxml",
                this.dao.findActualStateByFilter(filter));

        final ByteArrayResource result = reportBuilder.build(Arrays.asList(actualStateColl),
                ReportBuilder.JasperExportFormat.getXlsFormat());

        return createResponse(result, null);
    }

    @RequestMapping(value = PATH, method = RequestMethod.OPTIONS)
    @ResponseStatus(HttpStatus.OK)
    public void doOptions(final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("Allow", "GET, OPTIONS");
        if (request.getHeader("Origin") != null) {
            response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS");
        }
    }
}
