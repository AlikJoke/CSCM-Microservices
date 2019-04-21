package ru.project.cscm.dao.core;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.executor.BatchResult;
import org.mybatis.caches.ehcache.EhcacheCache;

/**
 * Интерфейс-маркер, наследовать который должен каждый интерфейс, содержащий
 * маппинг аннотациями MyBatis. Необходимость обусловлена тем, что в противном
 * случае результаты работы маппера не будут кэшироваться.
 * 
 * @author Alimurad Ramazanov
 *
 */
@CacheNamespace(implementation = EhcacheCache.class)
public interface Mapper {

	/**
	 * Метод для синхронизации пакетных запросов с базой данных.
	 * <p>
	 * 
	 * @see BatchResult
	 * @see {@link SessionHolder}
	 * @return результаты batch-запроса.
	 */
	@Flush
	List<BatchResult> flush();
}
