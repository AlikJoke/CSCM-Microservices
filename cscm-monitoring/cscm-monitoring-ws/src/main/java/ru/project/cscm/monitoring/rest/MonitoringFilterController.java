package ru.project.cscm.monitoring.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.project.cscm.monitoring.dao.core.FilterService;
import ru.project.cscm.monitoring.integration.model.MonitoringFilter;
import ru.project.cscm.monitoring.model.MonitoringFilterId;
import ru.project.cscm.ws_base.core.ControllerWithExceptionHandler;
import ru.project.cscm.ws_base.core.CurrentUserAccessor;

@RestController
@RequestMapping(MonitoringFilterController.BASE_PATH)
public class MonitoringFilterController extends ControllerWithExceptionHandler {

    static final String BASE_PATH = "/monitoring-service/filters";

    @Autowired
    private FilterService dao;

    @Autowired
    private CurrentUserAccessor userAccessor;

    @Override
    @RequestMapping(value = { "", "/{id}" }, method = RequestMethod.OPTIONS)
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
        super.doOptions(request, response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MonitoringFilter> readAll() {
        return dao.findAllByUser(this.userAccessor.getCurrentUser());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MonitoringFilter create(@RequestBody MonitoringFilter resource) {

        resource.setId(new MonitoringFilterId(this.userAccessor.getCurrentUser(), this.dao.findCountAllByUser(this.userAccessor
                .getCurrentUser()) + 1));

        return this.dao.save(resource);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public MonitoringFilter update(@RequestBody MonitoringFilter resource) {

        return this.dao.save(resource);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        this.dao.deleteById(new MonitoringFilterId(this.userAccessor.getCurrentUser(), id));
    }
}
