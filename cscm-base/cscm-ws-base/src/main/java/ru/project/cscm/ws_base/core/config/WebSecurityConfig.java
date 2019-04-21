package ru.project.cscm.ws_base.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import ru.project.cscm.ws_base.filters.AuthServerFilter;
import ru.project.cscm.ws_base.filters.OptionsCorsFilter;
import ru.project.cscm.ws_base.utils.Sha512Encoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.IGNORED_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String AUTH_COOKIE_NAME = "CSCM-Auth";

    @Autowired
    private OptionsCorsFilter corsFilter;

    @Autowired
    private AuthServerFilter authFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(this.authFilter, BasicAuthenticationFilter.class).addFilterBefore(corsFilter, AuthServerFilter.class).csrf()
                .disable().authorizeRequests().antMatchers("/login", "/login/**").permitAll().and().httpBasic().and().logout()
                .logoutUrl("/logout").deleteCookies("JSESSIONID", "remember-me", AUTH_COOKIE_NAME).logoutSuccessUrl("/").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Sha512Encoder();
    }
}