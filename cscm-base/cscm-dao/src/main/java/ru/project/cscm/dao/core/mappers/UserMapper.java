package ru.project.cscm.dao.core.mappers;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import ru.project.cscm.dao.core.Mapper;
import ru.project.cscm.dao.core.handlers.DoubleToBooleanTypeHandler;
import ru.project.cscm.dao.core.handlers.FormatStringTypeHandler;
import ru.project.cscm.dto.items.user.UserItem;

/**
 * Маппер для работы с объектами пользователей системы.
 * 
 * @author Alimurad Ramazanov
 *
 */
public interface UserMapper extends Mapper {

	@ConstructorArgs({
			@Arg(column = "login", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "password_hash", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "locking", javaType = Boolean.class, typeHandler = DoubleToBooleanTypeHandler.class) })
	@Select("select login, password_hash, locking from T_CM_USER where login = #{login}")
	@Options(useCache = true)
	@NotNull
	UserItem getUserByLogin(@NotNull @NotEmpty @Param("login") String login);

	@Select("select id from T_CM_USER where login = #{login}")
	@Options(useCache = true)
	@ResultType(Integer.class)
	Integer getUserIdByLogin(@NotNull @NotEmpty @Param("login") String login);

}
