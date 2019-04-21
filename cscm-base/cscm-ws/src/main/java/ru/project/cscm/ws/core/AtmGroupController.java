package ru.project.cscm.ws.core;

import java.util.List;
import java.util.Optional;
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

import ru.project.cscm.dao.monitoring.AtmGroupService;
import ru.project.cscm.dao.monitoring.AtmService;
import ru.project.cscm.dto.items.common.Atm;
import ru.project.cscm.dto.items.common.AtmGroup;
import ru.project.cscm.dto.items.enums.AtmGroupType;
import ru.project.cscm.ws.core.resources.AtmGroupResource;
import ru.project.cscm.ws.core.resources.AtmResource;
import ru.project.cscm.ws_base.core.ControllerWithExceptionHandler;
import ru.project.cscm.ws_base.utils.RestUtils;

@RestController
@RequestMapping(AtmGroupController.BASE_PATH)
public class AtmGroupController extends ControllerWithExceptionHandler {

    static final String BASE_PATH = "/base-service/atm/groups";

    @Autowired
    private AtmGroupService atmGroupDao;

    @Autowired
    private AtmService atmDao;

    private static final Function<AtmGroupResource, AtmGroup> convert = i -> {

        final AtmGroup result = new AtmGroup(i.getId(), i.getName(), i.getDescription(), Optional.ofNullable(i.getType())
                .orElse(AtmGroupType.ATM_MODEL).getId());
        result.getAtms().addAll(
                i.getAtms()
                        .stream()
                        .map(a -> new Atm(a.getId(), a.getName(), a.getStreet(), a.getCity(), a.getState(), a.getInstituteId(), a
                                .getExtAtmId(), a.getModel())).collect(Collectors.toList()));

        return result;
    };

    @Override
    @RequestMapping(value = { "", "/{id}" }, method = RequestMethod.OPTIONS)
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
        super.doOptions(request, response);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AtmGroupResource read(@PathVariable("id") Integer id) {
        final AtmGroupResource result = new AtmGroupResource(RestUtils.checkNotNull(atmGroupDao.getAtmGroupById(id)));
        result.getAtmsNotInGroup().addAll(
                this.atmDao.getAtmsNotInAtmGroup(id).stream().map(a -> new AtmResource(a)).collect(Collectors.toList()));

        return result;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AtmGroupResource> readAll() {
        final List<AtmGroupResource> result = atmGroupDao.getAllAtmGroups().stream().map(g -> new AtmGroupResource(g))
                .collect(Collectors.toList());

        result.forEach(g -> g.getAtmsNotInGroup().addAll(
                this.atmDao.getAtmsNotInAtmGroup(g.getId()).stream().map(a -> new AtmResource(a)).collect(Collectors.toList())));

        return result;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AtmGroupResource resource) {
        this.atmGroupDao.save(convert.apply(resource));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public AtmGroupResource update(@RequestBody AtmGroupResource resource) {
        this.atmGroupDao.save(convert.apply(resource));

        return resource;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        this.atmGroupDao.deleteAtmGroupById(id);
    }
}
