package ru.project.cscm.dao.core;

import org.apache.ibatis.session.SqlSession;

public interface ThreadContext {

	SqlSession getSession();
	
	SqlSession getROSession();

	SqlSession getNewSession();
	
	SqlSession getNewBatchSession();

	SqlSession getBatchSession();
	
	<T extends Mapper> T getMapper(Class<T> mapperClass, boolean readOnly);
	
	void destroy();
}
