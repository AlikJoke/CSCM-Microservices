package ru.project.cscm.integration.ws.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.IGNORED_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String INTEGRATION_ROLE = "INTEGRATION_USER";

    @Autowired
    private Environment env;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/login", "/login/**").permitAll().and().httpBasic().and().logout()
                .logoutUrl("/logout").deleteCookies("JSESSIONID", "remember-me").logoutSuccessUrl("/").permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(
                        User.builder().username(this.env.getRequiredProperty("cscm.integration.credentials.user"))
                                .password(passwordEncoder.encode(this.env.getRequiredProperty("cscm.integration.credentials.password")))
                                .accountExpired(false).accountLocked(false).disabled(false).authorities(INTEGRATION_ROLE))
                .passwordEncoder(passwordEncoder);
    }
}