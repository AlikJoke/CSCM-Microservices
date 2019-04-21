package ru.project.cscm.ws.core;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

import ru.project.cscm.dao.monitoring.InstituteService;
import ru.project.cscm.dto.items.common.Institute;
import ru.project.cscm.ws.core.resources.InstituteResource;
import ru.project.cscm.ws_base.core.ControllerWithExceptionHandler;
import ru.project.cscm.ws_base.utils.RestUtils;

@RestController
@RequestMapping(InstituteController.BASE_PATH)
public class InstituteController extends ControllerWithExceptionHandler {

    static final String BASE_PATH = "/base-service/institutes";

    private static final Function<InstituteResource, Institute> convert = i -> new Institute(i.getId(), i.getDescription());

    @Autowired
    private InstituteService dao;

    @Override
    @RequestMapping(value = { "", "/{id}" }, method = RequestMethod.OPTIONS)
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
        super.doOptions(request, response);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InstituteResource read(@PathVariable("id") String id) {
        return new InstituteResource(RestUtils.checkNotNull(dao.getInstituteById(id)));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InstituteResource> readAll() {
        return dao.getAllInstitutes().stream().map(i -> new InstituteResource(i)).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody InstituteResource resource) {
        this.dao.save(convert.apply(resource));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public InstituteResource update(@RequestBody InstituteResource resource) {
        this.dao.save(convert.apply(resource));

        return resource;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.dao.deleteInstituteById(id);
    }
}
