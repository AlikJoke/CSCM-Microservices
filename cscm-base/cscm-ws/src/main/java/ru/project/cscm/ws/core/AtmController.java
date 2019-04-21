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

import ru.project.cscm.dao.monitoring.AtmService;
import ru.project.cscm.dto.items.common.Atm;
import ru.project.cscm.dto.items.common.Currency;
import ru.project.cscm.ws.core.resources.AtmGroupResource;
import ru.project.cscm.ws.core.resources.AtmResource;
import ru.project.cscm.ws.core.resources.CurrencyResource;
import ru.project.cscm.ws.core.resources.FilterResource;
import ru.project.cscm.ws_base.core.ControllerWithExceptionHandler;
import ru.project.cscm.ws_base.utils.RestUtils;

@RestController
@RequestMapping(AtmController.BASE_PATH)
public class AtmController extends ControllerWithExceptionHandler {

    static final String BASE_PATH = "/base-service/atms";

    @Autowired
    private AtmService dao;

    private static final Function<CurrencyResource, Currency> convertCurrency = i -> new Currency(i.getId(), i.getCurrencyCode(),
            i.getCurrency());

    private static final Function<AtmResource, Atm> convertAtm = i -> {
        final Atm atm = new Atm(i.getId(), i.getName(), i.getStreet(), i.getCity(), i.getState(), i.getInstituteId(), i.getExtAtmId(),
                i.getModel());
        if (i.getMainCurrency() != null) {
            atm.setMainCurrency(convertCurrency.apply(i.getMainCurrency()));
        }

        if (i.getSecondaryCurrency() != null) {
            atm.setSecondaryCurrency(convertCurrency.apply(i.getSecondaryCurrency()));
        }

        if (i.getSecondary2Currency() != null) {
            atm.setSecondaryCurrency2(convertCurrency.apply(i.getSecondary2Currency()));
        }

        if (i.getSecondary3Currency() != null) {
            atm.setSecondaryCurrency3(convertCurrency.apply(i.getSecondary3Currency()));
        }

        return atm;
    };

    @Override
    @RequestMapping(value = { "", "/{id}" }, method = RequestMethod.OPTIONS)
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
        super.doOptions(request, response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AtmResource> readAll() {
        final List<AtmResource> result = dao.getAtms().stream().map(c -> new AtmResource(c)).collect(Collectors.toList());

        result.forEach(a -> a.getAtmGroups().addAll(
                dao.getAtmGroupsById(a.getId()).stream().map(g -> new AtmGroupResource(g)).collect(Collectors.toList())));

        return result;
    }

    @GetMapping("/withoutGroups")
    @ResponseStatus(HttpStatus.OK)
    public List<AtmResource> readWithoutGroups() {
        return dao.getAtmsNotInGroup().stream().map(c -> new AtmResource(c)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AtmResource read(@PathVariable("id") Integer id) {
        final AtmResource result = new AtmResource(RestUtils.checkNotNull(dao.getAtmById(id)));
        result.getAtmGroups().addAll(
                dao.getAtmGroupsById(result.getId()).stream().map(g -> new AtmGroupResource(g)).collect(Collectors.toList()));

        return result;
    }

    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<AtmResource> search(@RequestBody FilterResource filter) {
        final List<AtmResource> result = dao.getAtmsByFilter(filter.getGroupsIds(), filter.getAtmsIds()).stream()
                .map(c -> new AtmResource(c)).collect(Collectors.toList());

        result.forEach(a -> a.getAtmGroups().addAll(
                dao.getAtmGroupsById(a.getId()).stream().map(g -> new AtmGroupResource(g)).collect(Collectors.toList())));

        return result;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AtmResource resource) {
        this.dao.save(convertAtm.apply(resource));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public AtmResource update(@RequestBody AtmResource resource) {
        this.dao.save(convertAtm.apply(resource));

        return resource;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        this.dao.deleteAtm(id);
    }

}