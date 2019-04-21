package ru.project.cscm.dao.core.mappers;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import ru.project.cscm.dao.core.Mapper;
import ru.project.cscm.dao.core.handlers.EnumHandler;
import ru.project.cscm.dto.items.common.AtmGroup;
import ru.project.cscm.dto.items.enums.AtmGroupType;

/**
 * Маппер для мониторинга состояния групп банкоматов.
 * 
 * @author Alimurad A. Ramazanov
 *
 */
public interface GroupActualStateMapper extends Mapper {

	@ConstructorArgs({ @Arg(column = "DENOM_COUNT", javaType = String.class),
			@Arg(column = "CODE_A3", javaType = String.class) })
	@Select(" SELECT sum(epd.DENOM_VALUE*epd.DENOM_COUNT) as DENOM_COUNT, epd.DENOM_CURR , ci.CODE_A3 "
			+ "FROM T_CM_ENC_PLAN ep join T_CM_ENC_PLAN_DENOM epd on(ep.ENC_PLAN_ID = epd.ENC_PLAN_ID) "
			+ "join T_CM_CURR ci on (DENOM_CURR = ci.code_n3) WHERE "
			+ "trunc(ep.DATE_FORTHCOMING_ENCASHMENT) = #{date} AND ep.ATM_ID = #{atmId} AND ep.IS_APPROVED = 1 "
			+ "AND ep.APPROVE_ID > 0 AND ep.CONFIRM_ID > 0 GROUP BY epd.DENOM_CURR,ci.CODE_A3 "
			+ "ORDER BY DENOM_CURR")
	@Options(useCache = true, fetchSize = 1000)
	List<ImmutablePair<String, String>> getAtmEncPlanSums(@Param("date") java.sql.Date date, @Param("atmId") Integer atmId);

	@ConstructorArgs({ @Arg(column = "DENOM_COUNT", javaType = String.class),
			@Arg(column = "CODE_A3", javaType = String.class) })
	@Select(" SELECT sum(epd.DENOM_VALUE*epd.DENOM_COUNT) as DENOM_COUNT, epd.DENOM_CURR , ci.CODE_A3 "
			+ "FROM T_CM_ENC_PERIOD ep join T_CM_ENC_PERIOD_DENOM epd on(ep.ID = epd.ENC_PERIOD_ID) "
			+ "join T_CM_CURR ci on (DENOM_CURR = ci.code_n3) WHERE "
			+ "trunc(ep.DATE_FORTHCOMING_ENCASHMENT) >= #{dateFrom} AND trunc(ep.DATE_FORTHCOMING_ENCASHMENT) <= #{dateTo} "
			+ "AND ep.ATM_ID = #{atmId} GROUP BY epd.DENOM_CURR,ci.CODE_A3 ORDER BY DENOM_CURR")
	@Options(useCache = true, fetchSize = 1000)
	List<ImmutablePair<String, String>> getAtmEncPeriodSums(@Param("dateFrom") java.sql.Date dateFrom,
			@Param("dateTo") java.sql.Date dateTo, @Param("atmId") Integer atmId);

	@ConstructorArgs({
			@Arg(column = "CURR_REMAINING", javaType = String.class),
			@Arg(column = "CODE_A3", javaType = String.class) })
	@Select(" SELECT SUM(curr_remaining) as CURR_REMAINING, CURR_CODE, CODE_A3 FROM ( "
			+ "SELECT ccus.curr_remaining,ccus.curr_code , ci.CODE_A3 FROM t_cm_atm_actual_state aas "
			+ "JOIN t_cm_cashout_curr_stat ccus ON ( aas.atm_id = ccus.atm_id "
			+ "AND ccus.encashment_id = aas.cash_out_encashment_id AND ccus.stat_date = aas.cash_out_stat_date) "
			+ "JOIN t_cm_curr ci on (ccus.curr_code = ci.code_n3) WHERE aas.atm_id = #{atmId} union "
			+ "SELECT ccus.curr_remaining,ccus.curr_code , ci.CODE_A3 FROM t_cm_atm_actual_state aas "
			+ "JOIN t_cm_cashin_r_curr_stat ccus ON ( aas.atm_id = ccus.atm_id "
			+ "AND ccus.cash_in_encashment_id = aas.cash_in_encashment_id "
			+ "AND ccus.stat_date = aas.cash_out_stat_date) JOIN t_cm_curr ci on (ccus.curr_code = ci.code_n3) "
			+ "WHERE aas.atm_id = #{atmId} ) GROUP BY CURR_CODE, CODE_A3 ORDER BY curr_code")
	@Options(useCache = true, fetchSize = 1000)
	List<ImmutablePair<String, String>> getAtmRemainingSums(@Param("atmId") Integer atmId);

	@ConstructorArgs({ @Arg(column = "DENOM_COUNT", javaType = String.class),
			@Arg(column = "CODE_A3", javaType = String.class) })
	@Select(" SELECT sum(epd.DENOM_VALUE*epd.DENOM_COUNT) as DENOM_COUNT, ci.CODE_A3 "
			+ "FROM T_CM_ENC_CASHOUT_STAT ep "
			+ "join V_CM_ENC_CASHOUT_STAT_DETAILS epd on(ep.ENCASHMENT_ID = epd.ENCASHMENT_ID and epd.ACTION_TYPE = 1) "
			+ "join T_CM_CURR ci on (DENOM_CURR = ci.code_n3) WHERE trunc(ep.ENC_DATE) = #{date} "
			+ "AND ep.ATM_ID = #{atmId} AND epd.action_type = #{actionId} GROUP BY ci.CODE_A3 ORDER BY DENOM_COUNT DESC")
	@Options(useCache = true, fetchSize = 1000)
	List<ImmutablePair<String, String>> getAtmEncStatSums(@Param("date") java.sql.Date date,
			@Param("atmId") Integer atmId, @Param("actionId") Integer actionId);

	@ConstructorArgs({ @Arg(column = "DENOM_COUNT", javaType = String.class),
			@Arg(column = "CODE_A3", javaType = String.class) })
	@Select(" SELECT sum(epd.DENOM_VALUE*epd.DENOM_COUNT) as DENOM_COUNT, epd.DENOM_CURR , ci.CODE_A3 "
			+ "FROM T_CM_ENC_PLAN ep join T_CM_ENC_PLAN_DENOM epd on(ep.ENC_PLAN_ID = epd.ENC_PLAN_ID) "
			+ "join T_CM_CURR ci on (DENOM_CURR = ci.code_n3) WHERE "
			+ "trunc(ep.DATE_FORTHCOMING_ENCASHMENT) = #{date} AND ep.atm_id in (select id from t_cm_temp_atm_list) "
			+ "AND ep.IS_APPROVED = 1 AND ep.APPROVE_ID > 0 AND ep.CONFIRM_ID > 0 "
			+ "GROUP BY epd.DENOM_CURR,ci.CODE_A3 ORDER BY DENOM_CURR")
	@Options(useCache = true, fetchSize = 1000)
	List<ImmutablePair<String, String>> getAtmsEncPlanSums(@Param("date") java.sql.Date date);

	@ConstructorArgs({ @Arg(column = "DENOM_COUNT", javaType = String.class),
			@Arg(column = "CODE_A3", javaType = String.class) })
	@Select(" SELECT sum(epd.DENOM_VALUE*epd.DENOM_COUNT) as DENOM_COUNT, epd.DENOM_CURR , ci.CODE_A3 "
			+ "FROM T_CM_ENC_PERIOD ep join T_CM_ENC_PERIOD_DENOM epd on(ep.ID = epd.ENC_PERIOD_ID) "
			+ "join T_CM_CURR ci on (DENOM_CURR = ci.code_n3) WHERE "
			+ "trunc(ep.DATE_FORTHCOMING_ENCASHMENT) >= #{dateFrom} AND ep.atm_id in (select id from t_cm_temp_atm_list) "
			+ "AND trunc(ep.DATE_FORTHCOMING_ENCASHMENT) <= #{dateTo} GROUP BY epd.DENOM_CURR,ci.CODE_A3 "
			+ "ORDER BY DENOM_CURR")
	@Options(useCache = true, fetchSize = 1000)
	List<ImmutablePair<String, String>> getAtmsEncPeriodSums(@Param("dateFrom") java.sql.Date dateFrom, @Param("dateTo") java.sql.Date dateTo);

	@ConstructorArgs({
			@Arg(column = "CURR_REMAINING", javaType = String.class),
			@Arg(column = "CODE_A3", javaType = String.class) })
	@Select(" SELECT SUM(curr_remaining) as CURR_REMAINING, CURR_CODE, CODE_A3 FROM ( "
			+ "SELECT ccus.curr_remaining,ccus.curr_code , ci.CODE_A3 FROM t_cm_atm_actual_state aas "
			+ "JOIN t_cm_cashout_curr_stat ccus ON ( aas.atm_id = ccus.atm_id "
			+ "AND ccus.encashment_id = aas.cash_out_encashment_id AND ccus.stat_date = aas.cash_out_stat_date) "
			+ "JOIN t_cm_curr ci on (ccus.curr_code = ci.code_n3) "
			+ "WHERE aas.atm_id in (select id from t_cm_temp_atm_list) union "
			+ "SELECT ccus.curr_remaining,ccus.curr_code , ci.CODE_A3 FROM t_cm_atm_actual_state aas "
			+ "JOIN t_cm_cashin_r_curr_stat ccus ON ( aas.atm_id = ccus.atm_id "
			+ "AND ccus.cash_in_encashment_id = aas.cash_in_encashment_id "
			+ "AND ccus.stat_date = aas.cash_out_stat_date) JOIN t_cm_curr ci on (ccus.curr_code = ci.code_n3) "
			+ "WHERE aas.atm_id in (select id from t_cm_temp_atm_list)  ) GROUP BY CURR_CODE, CODE_A3 "
			+ "ORDER BY curr_code")
	@Options(useCache = true, fetchSize = 1000)
	List<ImmutablePair<String, String>> getAtmsRemainingSums();

	@ConstructorArgs({
			@Arg(column = "ID", javaType = Integer.class),
			@Arg(column = "NAME", javaType = String.class),
			@Arg(column = "TYPE_ID", javaType = AtmGroupType.class, typeHandler = EnumHandler.class),
			@Arg(column = "DESCRIPTION", javaType = String.class)
			})
	@Select(" SELECT ID, NAME, TYPE_ID, DESCRIPTION FROM T_CM_ATM_GROUP WHERE ID = #{id}")
	@Options(useCache = true, fetchSize = 1)
	AtmGroup getAtmGroup(@Param("id") String id);

	@ConstructorArgs({ @Arg(column = "DENOM_COUNT", javaType = String.class),
			@Arg(column = "CODE_A3", javaType = String.class) })
	@Select(" SELECT sum(epd.DENOM_VALUE*epd.DENOM_COUNT) as DENOM_COUNT, ci.CODE_A3 "
			+ "FROM T_CM_ENC_CASHOUT_STAT ep "
			+ "join V_CM_ENC_CASHOUT_STAT_DETAILS epd on(ep.ENCASHMENT_ID = epd.ENCASHMENT_ID and epd.ACTION_TYPE = 1) "
			+ "join T_CM_CURR ci on (DENOM_CURR = ci.code_n3) WHERE trunc(ep.ENC_DATE) = #{date} "
			+ "AND ep.atm_id in (select id from t_cm_temp_atm_list) AND epd.action_type = #{actionId} "
			+ "GROUP BY ci.CODE_A3 ORDER BY DENOM_COUNT DESC")
	@Options(useCache = true, fetchSize = 1000)
	List<ImmutablePair<String, String>> getAtmsEncStatSums(@Param("date") java.sql.Date date, @Param("actionId") Integer actionId);
}
