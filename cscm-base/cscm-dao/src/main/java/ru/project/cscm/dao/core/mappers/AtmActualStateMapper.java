package ru.project.cscm.dao.core.mappers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import ru.project.cscm.dao.core.Mapper;
import ru.project.cscm.dao.core.mappers.builders.AtmActualStateBuilder;
import ru.project.cscm.dto.items.filters.MonitoringFilter;
import ru.project.cscm.dto.items.monitoring.AtmActualStateItem;

/**
 * Маппер для мониторинга состояния банкоматов.
 * 
 * @author Alimurad Ramazanov
 *
 */
public interface AtmActualStateMapper extends Mapper {

    @ConstructorArgs({ @Arg(column = "CASH_OUT_ENC_ID", javaType = Integer.class),
            @Arg(column = "CASH_IN_ENC_ID", javaType = Integer.class), @Arg(column = "STATE", javaType = String.class),
            @Arg(column = "CITY", javaType = String.class), @Arg(column = "STREET", javaType = String.class),
            @Arg(column = "ATM_STATE", javaType = Integer.class), @Arg(column = "ATM_PROBLEM", javaType = Integer.class),
            @Arg(column = "ATM_ID", javaType = Integer.class), @Arg(column = "MODEL", javaType = String.class),
            @Arg(column = "CASH_IN_CAPACITY", javaType = Integer.class), @Arg(column = "date_previous_encashment", javaType = Date.class),
            @Arg(column = "date_forthcoming_encashment", javaType = Date.class),
            @Arg(column = "PLANNED_ENC_SUMM", javaType = Integer.class), @Arg(column = "CASH_IN_BILLS_INITIAL", javaType = Integer.class),
            @Arg(column = "CASH_IN_BILLS_REMAINING", javaType = Integer.class),
            @Arg(column = "REJECT_BILLS_INITIAL", javaType = Integer.class),
            @Arg(column = "REJECT_BILLS_REMAINING", javaType = Integer.class), @Arg(column = "CASH_OUT_STAT_DATE", javaType = Date.class),
            @Arg(column = "STAT_LOAD_DATE", javaType = Date.class), @Arg(column = "CASH_IN_R_BILLS_INITIAL", javaType = Integer.class),
            @Arg(column = "LAST_WITHDRAWAL_HOURS", javaType = Integer.class),
            @Arg(column = "LAST_ADDITION_HOURS", javaType = Integer.class), @Arg(column = "ATM_NAME", javaType = String.class),
            @Arg(column = "DATE_FORTHCOMING_ENCASHMENT", javaType = Date.class),
            @Arg(column = "EMERGENCY_ENCASHMENT", javaType = Boolean.class), @Arg(column = "IS_APPROVED", javaType = Boolean.class),
            @Arg(column = "OUT_OF_CASH_OUT_DATE", javaType = Date.class), @Arg(column = "AVG_TRANS_IN_DAY", javaType = Integer.class),
            @Arg(column = "AVG_TRANS_IN_HOUR", javaType = Integer.class), @Arg(column = "EXTERNAL_ATM_ID", javaType = String.class) })
    @SelectProvider(type = AtmActualStateBuilder.class, method = "builderAtmActualStateQuery")
    @Options(useCache = true, fetchSize = 1000)
    List<AtmActualStateItem> getAtmActualStateList(@Param("addFilter") MonitoringFilter filter);

    @Delete("delete from t_cm_atm_actual_state where atm_id = #{atmId}")
    void deleteAtmState(@Param("atmId") Integer atmId);

    @Insert("insert into t_cm_atm_actual_state (ATM_ID, CASH_OUT_STAT_DATE, CASH_OUT_ENCASHMENT_ID, CASH_IN_STAT_DATE, CASH_IN_ENCASHMENT_ID,"
            + "CASH_IN_CAPACITY, LAST_UPDATE, OUT_OF_CASH_OUT_DATE, OUT_OF_CASH_OUT_CURR, OUT_OF_CASH_OUT_RESP, CASH_IN_INITIAL,"
            + "REJECT_INITIAL, OUT_OF_CASH_IN_RESP, LAST_WITHDRAWAL_HOURS, LAST_ADDITION_HOURS, CURR_REMAINING_ALERT, CASH_IN_R_INITIAL,"
            + "OUT_OF_CASH_IN_DATE, ATM_STATE, ATM_PROBLEM, AVG_TRANS_IN_HOUR, AVG_TRANS_IN_DAY,"
            + "DATE_PREVIOUS_ENCASHMENT, PLANNED_ENC_SUMM, DATE_FORTHCOMING_ENCASHMENT, IS_APPROVED, EMERGENCY_ENCASHMENT) values "
            + "(#{atmId, jdbcType=NUMERIC}, #{cashOutStateDate, jdbcType=TIMESTAMP}, "
            + "#{cashOutEncId, jdbcType=NUMERIC}, #{cashInStatDate, jdbcType=TIMESTAMP}, #{cashInEncId, jdbcType=NUMERIC}, "
            + "#{cashInCapacity, jdbcType = NUMERIC}, #{lastUpdate, jdbcType=TIMESTAMP}, #{outOfCashOutDate, jdbcType=TIMESTAMP},"
            + "#{outOfCashOutCurr, jdbcType=NUMERIC}, #{outOfCashOutResp, jdbcType=NUMERIC}, #{cashInInitial, jdbcType=NUMERIC}, "
            + "#{rejectInitial, jdbcType=NUMERIC}, #{outOfCashInResp, jdbcType=NUMERIC}, #{lastWithdrawalHours, jdbcType=NUMERIC}, "
            + "#{lastAddHours, jdbcType=NUMERIC}, #{currRemainingAlert, jdbcType=INTEGER},"
            + "#{cashInRInitial, jdbcType=NUMERIC}, #{outOfCashInDate, jdbcType=TIMESTAMP},"
            + "#{atmState, jdbcType=NUMERIC}, #{atmProblem, jdbcType=NUMERIC}, #{avgTransInHour, jdbcType=NUMERIC}, "
            + "#{avgTransInDay, jdbcType=NUMERIC}, #{prevEncashmentDate, jdbcType=TIMESTAMP}, #{plannedEncSumm, jdbcType=NUMERIC},"
            + "#{forthComingEncashmentDate, jdbcType=TIMESTAMP}, #{isApproved, jdbcType=NUMERIC}, #{emergency, jdbcType=NUMERIC})")
    void createAtmActualState(@Param("atmId") Integer atmId, @Param("cashOutStateDate") Timestamp cashOutStateDate,
            @Param("cashOutEncId") Integer cashOutEncId, @Param("cashInStatDate") Timestamp cashInStatDate,
            @Param("cashInEncId") Integer cashInEncId, @Param("lastUpdate") Timestamp lastUpdate,
            @Param("outOfCashOutDate") Timestamp outOfCashOutDate, @Param("outOfCashOutCurr") Integer outOfCashOutCurr,
            @Param("outOfCashOutResp") Integer outOfCashOutResp, @Param("cashInInitial") Integer cashInInitial,
            @Param("rejectInitial") Integer rejectInitial, @Param("outOfCashInResp") Integer outOfCashInResp,
            @Param("lastWithdrawalHours") Integer lastWithdrawalHours, @Param("lastAddHours") Integer lastAddHours,
            @Param("currRemainingAlert") Integer currRemainingAlert, @Param("cashInRInitial") Integer cashInRInitial,
            @Param("outOfCashInDate") Timestamp outOfCashInDate, @Param("atmState") Integer atmState,
            @Param("atmProblem") Integer atmProblem, @Param("avgTransInHour") Integer avgTransInHour,
            @Param("avgTransInDay") Integer avgTransInDay, @Param("cashInCapacity") Integer cashInCapacity,
            @Param("prevEncashmentDate") Timestamp prevEncashmentDate, @Param("plannedEncSumm") Integer plannedEncSumm,
            @Param("forthComingEncashmentDate") Timestamp forthComingEncashmentDate, @Param("isApproved") Integer isApproved,
            @Param("emergency") Integer emergency);

}
