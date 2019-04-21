package ru.project.cscm.ws.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public final class RestUtils {

    private RestUtils() {
        super();
    }

    public static <T extends Object> T checkNotNull(final T obj) {
        if (obj == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        
        return obj;
    }
}
