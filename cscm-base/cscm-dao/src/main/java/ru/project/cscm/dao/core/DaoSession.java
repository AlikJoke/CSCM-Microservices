package ru.project.cscm.dao.core;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;

/**
 * Интерфейс, обеспечивающий доступ к основным методам для работы с сессией
 * MyBatis ORM.
 * 
 * @author Alimurad Ramazanov
 *
 */
public interface DaoSession {

	/**
	 * Получает сессию, добавляя ее в {@linkplain Configuration}. После вызова
	 * <b>обязательно</b> должен быть вызван метод
	 * {@linkplain ISessionHolder#close(SqlSession)}.
	 * <p>
	 * 
	 * @param type
	 *            - тип сессии.
	 * @return сессию, не может быть {@code null}.
	 */
	SqlSession getSession(ExecutorType... type);

	/**
	 * Получает сессию, добавляя ее в {@linkplain Configuration}. После вызова
	 * <b>обязательно</b> должен быть вызван метод
	 * {@linkplain ISessionHolder#close(SqlSession)}.
	 * <p>
	 * 
	 * @return сессию, не может быть {@code null}.
	 */
	SqlSession getROSession();

	/**
	 * Получает сессию для batch-запросов. После вызова <b>обязательно</b>
	 * должен быть вызван метод {@linkplain ISessionHolder#close(SqlSession)}.
	 * <p>
	 * 
	 * @see {ExecutorType#BATCH}
	 * 
	 * @return сессию, не может быть {@code null}.
	 */
	SqlSession getBatchSession();

	/**
	 * Закрывает сессию.
	 * <p>
	 * 
	 * @see SqlSession
	 * @param session
	 *            - сессия, не может быть {@code null}.
	 * @throws IllegalStateExcetion
	 */
	void close(SqlSession session);
}
