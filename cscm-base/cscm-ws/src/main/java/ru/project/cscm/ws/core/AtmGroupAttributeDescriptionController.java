package ru.project.cscm.ws.core;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.project.cscm.dao.monitoring.AttributesService;
import ru.project.cscm.dto.items.enums.AtmGroupType;
import ru.project.cscm.ws.core.resources.AtmGroupAttributeDescriptionResource;
import ru.project.cscm.ws_base.core.ControllerWithExceptionHandler;

@RestController
@RequestMapping(AtmGroupAttributeDescriptionController.BASE_PATH)
public class AtmGroupAttributeDescriptionController extends ControllerWithExceptionHandler {

    static final String BASE_PATH = "/base-service/atm/group/attribute/descriptions";

    @Autowired
    private AttributesService dao;

    @Override
    @RequestMapping(method = RequestMethod.OPTIONS)
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
        super.doOptions(request, response);
    }

    @GetMapping("/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<AtmGroupAttributeDescriptionResource> read(@PathVariable("type") AtmGroupType type) {
        return dao.getAtmGroupDescriptions(type).stream().map(a -> new AtmGroupAttributeDescriptionResource(a))
                .collect(Collectors.toList());
    }
}
