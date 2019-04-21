package ru.project.cscm.dao.core.mappers;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import ru.project.cscm.dao.core.Mapper;
import ru.project.cscm.dao.core.handlers.FormatStringTypeHandler;
import ru.project.cscm.dto.items.common.Institute;

/**
 * Маппер для объектов институтов.
 * 
 * @author Alimurad Ramazanov
 *
 */
public interface InstituteMapper extends Mapper {

	@ConstructorArgs({
			@Arg(column = "ID", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "description", javaType = String.class, typeHandler = FormatStringTypeHandler.class) 
	})
	@Select("select id, description from T_CM_INST where id = #{id}")
	@Options(useCache = true)
	Institute getInstituteById(@NotEmpty @Param("id") String id);

	@ConstructorArgs({
			@Arg(column = "ID", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "description", javaType = String.class, typeHandler = FormatStringTypeHandler.class) 
	})
	@Select("select id, description from T_CM_INST")
	@Options(useCache = true)
	@NotNull
	List<Institute> getAllInstitutes();

	@Update("update T_CM_INST set description = #{description} WHERE id = #{id}")
	void updateInstitute(@NotEmpty @Param("id") String id,
			@Param("description") String description);

	@Delete("DELETE FROM T_CM_INST where ID = #{id}")
	void deleteInstituteById(@NotEmpty @Param("id") String id);

	@Delete("DELETE FROM T_CM_INST")
	void deleteAllInstitutes();
	
	@Insert("INSERT INTO T_CM_INST (id, description) VALUES (#{id}, #{description})")
	void createInstitute(@Param("id") @NotEmpty String id, @Param("description") @NotEmpty String description);
}
