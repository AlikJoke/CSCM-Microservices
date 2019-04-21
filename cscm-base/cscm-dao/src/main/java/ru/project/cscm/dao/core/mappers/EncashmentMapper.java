package ru.project.cscm.dao.core.mappers;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import ru.project.cscm.dao.core.Mapper;

public interface EncashmentMapper extends Mapper {

    @Insert("insert into T_CM_ENC_CASHIN_STAT values (#{atmId, jdbcType=NUMERIC}, #{cashEncDate, jdbcType=TIMESTAMP}, #{cashEncId, jdbcType=NUMERIC})")
    void saveEncashmentInStatistics(@Param("atmId") Integer atmId, @Param("cashEncDate") Timestamp cashEncDate,
            @Param("cashEncId") Integer encId);

    @Insert("insert into T_CM_ENC_CASHIN_STAT_DETAILS (CASH_IN_ENCASHMENT_ID, CASS_VALUE, CASS_CURR, CASS_COUNT, CASS_NUMBER, ACTION_TYPE) values "
            + "(#{cashEncId, jdbcType=NUMERIC}, #{cassValue, jdbcType=NUMERIC}, #{cassCurr, jdbcType=NUMERIC},"
            + "#{cassCount, jdbcType=NUMERIC}, #{cassNumber, jdbcType=NUMERIC}, #{actionType, jdbcType=NUMERIC})")
    void saveEncashmentInStatisticsDetails(@Param("cashEncId") Integer encId, @Param("cassValue") Integer cassValue,
            @Param("cassCurr") Integer cassCurr, @Param("cassCount") Integer cassCount, @Param("cassNumber") Integer cassNumber,
            @Param("actionType") Integer actionType);

    @Insert("insert into T_CM_ENC_CASHOUT_STAT values (#{atmId, jdbcType=NUMERIC}, #{cashEncDate, jdbcType=TIMESTAMP}, #{cashEncId, jdbcType=NUMERIC},"
            + "#{emergencyEncashment, jdbcType=INTEGER}, #{cashAddEnc, jdbcType=INTEGER})")
    void saveEncashmentOutStatistics(@Param("atmId") Integer atmId, @Param("cashEncDate") Timestamp cashEncDate,
            @Param("cashEncId") Integer encId, @Param("emergencyEncashment") Integer emergencyEncashment,
            @Param("cashAddEnc") Integer cashAddEnc);

    @Insert("insert into T_CM_ENC_CASHOUT_STAT_DETAILS (ENCASHMENT_ID, CASS_VALUE, CASS_CURR, CASS_COUNT, CASS_NUMBER, ACTION_TYPE) values "
            + "(#{cashEncId, jdbcType=NUMERIC}, #{cassValue, jdbcType=NUMERIC}, #{cassCurr, jdbcType=NUMERIC},"
            + "#{cassCount, jdbcType=NUMERIC}, #{cassNumber, jdbcType=NUMERIC}, #{actionType, jdbcType=NUMERIC})")
    void saveEncashmentOutStatisticsDetails(@Param("cashEncId") Integer encId, @Param("cassValue") Integer cassValue,
            @Param("cassCurr") Integer cassCurr, @Param("cassCount") Integer cassCount, @Param("cassNumber") Integer cassNumber,
            @Param("actionType") Integer actionType);

    @Insert("insert into T_CM_CASHIN_STAT values "
            + "(#{atmId, jdbcType=NUMERIC}, #{statDate, jdbcType=TIMESTAMP}, #{cashEncId, jdbcType=NUMERIC},"
            + "#{billsCount, jdbcType=NUMERIC}, #{billsRemaining, jdbcType=NUMERIC}, #{availableCoeff, jdbcType=NUMERIC})")
    void saveCashInStatistics(@Param("atmId") Integer atmId, @Param("statDate") Timestamp statDate, @Param("cashEncId") Integer cashEncId,
            @Param("billsCount") Integer billsCount, @Param("billsRemaining") Integer billsRemaining,
            @Param("availableCoeff") Double availableCoeff);

    @Insert("insert into t_cm_cashin_r_cass_stat values "
            + "(#{atmId, jdbcType=NUMERIC}, #{statDate, jdbcType=TIMESTAMP}, #{cashEncId, jdbcType=NUMERIC},"
            + "#{cassNumber, jdbcType=NUMERIC}, #{cassValue, jdbcType=NUMERIC}, #{cassCurr, jdbcType=NUMERIC},"
            + "#{cassCountIn, jdbcType=NUMERIC}, #{cassTransCountIn, jdbcType=NUMERIC},"
            + "#{cassTransCountOut, jdbcType=NUMERIC}, #{cassRemaining, jdbcType=NUMERIC},"
            + "#{availableCoeff, jdbcType=NUMERIC}, #{cassCountOut, jdbcType=INTEGER})")
    void saveCashInRecycleCassetteStatistics(@Param("atmId") Integer atmId, @Param("statDate") Timestamp statDate,
            @Param("cashEncId") Integer cashEncId, @Param("cassNumber") Integer cassNumber, @Param("cassValue") Integer cassValue,
            @Param("cassCurr") Integer cassCurr, @Param("cassCountIn") Integer cassCountIn,
            @Param("cassTransCountIn") Integer cassTransCountIn, @Param("cassTransCountOut") Integer cassTransCountOut,
            @Param("cassRemaining") Integer cassRemaining, @Param("availableCoeff") Double availableCoeff,
            @Param("cassCountOut") Integer cassCountOut);

    @Insert("insert into T_CM_CASHOUT_CASS_STAT values "
            + "(#{atmId, jdbcType=NUMERIC}, #{statDate, jdbcType=TIMESTAMP}, #{cashEncId, jdbcType=NUMERIC},"
            + "#{cassNumber, jdbcType=NUMERIC}, #{cassValue, jdbcType=NUMERIC}, #{cassCurr, jdbcType=NUMERIC},"
            + "#{cassCount, jdbcType=NUMERIC}, #{cassTransCount, jdbcType=NUMERIC}," + "#{cassRemaining, jdbcType=NUMERIC},"
            + "#{availableCoeff, jdbcType=NUMERIC},"
            + "#{demandValue, jdbcType=INTEGER}, #{exhaustionDate, jdbcType=TIMESTAMP})")
    void saveCashOutCassetteStatistics(@Param("atmId") Integer atmId, @Param("statDate") Timestamp statDate,
            @Param("cashEncId") Integer cashEncId, @Param("cassNumber") Integer cassNumber, @Param("cassValue") Integer cassValue,
            @Param("cassCurr") Integer cassCurr, @Param("cassCount") Integer cassCount, @Param("cassTransCount") Integer cassTransCount,
            @Param("cassRemaining") Integer cassRemaining, @Param("availableCoeff") Double availableCoeff,
            @Param("demandValue") Integer demandValue,
            @Param("exhaustionDate") Timestamp exhaustionDate);

    @Insert("insert into t_cm_reject_stat values "
            + "(#{atmId, jdbcType=NUMERIC}, #{statDate, jdbcType=TIMESTAMP}, #{cashEncId, jdbcType=NUMERIC},"
            + "#{billsCount, jdbcType=NUMERIC}, #{billsRemaining, jdbcType=NUMERIC})")
    void saveRejectStatistics(@Param("atmId") Integer atmId, @Param("statDate") Timestamp statDate, @Param("cashEncId") Integer cashEncId,
            @Param("billsCount") Integer billsCount, @Param("billsRemaining") Integer billsRemaining);

    @Insert("Insert into T_CM_ENC_PLAN "
            + " (ENC_PLAN_ID, ATM_ID, DATE_PREVIOUS_ENCASHMENT, INTERVAL_ENC_LAST_TO_FORTH, DATE_FORTHCOMING_ENCASHMENT,  "
            + " INTERVAL_ENC_FORTH_TO_FUTURE, DATE_FUTURE_ENCASHMENT, "
            + "  IS_APPROVED, ENC_LOSTS_CURR_CODE, FORECAST_RESP_CODE, "
            + "EMERGENCY_ENCASHMENT, ENC_LOSTS, ENC_PRICE, CASH_ADD_ENCASHMENT, ENCASHMENT_TYPE,"
            + "ENC_LOSTS_JOINT, ENC_LOSTS_SPLIT, ENCASHMENT_TYPE_BY_LOSTS, "
            + "ENC_PRICE_CASH_IN , ENC_PRICE_CASH_OUT, ENC_PRICE_BOTH_IN_OUT) " + " VALUES "
            + " (#{encPlanId, jdbcType=NUMERIC}, #{atmId, jdbcType=NUMERIC}, #{lastEncDate, jdbcType=TIMESTAMP}, #{forthcomingEncInterval, jdbcType=NUMERIC}, "
            + "#{forthcomingEncDate, jdbcType=TIMESTAMP}, #{futureEncInterval, jdbcType=NUMERIC}, #{futureEncDate, jdbcType=TIMESTAMP}, "
            + "#{isApproved, jdbcType=INTEGER}, #{lostsCurr, jdbcType=NUMERIC}, #{forecastResp, jdbcType=NUMERIC}, #{isEmergencyEncashment, jdbcType=INTEGER}, "
            + "#{encLosts, jdbcType=NUMERIC}, #{encPrice, jdbcType=NUMERIC}, #{isAddCashmanagement, jdbcType=INTEGER}, #{encTypeId, jdbcType=NUMERIC}, "
            + "#{lostsJointEcnashment, jdbcType=NUMERIC}, "
            + "#{lostsSplitEcnashment, jdbcType=NUMERIC}, #{encTypeByLostsId, jdbcType=NUMERIC}, #{encPriceCashIn, jdbcType=NUMERIC}, "
            + "#{encPriceCashOut, jdbcType=NUMERIC}, #{encPriceBothInOut, jdbcType=NUMERIC})")
    void insertForecastData(@Param("encPlanId") Integer encPlanId, @Param("atmId") Integer atmId,
            @Param("lastEncDate") Timestamp lastEncDate, @Param("forthcomingEncInterval") Integer forthcomingEncInterval,
            @Param("forthcomingEncDate") Timestamp forthcomingEncDate,
            @Param("futureEncInterval") Integer futureEncInterval, @Param("futureEncDate") Timestamp futureEncDate,
            @Param("isApproved") Boolean isApproved, @Param("lostsCurr") Integer lostsCurr,
            @Param("forecastResp") Integer forecastResp, @Param("isEmergencyEncashment") Boolean isEmergencyEncashment,
            @Param("encLosts") Double encLosts, @Param("encPrice") Double encPrice,
            @Param("isAddCashmanagement") Boolean isAddCashmanagement, @Param("encTypeId") Integer encTypeId,
            @Param("lostsJointEcnashment") Double lostsJointEcnashment,
            @Param("lostsSplitEcnashment") Double lostsSplitEcnashment,
            @Param("encTypeByLostsId") Integer encTypeByLostsId, @Param("encPriceCashIn") Double encPriceCashIn,
            @Param("encPriceCashOut") Double encPriceCashOut, @Param("encPriceBothInOut") Double encPriceBothInOut);

    @Update("UPDATE T_CM_ENC_PLAN SET ATM_ID = #{atmId}, DATE_PREVIOUS_ENCASHMENT = #{lastEncDate}, "
            + "INTERVAL_ENC_LAST_TO_FORTH = #{forthcomingEncInterval}, "
            + "DATE_FORTHCOMING_ENCASHMENT = #{forthcomingEncDate}, "
            + "INTERVAL_ENC_FORTH_TO_FUTURE = #{futureEncInterval}, DATE_FUTURE_ENCASHMENT = #{futureEncDate} , "
            + "IS_APPROVED = #{isApproved}, ENC_LOSTS_CURR_CODE = #{lostsCurr}, "
            + "FORECAST_RESP_CODE = #{forecastResp}, EMERGENCY_ENCASHMENT = #{isEmergencyEncashment}, "
            + "ENC_LOSTS = #{encLosts} , ENC_PRICE = #{encPrice} , "
            + "CASH_ADD_ENCASHMENT = #{isAddCashmanagement}, ENCASHMENT_TYPE = #{encTypeId}, "
            + "ENC_LOSTS_JOINT = #{lostsJointEcnashment}, ENC_LOSTS_SPLIT = #{lostsSplitEcnashment}, "
            + "ENCASHMENT_TYPE_BY_LOSTS = #{encTypeByLostsId}, ENC_PRICE_CASH_IN = #{encPriceCashIn}, "
            + "ENC_PRICE_CASH_OUT = #{encPriceCashOut}, ENC_PRICE_BOTH_IN_OUT = #{encPriceBothInOut} "
            + "WHERE ENC_PLAN_ID = #{encPlanId}")
    void updateEncashmentPlan(@Param("encPlanId") Integer encPlanId, @Param("atmId") Integer atmId,
            @Param("lastEncDate") Timestamp lastEncDate, @Param("forthcomingEncInterval") Integer forthcomingEncInterval,
            @Param("forthcomingEncDate") Timestamp forthcomingEncDate, @Param("futureEncDate") Timestamp futureEncDate,
            @Param("futureEncInterval") Integer futureEncInterval, @Param("isApproved") Integer isApproved,
            @Param("lostsCurr") Integer lostsCurr, @Param("forecastResp") Integer forecastResp,
            @Param("isEmergencyEncashment") Boolean isEmergencyEncashment, @Param("encLosts") Timestamp encLosts,
            @Param("encPrice") Double encPrice, @Param("isAddCashmanagement") Boolean isAddCashmanagement,
            @Param("encTypeId") Integer encTypeId, @Param("lostsJointEcnashment") Double lostsJointEcnashment,
            @Param("lostsSplitEcnashment") Double lostsSplitEcnashment,
            @Param("encTypeByLostsId") Integer encTypeByLostsId, @Param("encPriceCashIn") Double encPriceCashIn,
            @Param("encPriceCashOut") Double encPriceCashOut, @Param("encPriceBothInOut") Double encPriceBothInOut);
    
    @Select("SELECT COALESCE(MAX(CASH_IN_ENCASHMENT_ID), 0) FROM T_CM_ENC_CASHIN_STAT")
    @ResultType(Integer.class)
    Integer getLastCashInEncashmentId();
    
    @Select("SELECT COALESCE(MAX(ENCASHMENT_ID), 0) FROM T_CM_ENC_CASHOUT_STAT")
    @ResultType(Integer.class)
    Integer getLastCashOutEncashmentId();
}
