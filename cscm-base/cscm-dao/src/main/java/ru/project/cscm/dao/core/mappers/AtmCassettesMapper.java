package ru.project.cscm.dao.core.mappers;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import ru.project.cscm.dao.core.Mapper;
import ru.project.cscm.dto.items.monitoring.AtmCashOutCassetteItem;
import ru.project.cscm.dto.items.monitoring.AtmCassetteItem;
import ru.project.cscm.dto.items.monitoring.AtmRecyclingCassetteItem;

public interface AtmCassettesMapper extends Mapper {

    @Insert("insert into T_CM_ATM_CASSETTES (ATM_ID, CASS_TYPE, CASS_NUMBER, CASS_CURR, CASS_VALUE, CASS_CAPACITY, CASS_STATE, CASS_NOTES) VALUES ( #{atmId}, #{typeId}, #{cassNumber}, "
            + "#{curr}, #{denom}, #{capacity, jdbcType = INTEGER}, #{isNotWorking}, #{notesCount, jdbcType = INTEGER})")
    void saveAtmCassettes(@Param("atmId") int atmId, @Param("typeId") Integer typeId, @Param("curr") Integer curr,
            @Param("cassNumber") Integer cassNumber, @Param("denom") Integer denom, @Param("capacity") Integer capacity,
            @Param("isNotWorking") Integer isNotWorking, @Param("notesCount") Integer notesCount);

    @Delete("DELETE FROM T_CM_ATM_CASSETTES WHERE ATM_ID = #{atmId}")
    void deleteAtmCassettes(@Param("atmId") Integer atmId);

    @Update("UPDATE T_CM_ATM_CASSETTES SET CASS_STATE = #{isNotWorking} where ATM_ID = #{atmId} "
            + "AND CASS_TYPE = #{typeId} AND CASS_NUMBER = #{cassNumber}")
    void updateAtmCassettes(@Param("atmId") Integer atmId, @Param("typeId") Integer typeId, @Param("cassNumber") Integer cassNumber,
            @Param("isNotWorking") Integer isNotWorking);

    @Results({ @Result(property = "amountInit", column = "CASS_INIT", javaType = Integer.class),
            @Result(property = "number", column = "CASS_NUMBER", javaType = Integer.class),
            @Result(property = "denom", column = "CASS_VALUE", javaType = Integer.class),
            @Result(property = "currency", column = "CURRENCY", javaType = String.class),
            @Result(property = "currCode", column = "cass_curr", javaType = Integer.class),
            @Result(property = "currCodeA3", column = "code_a3", javaType = String.class),
            @Result(property = "balanceAlert", column = "BALANCE_STATUS", javaType = Boolean.class),
            @Result(property = "amountLeft", column = "CASS_REMAINING", javaType = Integer.class),
            @Result(property = "amountLeftFE", column = "CASS_REMAINING_LOAD", javaType = Integer.class),
            @Result(property = "capacity", column = "CASS_CAPACITY", javaType = Integer.class),
            @Result(property = "notesCount", column = "CASS_NOTES", javaType = Integer.class),
            @Result(property = "isCassNotWorking", column = "CASS_STATE", javaType = Boolean.class),
            @Result(property = "demandValue", column = "CASS_CURR_DEMAND", javaType = Integer.class),
            @Result(property = "exhaustionDate", column = "CASS_CURR_EXHAUSTION_DATE", javaType = Timestamp.class)})
    @Select("select cass.cass_number, cass.cass_value, cass.CASS_NOTES, cs.cass_remaining,cs.cass_curr, cr.code_a3, cr.currency, "
            + "en.CASS_COUNT as CASS_INIT, CI.CODE_A3,cass.CASS_STATE, bal.CASS_REMAINING_LOAD, "
            + "bal.balance_status, cass.CASS_CAPACITY, cs.CASS_CURR_EXHAUSTION_DATE, cs.CASS_CURR_DEMAND "
            + "from T_CM_ATM_CASSETTES cass "
            + "join t_cm_curr cr on cr.CODE_N3 = cass.cass_curr "
            + "left outer join T_CM_CASHOUT_CASS_STAT cs on ("
            + "cs.atm_id = cass.atm_id) "
            + "left outer join T_CM_INTGR_CASS_BALANCE bal on ("
            + "bal.atm_id = cass.atm_id "
            + "and bal.cass_number = cass.cass_number) "
            + "join (select sum(CASS_COUNT) as CASS_COUNT, ENCASHMENT_ID,CASS_NUMBER "
            + "from T_CM_ENC_CASHOUT_STAT_DETAILS where action_type in (2,4)"
            + "group by ENCASHMENT_ID,CASS_NUMBER) en on (en.encashment_id = cs.encashment_id and cs.cass_number = en.cass_number) "
            + "left outer join T_CM_CURR ci on (ci.code_n3 = cass.cass_curr) "
            + "where  cass.atm_id = #{atmId} "
            + "AND cass.CASS_TYPE = #{typeId} "
            + "AND cs.encashment_id = #{encId} "
            + "AND cs.stat_date = #{statDate} "
            + "ORDER BY cs.CASS_NUMBER")
    @Options(useCache = true, fetchSize = 1000)
    List<AtmCashOutCassetteItem> getAtmCashOutCassettesList(@Param("atmId") int atmId, @Param("typeId") int typeId,
            @Param("encId") int encId, @Param("statDate") Timestamp statDate);

    @Results({ @Result(property = "amountInit", column = "CASS_INIT", javaType = Integer.class),
            @Result(property = "number", column = "CASS_NUMBER", javaType = Integer.class),
            @Result(property = "denom", column = "CASS_VALUE", javaType = Integer.class),
            @Result(property = "currency", column = "CURRENCY", javaType = String.class),
            @Result(property = "currCode", column = "cass_curr", javaType = Integer.class),
            @Result(property = "currCodeA3", column = "code_a3", javaType = String.class),
            @Result(property = "balanceAlert", column = "BALANCE_STATUS", javaType = Boolean.class),
            @Result(property = "amountLeft", column = "CASS_REMAINING", javaType = Integer.class),
            @Result(property = "amountLeftFE", column = "CASS_REMAINING_LOAD", javaType = Integer.class),
            @Result(property = "codeA3", column = "CODE_A3", javaType = String.class),
            @Result(property = "amountIn", column = "CASS_COUNT_IN", javaType = Integer.class),
            @Result(property = "amountOut", column = "CASS_COUNT_OUT", javaType = Integer.class),
            @Result(property = "notesCount", column = "CASS_NOTES", javaType = Integer.class),
            @Result(property = "capacity", column = "CASS_CAPACITY", javaType = Integer.class),
            @Result(property = "isCassNotWorking", column = "CASS_STATE", javaType = Boolean.class)})
    @Select("select cass.CASS_NUMBER, cass.cass_notes, cass.cass_value,csi.cass_remaining,"
            + "sum(cs.CASS_COUNT_IN) as CASS_COUNT_IN, cr.code_a3, cr.currency, "
            + "sum(cs.CASS_COUNT_OUT) as CASS_COUNT_OUT,"
            + "cass.cass_curr, en.CASS_COUNT as CASS_INIT, CI.CODE_A3,"
            + "cass.CASS_STATE, bal.CASS_REMAINING_LOAD, bal.balance_status, cass.CASS_CAPACITY "
            + "from T_CM_ATM_CASSETTES cass "
            + "join t_cm_curr cr on cr.CODE_N3 = cass.cass_curr "
            + "left outer join ("
            + "select sum(CASS_COUNT) as CASS_COUNT, CASH_IN_ENCASHMENT_ID,CASS_NUMBER "
            + "from T_CM_ENC_CASHIN_STAT_DETAILS "
            + "where action_type in (2,4) and cash_in_encashment_id = #{cachInEncId} "
            + "group by CASH_IN_ENCASHMENT_ID,CASS_NUMBER) en on ("
            + "cass.cass_number = en.cass_number) "
            + "left outer join T_CM_CASHIN_R_CASS_STAT cs on ("
            + "cs.atm_id = cass.atm_id "
            + "and cs.cass_number = cass.cass_number "
            + "and cs.cass_value = cass.cass_value "
            + "and cs.cass_curr = cass.cass_curr "
            + "AND cs.cash_in_encashment_id = #{cachInEncId}) "
            + "left outer join T_CM_INTGR_CASS_BALANCE bal on ("
            + "bal.atm_id = cass.atm_id "
            + "and bal.cass_number = cass.cass_number) "
            + "left outer join T_CM_CURR ci on (ci.code_n3 = cass.cass_curr) "
            + "left outer join (select CASS_REMAINING,cass_number "
            + "from T_CM_CASHIN_R_CASS_STAT "
            + "where  atm_id = #{atmId} "
            + "AND cash_in_encashment_id = #{cachInEncId} "
            + "AND stat_date = #{statDate} ) csi on (csi.cass_number = cass.cass_number) "
            + "where cass.atm_id = #{atmId} "
            + "AND cass.CASS_TYPE = #{typeId} "
            + "GROUP BY cass.CASS_NUMBER, cass.cass_value,csi.cass_remaining,cass.cass_curr, CI.CODE_A3,cass.CASS_TYPE,cass.CASS_STATE,en.CASS_COUNT,bal.balance_status, bal.CASS_REMAINING_LOAD, "
            + "cass.cass_notes, cass.CASS_CAPACITY, cr.code_a3, cr.currency  "
            + "ORDER BY cass.CASS_NUMBER")
    @Options(useCache = true, fetchSize = 1000)
    List<AtmRecyclingCassetteItem> getAtmRecyclingCassettesList(@Param("atmId") int atmId, @Param("cachInEncId") int cachInEncId,
            @Param("statDate") Timestamp statDate, @Param("typeId") int typeId);
    
    @ConstructorArgs({
        @Arg(column = "CASS_NUMBER", javaType = Integer.class),
        @Arg(column = "CASS_VALUE", javaType = Integer.class),
        @Arg(column = "CASS_CAPACITY", javaType = Integer.class),
        @Arg(column = "CASS_CURR", javaType = Integer.class),
        @Arg(column = "CODE_A3", javaType = String.class),
        @Arg(column = "CURRENCY", javaType = String.class),
        @Arg(column = "CASS_TYPE", javaType = Integer.class),
        @Arg(column = "CASS_STATE", javaType = Boolean.class),
        @Arg(column = "CASS_NOTES", javaType = Integer.class),
         })
    @Select("select cass.cass_number, cass.cass_notes, cass.cass_curr, cass.cass_value, cass.cass_value,cass.CASS_STATE, cass.CASS_TYPE, ci.CODE_A3, ci.CURRENCY, cass.CASS_CAPACITY "
            + "from T_CM_ATM_CASSETTES cass left outer join T_CM_CURR ci on (ci.code_n3 = cass.cass_curr) "
            + "where cass.atm_id = #{atmId} and cass.cass_type = #{typeId} and cass.cass_number = #{cassNumber}")
    @Options(useCache = true, fetchSize = 1)
    AtmCassetteItem getAtmCassette(@Param("atmId") int atmId, @Param("typeId") int typeId, @Param("cassNumber") int cassNumber);
}
