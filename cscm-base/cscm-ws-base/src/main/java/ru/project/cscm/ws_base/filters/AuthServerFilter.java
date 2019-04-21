package ru.project.cscm.ws_base.filters;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;

import ru.project.cscm.ws_base.auth.ApproveCredentialsResource;
import ru.project.cscm.ws_base.auth.ApproveResultResource;
import ru.project.cscm.ws_base.core.config.WebSecurityConfig;

@Component
public class AuthServerFilter extends GenericFilterBean {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenStore tokenStore;

    private String credentialsApproveUrl;

    @Autowired
    private AuthServerFilter(@NotNull final Environment env) {
        this.credentialsApproveUrl = env.getRequiredProperty("cscm.gateway.url") + "/auth-service/credentials/approve";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (isAuthRequired(httpRequest)) {

            final String authHeader = httpRequest.getHeader("Authorization");
            if (StringUtils.isEmpty(authHeader)) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            final String[] parts = authHeader.split(" ");
            if (parts.length != 2) {
                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            if (!httpResponse.isCommitted() && !RequestMethod.OPTIONS.name().equalsIgnoreCase(httpRequest.getMethod())) {

                final String args = new String(Base64.getDecoder().decode(parts[1].getBytes("UTF-8")));

                final String[] authParams = args.split(":");
                final String encodedPassword = passwordEncoder.encode(authParams[1]);
                final ResponseEntity<ApproveResultResource> approveResult = restTemplate.postForEntity(this.credentialsApproveUrl,
                        new ApproveCredentialsResource(authParams[0], encodedPassword), ApproveResultResource.class);

                if (approveResult.getStatusCodeValue() != 202) {
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication service is unavailable");
                } else if (!approveResult.getBody().isApproved()) {
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, approveResult.getBody().getReason());
                } else {

                    final String token = Base64.getEncoder().encodeToString(
                            (UUID.randomUUID() + "|" + authParams[0]).getBytes(Charset.forName("UTF-8")));

                    this.authenticate(authParams[0], token);
                    this.tokenStore.addToken(token);

                    httpResponse.addCookie(this.createCookie(token));
                }
            }
        }

        if (httpResponse.getStatus() == HttpServletResponse.SC_OK) {
            chain.doFilter(request, response);
        }
    }

    private boolean isAuthRequired(final HttpServletRequest httpRequest) {

        final Cookie authCookie = Stream.of(Optional.ofNullable(httpRequest.getCookies()).orElse(new Cookie[0]))
                .filter(c -> WebSecurityConfig.AUTH_COOKIE_NAME.equals(c.getName())).findFirst().orElse(null);
        if (authCookie != null) {

            final String token = new String(Base64.getDecoder().decode(authCookie.getValue()));
            if (this.tokenStore.isTokenExists(token)) {

                this.authenticate(token.split("|")[1], token);

                return false;
            }
        }

        return true;
    }

    private void authenticate(final String username, final String token) {

        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, token, Collections.emptyList());

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private Cookie createCookie(final String token) {

        final Cookie cookie = new Cookie(WebSecurityConfig.AUTH_COOKIE_NAME, token);
        cookie.setMaxAge(10 * 60);
        cookie.setPath("/");

        return cookie;
    }
}
