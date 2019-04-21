package ru.project.cscm.dao.core;

import java.io.Closeable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class AbstractService<T extends Mapper> implements HasMapper<T>, Closeable {

    @Autowired
    private ApplicationContext context;

    @Autowired
    protected ThreadContext threadContext;

    @Override
    public void close() {
        threadContext.destroy();
    }

    @Override
    public T getMapper() {
        return this.threadContext.getMapper(getMapperClass(), false);
    }

    @Override
    public T getReadOnlyMapper() {
        return this.threadContext.getMapper(getMapperClass(), true);
    }

    protected abstract Class<T> getMapperClass();

}
