package ru.project.cscm.ws.core.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public final class ServiceLink {

    private final String baseUrl;
    private final String serviceName;

    public ServiceLink(String serviceName, String baseUrl) {
        super();
        this.baseUrl = baseUrl;
        this.serviceName = serviceName;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getServiceName() {
        return serviceName;
    }

}
