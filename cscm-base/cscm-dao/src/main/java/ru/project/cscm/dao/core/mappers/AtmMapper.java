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
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import ru.project.cscm.dao.core.Mapper;
import ru.project.cscm.dao.core.handlers.FormatStringTypeHandler;
import ru.project.cscm.dao.core.mappers.builders.AtmBuilder;
import ru.project.cscm.dto.items.common.Atm;
import ru.project.cscm.dto.items.common.AtmGroup;
import ru.project.cscm.dto.items.common.Currency;

/**
 * Маппер для объектов банкоматов.
 * 
 * @author Alimurad Ramazanov
 *
 */
public interface AtmMapper extends Mapper {

	@ConstructorArgs({
			@Arg(column = "atm_id", javaType = Integer.class),
			@Arg(column = "name", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "street", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "city", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "state", javaType = String.class),
			@Arg(column = "inst_id", javaType = String.class),
			@Arg(column = "external_atm_id", javaType = String.class),
			@Arg(column = "model", javaType = String.class) })
	@Select("select atm_id, name, street, city, state, inst_id, external_atm_id, model from T_CM_ATM where atm_id = #{id}")
	@Options(useCache = true)
	Atm getAtmById(@NotEmpty @Param("id") Integer id);
	
	@ConstructorArgs({
        @Arg(column = "atm_id", javaType = Integer.class),
        @Arg(column = "name", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
        @Arg(column = "street", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
        @Arg(column = "city", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
        @Arg(column = "state", javaType = String.class),
        @Arg(column = "inst_id", javaType = String.class),
        @Arg(column = "external_atm_id", javaType = String.class),
        @Arg(column = "model", javaType = String.class) })
    @Select("select atm_id, name, street, city, state, inst_id, external_atm_id, model from T_CM_ATM where external_atm_id = #{id}")
    @Options(useCache = true)
    Atm getAtmByExtId(@NotEmpty @Param("id") String id);

	@ConstructorArgs({
			@Arg(column = "atm_id", javaType = Integer.class),
			@Arg(column = "name", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "street", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "city", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "state", javaType = String.class),
			@Arg(column = "inst_id", javaType = String.class),
			@Arg(column = "external_atm_id", javaType = String.class),
			@Arg(column = "model", javaType = String.class) })
	@SelectProvider(type = AtmBuilder.class, method = "builderQueryFilter")
	@Options(useCache = true)
	List<Atm> getAtmsByFilter(@Param("groupsIds") List<Integer> groupsIds,
			@Param("atmsIds") List<Integer> atmsIds);

	@ConstructorArgs({
			@Arg(column = "atm_id", javaType = Integer.class),
			@Arg(column = "name", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "street", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "city", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "state", javaType = String.class),
			@Arg(column = "inst_id", javaType = String.class),
			@Arg(column = "external_atm_id", javaType = String.class),
			@Arg(column = "model", javaType = String.class) })
	@Select("select atm_id, name, street, city, state, inst_id, external_atm_id, model from T_CM_ATM")
	@Options(useCache = true)
	List<Atm> getAtms();

	@ConstructorArgs({
			@Arg(column = "atm_id", javaType = Integer.class),
			@Arg(column = "name", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "street", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "city", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "state", javaType = String.class),
			@Arg(column = "inst_id", javaType = String.class),
			@Arg(column = "external_atm_id", javaType = String.class),
			@Arg(column = "model", javaType = String.class) })
	@Select("select a.atm_id, name, street, city, state, inst_id, external_atm_id, model from T_CM_ATM a join T_CM_ATM2ATM_GROUP a2g ON a2g.atm_id = a.atm_id WHERE a2g.atm_group_id = #{groupId}")
	@Options(useCache = true)
	List<Atm> getAtmsByAtmGroup(@NotNull @Param("groupId") Integer groupId);

	@ConstructorArgs({
			@Arg(column = "atm_id", javaType = Integer.class),
			@Arg(column = "name", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "street", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "city", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "state", javaType = String.class),
			@Arg(column = "inst_id", javaType = String.class),
			@Arg(column = "external_atm_id", javaType = String.class),
			@Arg(column = "model", javaType = String.class) })
	@Select("select a.atm_id, name, street, city, state, inst_id, external_atm_id, model from T_CM_ATM a join T_CM_ATM2ATM_GROUP a2g ON a2g.atm_id = a.atm_id WHERE a2g.atm_group_id <> #{groupId}")
	@Options(useCache = true)
	List<Atm> getAtmsNotInAtmGroup(@Param("groupId") @NotNull Integer groupId);

	@ConstructorArgs({
			@Arg(column = "atm_id", javaType = Integer.class),
			@Arg(column = "name", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "street", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "city", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "state", javaType = String.class),
			@Arg(column = "inst_id", javaType = String.class),
			@Arg(column = "external_atm_id", javaType = String.class),
			@Arg(column = "model", javaType = String.class) })
	@Select("select a.atm_id, name, street, city, state, inst_id, external_atm_id, model from T_CM_ATM a where not exists (select 1 from T_CM_ATM2ATM_GROUP a2g WHERE a2g.atm_id = a.atm_id)")
	@Options(useCache = true)
	List<Atm> getAtmsNotInGroup();

	@ConstructorArgs({ @Arg(column = "code_n3", javaType = Integer.class),
			@Arg(column = "code_a3", javaType = String.class),
			@Arg(column = "currency", javaType = String.class) })
	@Select("select code_n3, code_a3, currency from t_cm_curr join T_CM_ATM ON main_curr_code = code_n3 WHERE atm_id = #{atmId}")
	@Options(useCache = true)
	Currency getMainCurrencyByAtm(@Param("atmId") @NotNull Integer atmId);

	@ConstructorArgs({ @Arg(column = "code_n3", javaType = Integer.class),
			@Arg(column = "code_a3", javaType = String.class),
			@Arg(column = "currency", javaType = String.class) })
	@Select("select code_n3, code_a3, currency from t_cm_curr join T_CM_ATM ON secondary_curr_code = code_n3 WHERE atm_id = #{atmId}")
	@Options(useCache = true)
	Currency getSecCurrencyByAtm(@Param("atmId") @NotNull Integer atmId);

	@ConstructorArgs({ @Arg(column = "code_n3", javaType = Integer.class),
			@Arg(column = "code_a3", javaType = String.class),
			@Arg(column = "currency", javaType = String.class) })
	@Select("select code_n3, code_a3, currency from t_cm_curr join T_CM_ATM ON secondary2_curr_code = code_n3 WHERE atm_id = #{atmId}")
	@Options(useCache = true)
	Currency getSec2CurrencyByAtm(@Param("atmId") @NotNull Integer atmId);

	@ConstructorArgs({ @Arg(column = "code_n3", javaType = Integer.class),
			@Arg(column = "code_a3", javaType = String.class),
			@Arg(column = "currency", javaType = String.class) })
	@Select("select code_n3, code_a3, currency from t_cm_curr join T_CM_ATM ON secondary3_curr_code = code_n3 WHERE atm_id = #{atmId}")
	@Options(useCache = true)
	Currency getSec3CurrencyByAtm(@Param("atmId") @NotNull Integer atmId);

	@ConstructorArgs({ @Arg(column = "code_n3", javaType = Integer.class),
			@Arg(column = "code_a3", javaType = String.class),
			@Arg(column = "currency", javaType = String.class) })
	@Select("select code_n3, code_a3, currency from t_cm_curr")
	@Options(useCache = true)
	List<Currency> getCurrencies();

	@Insert("INSERT INTO T_CM_ATM (atm_id, name, street, city, state, inst_id, external_atm_id, main_curr_code, secondary_curr_code, secondary2_curr_code, secondary3_curr_code, model) "
			+ "VALUES (#{id}, #{name}, #{street}, #{city}, #{state}, #{inst_id}, #{external_atm_id}, #{main_curr_code}, #{secondary_curr_code}, #{secondary2_curr_code}, #{secondary3_curr_code}, #{model})")
	void createAtm(@Param("id") @NotNull Integer id,
			@Param("name") String name, @Param("street") String street,
			@Param("city") String city, @Param("state") String state,
			@Param("inst_id") String instId,
			@Param("external_atm_id") String extAtmId,
			@Param("main_curr_code") Integer mainCurreCode,
			@Param("secondary_curr_code") Integer secCurrCode,
			@Param("secondary2_curr_code") Integer sec2CurrCode,
			@Param("secondary3_curr_code") Integer sec3CurrCode,
			@Param("model") String model);

	@Update("UPDATE T_CM_ATM SET name = #{name}, street = #{street}, city = #{city}, state = #{state}, inst_id = #{inst_id}, external_atm_id = #{external_atm_id}, "
			+ "main_curr_code = #{main_curr_code}, secondary_curr_code = #{secondary_curr_code}, secondary2_curr_code = #{secondary2_curr_code}, secondary3_curr_code = #{secondary3_curr_code}, model = #{model} WHERE atm_id = #{id}")
	void updateAtm(@Param("id") @NotNull Integer id,
			@Param("name") String name, @Param("street") String street,
			@Param("city") String city, @Param("state") String state,
			@Param("inst_id") String instId,
			@Param("external_atm_id") String extAtmId,
			@Param("main_curr_code") Integer mainCurreCode,
			@Param("secondary_curr_code") Integer secCurrCode,
			@Param("secondary2_curr_code") Integer sec2CurrCode,
			@Param("secondary3_curr_code") Integer sec3CurrCode,
			@Param("model") String model);

	@Delete("DELETE FROM T_CM_ATM WHERE atm_id = #{id}")
	void deleteAtm(@Param("id") @NotNull Integer id);

	@ConstructorArgs({ @Arg(column = "id", javaType = Integer.class),
			@Arg(column = "name", javaType = String.class),
			@Arg(column = "description", javaType = String.class),
			@Arg(column = "type_id", javaType = Integer.class) })
	@Select("SELECT id, t_cm_atm_group.name, t_cm_atm_group.description, type_id FROM t_cm_atm_group join t_cm_atm2atm_group a2g on id = a2g.atm_id WHERE a2g.atm_id = #{id}")
	@Options(useCache = true, fetchSize = 1000)
	@ResultType(AtmGroup.class)
	List<AtmGroup> getAtmGroupsByAtmId(@Param("id") Integer id);
}
