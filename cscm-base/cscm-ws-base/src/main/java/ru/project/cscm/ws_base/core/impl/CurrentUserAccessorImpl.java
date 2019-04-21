package ru.project.cscm.ws_base.core.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ru.project.cscm.ws_base.core.CurrentUserAccessor;

@Component
public class CurrentUserAccessorImpl implements CurrentUserAccessor {

    @Override
    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
