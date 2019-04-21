package ru.project.cscm.ws.core.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ru.project.cscm.dao.core.AbstractService;

@Component
public class ResourceClosingInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private List<AbstractService<?>> daoServices;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        this.daoServices.forEach(AbstractService::close);
    }

}