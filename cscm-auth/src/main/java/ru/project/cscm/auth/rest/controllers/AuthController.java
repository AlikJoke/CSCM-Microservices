package ru.project.cscm.auth.rest.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.project.cscm.auth.core.UserCredentialsApproveHandler;
import ru.project.cscm.auth.core.UserService;
import ru.project.cscm.auth.rest.resources.ApproveCredentialsResource;
import ru.project.cscm.auth.rest.resources.ApproveResultResource;

@RestController
public class AuthController extends ControllerWithExceptionHandler {

    private static final String PATH_APPROVE = "/auth-service/credentials/approve";

    @Autowired
    private UserCredentialsApproveHandler approveHandler;

    @Autowired
    private UserService userService;

    @PostMapping(PATH_APPROVE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Validated
    public ApproveResultResource approve(@Validated @RequestBody final ApproveCredentialsResource resource) {
        return new ApproveResultResource(approveHandler.approve(ApproveCredentialsResource.convertToUserCredentials.apply(resource)));
    }

    @RequestMapping(value = PATH_APPROVE, method = RequestMethod.OPTIONS)
    @ResponseStatus(HttpStatus.OK)
    public void doOptions(final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("Allow", "POST, OPTIONS");
        if (request.getHeader("Origin") != null) {
            response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        }
    }
}
