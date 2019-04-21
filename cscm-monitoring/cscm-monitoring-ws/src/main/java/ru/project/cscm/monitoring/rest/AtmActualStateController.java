package ru.project.cscm.monitoring.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.project.cscm.monitoring.dao.core.AtmActualStateService;
import ru.project.cscm.monitoring.integration.model.MonitoringFilter;
import ru.project.cscm.monitoring.model.Atm;
import ru.project.cscm.ws_base.core.ControllerWithExceptionHandler;

@RestController
@RequestMapping(AtmActualStateController.BASE_PATH)
public class AtmActualStateController extends ControllerWithExceptionHandler {

    static final String BASE_PATH = "/monitoring-service/state";

    @Autowired
    private AtmActualStateService dao;

    @Override
    @RequestMapping(value = "", method = RequestMethod.OPTIONS)
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
        super.doOptions(request, response);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Atm> readByFilter(@RequestBody MonitoringFilter filter) {
        return dao.findActualStateByFilter(filter);
    }
}
