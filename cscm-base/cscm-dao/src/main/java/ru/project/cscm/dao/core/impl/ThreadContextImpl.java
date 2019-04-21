package ru.project.cscm.dao.core.impl;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.project.cscm.dao.core.DaoSession;
import ru.project.cscm.dao.core.Mapper;
import ru.project.cscm.dao.core.ThreadContext;

@Component
public class ThreadContextImpl implements ThreadContext {

	@Autowired
	private DaoSession session;

	private final ThreadLocal<SqlSession> simpleSession = new ThreadLocal<>();
	private final ThreadLocal<SqlSession> simpleROSession = new ThreadLocal<>();
	private final ThreadLocal<Map<Class<? extends Mapper>, Mapper>> mappers = ThreadLocal.withInitial(HashMap::new);
	private final ThreadLocal<SqlSession> batchSession = new ThreadLocal<>();

	@Override
	public void destroy() {
		if (simpleSession.get() != null) {
			session.close(simpleSession.get());
		}

		if (simpleROSession.get() != null) {
			session.close(simpleROSession.get());
		}

		if (batchSession.get() != null) {
			session.close(batchSession.get());
		}
		
		simpleSession.remove();
		batchSession.remove();
		simpleROSession.remove();
		
		mappers.remove();
	}

	@Override
	public SqlSession getSession() {
		if (simpleSession.get() == null) {
			simpleSession.set(session.getSession());
		}

		return simpleSession.get();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Mapper> T getMapper(@NotNull Class<T> mapperClass, boolean readOnly) {
		return (T) this.mappers.get().computeIfAbsent(mapperClass, c -> (readOnly ? getROSession() : getSession()).getMapper(c));
	}

	@Override
	public SqlSession getNewSession() {
		simpleSession.set(closeAndGet(false));
		return getSession();
	}

	@Override
	public SqlSession getBatchSession() {
		if (batchSession.get() == null) {
			batchSession.set(session.getBatchSession());
		}

		return batchSession.get();
	}

	@Override
	public SqlSession getNewBatchSession() {
		batchSession.set(closeAndGet(true));
		return getBatchSession();
	}

	private SqlSession closeAndGet(boolean isBatch) {
		final SqlSession result;
		if (isBatch) {
			if (batchSession.get() != null) {
				session.close(batchSession.get());
			}

			result = session.getBatchSession();
		} else {
			if (simpleSession.get() != null) {
				session.close(simpleSession.get());
			}

			result = session.getSession();
		}

		return result;
	}

	@Override
	public SqlSession getROSession() {
		if (simpleROSession.get() == null) {
			simpleROSession.set(session.getROSession());
		}

		return simpleROSession.get();
	}
}
