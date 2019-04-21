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
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import ru.project.cscm.dao.core.Mapper;
import ru.project.cscm.dto.items.common.AtmGroup;

/**
 * Маппер для объектов групп банкоматов.
 * 
 * @author Alimurad Ramazanov
 *
 */
public interface AtmGroupMapper extends Mapper {

	@ConstructorArgs({ @Arg(column = "id", javaType = Integer.class),
			@Arg(column = "name", javaType = String.class),
			@Arg(column = "description", javaType = String.class),
			@Arg(column = "type_id", javaType = Integer.class) 
	})
	@Select("SELECT id, name, description, type_id FROM t_cm_atm_group WHERE id = #{id} ORDER BY name")
	@Options(useCache = true, fetchSize = 1000)
	@ResultType(AtmGroup.class)
	AtmGroup getAtmGroupById(@Param("id") Integer id);

	@ConstructorArgs({ @Arg(column = "id", javaType = Integer.class),
			@Arg(column = "name", javaType = String.class),
			@Arg(column = "description", javaType = String.class),
			@Arg(column = "type_id", javaType = Integer.class) 
	})
	@Select("SELECT id, name, description, type_id FROM t_cm_atm_group ORDER BY name")
	@Options(useCache = true, fetchSize = 1000)
	@ResultType(AtmGroup.class)
	List<AtmGroup> getAllAtmGroups();

	@Update("update T_CM_ATM_GROUP set description = #{description}, name = #{name}, type_id = #{typeId} WHERE id = #{id}")
	void updateAtmGroup(@NotEmpty @Param("id") Integer id,
			@Param("description") String description,
			@Param("name") String name, @Param("typeId") Integer typeId);

	@Delete("DELETE FROM t_cm_atm_group where id = #{id}")
	void deleteAtmGroupById(@NotNull @Param("id") Integer id);

	@Delete("DELETE FROM T_CM_ATM2ATM_GROUP where atm_group_id = #{id}")
	void deleteAtmsFromAtmGroup(@NotNull @Param("id") Integer id);

	@Delete("DELETE FROM T_CM_ATM2ATM_GROUP where atm_group_id = #{groupId} and atm_id = #{atmId}")
	void deleteAtmFromAtmGroup(@NotNull @Param("groupId") Integer groupId,
			@NotNull @Param("atmId") Integer atmId);

	@Insert("INSERT INTO T_CM_ATM2ATM_GROUP (atm_group_id, atm_id) VALUES (#{groupId}, #{atmId})")
	void addAtmToAtmGroup(@NotNull @Param("groupId") Integer groupId,
			@NotNull @Param("atmId") Integer atmId);

	@Insert("INSERT INTO T_CM_ATM_GROUP (id, name, type_id, description) VALUES (#{id}, #{name}, #{typeId}, #{description})")
	void createAtmGroup(@Param("id") @NotNull Integer id,
			@Param("description") String description,
			@Param("name") String name, @Param("typeId") Integer typeId);
	
	@Select("SELECT COALESCE(MAX(id), 0) FROM T_CM_ATM_GROUP")
	@ResultType(Integer.class)
	Integer getLastGroupId();
}