package ru.project.cscm.auth.core.impl;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.project.cscm.auth.core.CscmUser;
import ru.project.cscm.auth.core.UserCredentials;
import ru.project.cscm.auth.core.UserCredentialsApproveHandler;
import ru.project.cscm.auth.core.UserService;

@Component
public class UserCredentialsApproveHandlerImpl implements UserCredentialsApproveHandler {

    @Autowired
    private UserService userService;

    @Override
    public ApproveResult approve(@NotNull final UserCredentials credentials) {

        final CscmUser user = userService.findById(credentials.getUsername()).orElse(null);
        final boolean isApproved;
        final String reason;

        if (user == null) {
            isApproved = false;
            reason = String.format("User is not found with username '%s'", credentials.getUsername());
        } else if (!user.isActive()) {
            isApproved = false;
            reason = String.format("Account is locked for user with username '%s'", credentials.getUsername());
        } else if (!user.getPassword().equals(credentials.getPassword())) {
            isApproved = false;
            reason = String.format("Incorrent password for user with username '%s'", credentials.getUsername());
        } else {
            isApproved = true;
            reason = null;
        }

        return new ApproveResult() {

            @Override
            public boolean isApproved() {
                return isApproved;
            }

            @Override
            public String getReason() {
                return reason;
            }

        };
    }

}
