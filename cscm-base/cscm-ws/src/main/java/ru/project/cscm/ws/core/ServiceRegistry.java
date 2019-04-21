package ru.project.cscm.ws.core;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ru.project.cscm.ws.core.resources.ServiceLink;

@Component
public class ServiceRegistry {

    private static final String BASE_SERVICE = "cscm-base";
    private static final String AUTH_SERVICE = "auth";
    private static final String MONITORING_SERVICE = "monitoring";
    
    private static final String SERVICE_POSTFIX = ".service.url";
    
    private final List<ServiceLink> links;
    
    @Autowired
    private ServiceRegistry(final Environment env) {
        this.links = Arrays.asList(
                new ServiceLink(BASE_SERVICE, env.getProperty(BASE_SERVICE + SERVICE_POSTFIX)),
                new ServiceLink(MONITORING_SERVICE, env.getProperty(BASE_SERVICE + MONITORING_SERVICE)),
                new ServiceLink(AUTH_SERVICE, env.getProperty(BASE_SERVICE + AUTH_SERVICE)));
    }
    
    public List<ServiceLink> getServices() {
        
        return this.links;
    }
}
