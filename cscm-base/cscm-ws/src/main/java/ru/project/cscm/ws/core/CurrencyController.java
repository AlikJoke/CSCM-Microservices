package ru.project.cscm.ws.core;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.project.cscm.dao.monitoring.AtmService;
import ru.project.cscm.ws.core.resources.CurrencyResource;
import ru.project.cscm.ws_base.core.ControllerWithExceptionHandler;

@RestController
@RequestMapping(CurrencyController.BASE_PATH)
public class CurrencyController extends ControllerWithExceptionHandler {

    static final String BASE_PATH = "/base-service/currencies";

    @Autowired
    private AtmService dao;

    @Override
    @RequestMapping(value = { "", "/{id}" }, method = RequestMethod.OPTIONS)
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
        super.doOptions(request, response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CurrencyResource> readAll() {
        return dao.getCurrencies().stream().map(c -> new CurrencyResource(c)).collect(Collectors.toList());
    }
}
