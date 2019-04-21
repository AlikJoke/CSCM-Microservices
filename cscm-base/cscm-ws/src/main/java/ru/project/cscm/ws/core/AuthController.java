package ru.project.cscm.ws.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.project.cscm.ws.core.resources.ServiceLink;
import ru.project.cscm.ws_base.core.ControllerWithExceptionHandler;

@RestController
@RequestMapping(AuthController.PATH)
public class AuthController extends ControllerWithExceptionHandler {

    static final String PATH = "/base-service";

    @Autowired
    private ServiceRegistry registry;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceLink> login() {
        return this.registry.getServices();
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout() {

    }
}
