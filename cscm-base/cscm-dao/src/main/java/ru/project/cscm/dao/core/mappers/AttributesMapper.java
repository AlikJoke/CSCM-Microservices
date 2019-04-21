package ru.project.cscm.dao.core.mappers;

import java.util.List;

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
import ru.project.cscm.dao.core.handlers.IntegerToBooleanTypeHandler;
import ru.project.cscm.dto.items.common.AtmGroupAttribute;
import ru.project.cscm.dto.items.common.AtmGroupAttributeDescription;

/**
 * Маппер для объектов аттрибутов групп.
 * 
 * @author Alimurad Ramazanov
 *
 */
public interface AttributesMapper extends Mapper {

	@ConstructorArgs({ 
			@Arg(column = "attr_id", javaType = Integer.class),
			@Arg(column = "atm_group_id", javaType = Integer.class),
			@Arg(column = "attr_is_used", javaType = Boolean.class, typeHandler = IntegerToBooleanTypeHandler.class),
			@Arg(column = "value", javaType = String.class)
	})
	@Select("SELECT attr_id, atm_group_id, value, attr_is_used FROM t_cm_atm_group_attr WHERE attr_id = #{attrId} and atm_group_id = #{groupId}")
	@Options(useCache = true, fetchSize = 1000)
	@ResultType(AtmGroupAttribute.class)
	AtmGroupAttribute getAtmGroupAttributeById(@Param("attrId") Integer attrId, @Param("groupId") Integer groupId);

	@Insert("INSERT INTO t_cm_atm_group_attr (attr_id, atm_group_id, value, attr_is_used) VALUES(#{attrId}, #{groupId}, #{value}, #{isUsed})")
	void createAtmGroupAttribute(@Param("attrId") Integer attrId,
			@Param("groupId") Integer groupId, @Param("value") String value,
			@Param("isUsed") Integer isUsed);
	
	@Update("UPDATE t_cm_atm_group_attr set value = #{value}, attr_is_used = #{isUsed} where attr_id = #{attrId} and atm_group_id = #{groupId}")
	void updateAtmGroupAttribute(@Param("attrId") Integer attrId,
			@Param("groupId") Integer groupId, @Param("value") String value,
			@Param("isUsed") Integer isUsed);

	@ConstructorArgs({ 
		@Arg(column = "attr_id", javaType = Integer.class),
		@Arg(column = "atm_group_id", javaType = Integer.class),
		@Arg(column = "attr_is_used", javaType = Boolean.class, typeHandler = IntegerToBooleanTypeHandler.class),
		@Arg(column = "value", javaType = String.class)
	})
	@Select("SELECT attr_id, atm_group_id, value, attr_is_used FROM t_cm_atm_group_attr WHERE atm_group_id = #{id}")
	@Options(useCache = true, fetchSize = 1000)
	@ResultType(AtmGroupAttribute.class)
	List<AtmGroupAttribute> getAtmGroupAttributes(@Param("id") Integer groupId);
	
	@Delete("DELETE FROM t_cm_atm_group_attr WHERE atm_group_id = #{groupId} and attr_id = #{attrId}")
	void deleteAtmGroupAttributes(@Param("groupId") Integer groupId, @Param("attrId") Integer attrId);

	@ConstructorArgs({ 
		@Arg(column = "attr_id", javaType = Integer.class),
		@Arg(column = "descx", javaType = String.class),
		@Arg(column = "required", javaType = Boolean.class, typeHandler = IntegerToBooleanTypeHandler.class),
		@Arg(column = "group_type", javaType = Integer.class) 
	})
	@Select("SELECT attr_id, descx, required, group_type FROM t_cm_atm_group_attr_desc WHERE group_type = #{groupType}")
	@Options(useCache = true, fetchSize = 1000)
	@ResultType(AtmGroupAttributeDescription.class)
	List<AtmGroupAttributeDescription> getAtmGroupAttributesDescriptions(@Param("groupType") Integer groupType);
}
