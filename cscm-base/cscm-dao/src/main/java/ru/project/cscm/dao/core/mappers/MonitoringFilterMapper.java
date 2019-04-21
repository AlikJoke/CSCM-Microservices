package ru.project.cscm.dao.core.mappers;

import java.util.List;

import javax.validation.constraints.NotEmpty;

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
import ru.project.cscm.dao.core.handlers.FormatStringTypeHandler;
import ru.project.cscm.dto.items.filters.MonitoringFilter;

/**
 * Маппер для объектов фильтров мониторинга.
 * 
 * @author Alimurad Ramazanov
 *
 */
public interface MonitoringFilterMapper extends Mapper {

	@ConstructorArgs({
			@Arg(column = "ID", javaType = Integer.class),
			@Arg(column = "name", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "description", javaType = String.class, typeHandler = FormatStringTypeHandler.class),
			@Arg(column = "atm_state", javaType = String.class),
			@Arg(column = "atm_problem", javaType = String.class),
			@Arg(column = "days_to_cash_end", javaType = Integer.class),
			@Arg(column = "cash_state", javaType = String.class),
			@Arg(column = "device_type", javaType = Integer.class),
			@Arg(column = "enc_state", javaType = String.class) })
	@Select("select st.id, st.name, st.description, cash_state, atm_state, atm_problem, days_to_cash_end, enc_state, device_type from T_CM_ATM_ACTUAL_STATE_FILTER st "
			+ "join T_CM_USER u on u.ID = st.user_id where login = #{login}")
	@Options(useCache = true)
	List<MonitoringFilter> getFiltersByUser(
			@NotEmpty @Param("login") String login);

	@Select("SELECT COALESCE(MAX(id), 0) FROM T_CM_ATM_ACTUAL_STATE_FILTER")
	@ResultType(Integer.class)
	Integer getLastFilterId();

	@Insert("INSERT INTO T_CM_ATM_ACTUAL_STATE_FILTER (id, user_id, name, description, cash_state, atm_state, atm_problem, days_to_cash_end, enc_state, device_type) VALUES "
			+ "(#{id}, #{userId}, #{name}, #{description}, #{cashState}, #{state}, #{malfunctions}, #{daysToCashEnd}, #{encashmentState}, #{deviceType})")
	void createFilter(@Param("userId") Integer usedId, @Param("id") Integer id,
			@Param("name") String name,
			@Param("description") String description,
			@Param("state") String states,
			@Param("malfunctions") String malfunctions,
			@Param("daysToCashEnd") Integer daysToCashEnd,
			@Param("cashState") String cashState,
			@Param("deviceType") Integer deviceType,
			@Param("encashmentState") String encashmentState);

	@Update("UPDATE T_CM_ATM_ACTUAL_STATE_FILTER set name = #{name}, description = #{description}, cash_state = #{cashState}, atm_state=#{state}, atm_problem = #{malfunctions}, "
			+ "days_to_cash_end = #{daysToCashEnd}, enc_state = #{encashmentState}, device_type = #{deviceType} WHERE id = #{id}")
	void updateFilter(@Param("id") Integer id, @Param("name") String name,
			@Param("description") String description,
			@Param("state") String states,
			@Param("malfunctions") String malfunctions,
			@Param("daysToCashEnd") Integer daysToCashEnd,
			@Param("cashState") String cashState,
			@Param("deviceType") Integer deviceType,
			@Param("encashmentState") String encashmentState);

	@Delete("DELETE FROM T_CM_ATM_ACTUAL_STATE_FILTER WHERE id = #{id}")
	void deleteFilter(@Param("id") Integer id);
}