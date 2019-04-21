package ru.project.cscm.ws_base.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import ru.project.cscm.ws_base.core.config.WebSecurityConfig;

public class LogoutFilter extends GenericFilterBean {

    private static final String LOGOUT_URI = "/logout";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (isLogoutRequired(httpRequest)) {

            httpResponse.addCookie(this.createCookie(""));
            SecurityContextHolder.getContext().setAuthentication(null);
        } else {

            chain.doFilter(httpRequest, response);
        }

    }

    private boolean isLogoutRequired(final HttpServletRequest request) {

        return request.getRequestURI().endsWith(LOGOUT_URI);
    }

    private Cookie createCookie(final String token) {

        final Cookie cookie = new Cookie(WebSecurityConfig.AUTH_COOKIE_NAME, token);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        return cookie;
    }
}
