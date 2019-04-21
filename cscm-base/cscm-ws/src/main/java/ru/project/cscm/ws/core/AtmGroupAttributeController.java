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

import ru.project.cscm.dao.monitoring.AttributesService;
import ru.project.cscm.dto.items.common.AtmGroupAttribute;
import ru.project.cscm.ws.core.resources.AtmGroupAttributeResource;
import ru.project.cscm.ws_base.core.ControllerWithExceptionHandler;

@RestController
@RequestMapping(AtmGroupAttributeController.BASE_PATH)
public class AtmGroupAttributeController extends ControllerWithExceptionHandler {

    static final String BASE_PATH = "/base-service/atm/group/attributes";

    @Autowired
    private AttributesService dao;

    private static final Function<AtmGroupAttributeResource, AtmGroupAttribute> convert = i -> new AtmGroupAttribute(i.getAttrId(),
            i.getGroupId(), i.isUsed(), i.getValue());

    @Override
    @RequestMapping(value = { "", "/{id}" }, method = RequestMethod.OPTIONS)
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
        super.doOptions(request, response);
    }

    @GetMapping("/{groupId}")
    @ResponseStatus(HttpStatus.OK)
    public List<AtmGroupAttributeResource> read(@PathVariable("groupId") Integer id) {
        return dao.getGroupAttributes(id).stream().map(a -> new AtmGroupAttributeResource(a)).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AtmGroupAttributeResource create(@RequestBody AtmGroupAttributeResource resource) {
        this.dao.save(convert.apply(resource));

        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public AtmGroupAttributeResource update(@RequestBody AtmGroupAttributeResource resource) {
        this.dao.save(convert.apply(resource));

        return resource;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody AtmGroupAttributeResource resource) {
        this.dao.deleteAtmGroupAttributes(convert.apply(resource));
    }
}
