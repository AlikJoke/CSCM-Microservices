package ru.project.cscm.auth.rest.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import ru.project.cscm.auth.core.UserService;
import ru.project.cscm.auth.rest.resources.UserResource;

@RestController
public class UserController extends ControllerWithExceptionHandler {

    private static final String PATH = "/auth-service/user/management";

    @Autowired
    private UserService userService;

    @PostMapping(PATH)
    @ResponseStatus(HttpStatus.CREATED)
    public UserResource create(@Validated @RequestBody final UserResource resource) {

        if (this.userService.findById(resource.getUsername()).isPresent()) {

            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User already exists");
        }

        return new UserResource(this.userService.save(UserResource.convertToUser.apply(resource)));
    }

    @PutMapping(PATH + "/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserResource update(@Validated @RequestBody final UserResource resource, HttpServletResponse response) {

        if (!this.userService.findById(resource.getUsername()).isPresent()) {

            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User not exists");
        }

        return new UserResource(this.userService.save(UserResource.convertToUser.apply(resource)));
    }

    @GetMapping(PATH + "/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserResource read(@PathVariable("username") @NotEmpty final String username) {

        return new UserResource(this.userService.findById(username).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND)));
    }

    @RequestMapping(value = PATH + "/{username}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("username") final String username) {

        if (!this.userService.findById(username).isPresent()) {

            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User not exists");
        }

        this.userService.deleteById(username);
    }

    @RequestMapping(value = PATH, method = RequestMethod.OPTIONS)
    @ResponseStatus(HttpStatus.OK)
    public void doOptions2(final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("Allow", "GET, POST, DELETE, PUT, OPTIONS");
        if (request.getHeader("Origin") != null) {
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
        }
    }
}
