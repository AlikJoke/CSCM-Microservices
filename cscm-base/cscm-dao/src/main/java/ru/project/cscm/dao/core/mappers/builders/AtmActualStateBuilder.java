package ru.project.cscm.dao.core.mappers.builders;

import java.sql.SQLException;
import java.util.Map;

import ru.project.cscm.dao.utils.QueryConstructor;
import ru.project.cscm.dto.items.filters.MonitoringFilter;

public class AtmActualStateBuilder {

	public String builderAtmActualStateQuery(
			Map<String, MonitoringFilter> params) {
		//MonitoringFilter addFilter = params.get("addFilter");
		StringBuilder sql = new StringBuilder(this.createQuery());

		QueryConstructor querConstr = new QueryConstructor();
		querConstr.setQueryBody(sql.toString(), true);
		
		try {
			return querConstr.getQuery();
		} catch (SQLException e) {
			throw new RuntimeException("Can't create valid query", e);
		}
	}

	public String getAtmDeviceStateBuilder_limit(Map<String, Object> params) {
		String limit = (String) params.get("limit");
		return "SELECT ATM_STATE FROM T_CM_ATM_ACTUAL_STATE WHERE ATM_ID = #{atmId} "
				+ limit;
	}

	public String getCashOutHoursFromLastWithdrawalBuilder_limit(
			Map<String, Object> params) {
		String limit = (String) params.get("limit");
		return "select t1.stat_date AS STAT_DATE, t2.cass_count "
				+ "from (select atm_id, max(stat_date) as stat_date "
				+ "from T_CM_CASHOUT_CASS_STAT where cass_count<>0 group by atm_id) t1, T_CM_CASHOUT_CASS_STAT t2 "
				+ "where t1.atm_id=t2.atm_id and t1.stat_date=t2.stat_date and t1.atm_id = #{atmId} "
				+ limit;
	}

	public String getCashInHoursFromLastAdditionBuilder_limit(
			Map<String, Object> params) {
		String limit = (String) params.get("limit");
		return "select t1.stat_date AS STAT_DATE, t2.bills_count "
				+ "from (select atm_id, max(stat_date) as stat_date "
				+ "from T_CM_CASHIN_STAT where bills_count<>0 group by atm_id) t1, T_CM_CASHIN_STAT t2 "
				+ "where t1.atm_id=t2.atm_id and t1.stat_date=t2.stat_date and t1.atm_id= #{atmId} "
				+ limit;
	}
	
	private String createQuery() {

		return "with "
				+ "ci_enc as ("
				+ "	    SELECT ATM_ID,count(1) as CNT, date_trunc('day',ecs.CASH_IN_ENC_DATE) as ENC_DATE "
				+ "    FROM T_CM_ENC_CASHIN_STAT ecs "
				+ "    WHERE ecs.CASH_IN_ENC_DATE >= date_trunc('day',CURRENT_TIMESTAMP) "
				+ "    GROUP BY ATM_ID, date_trunc('day',ecs.CASH_IN_ENC_DATE) "
				+ "),"
				+ "co_enc as ("
				+ "    SELECT ATM_ID,count(1) as CNT, date_trunc('day',ecs.ENC_DATE) as ENC_DATE "
				+ "    FROM T_CM_ENC_CASHOUT_STAT ecs "
				+ "    WHERE ecs.ENC_DATE >= date_trunc('day',CURRENT_TIMESTAMP) "
				+ "    GROUP BY ATM_ID,date_trunc('day',ecs.ENC_DATE) "
				+ "),"
				+ "jt_enc as ("
				+ "    SELECT ecso.ATM_ID,count(1) as CNT, date_trunc('day',ecso.ENC_DATE) as ENC_DATE "
				+ "    FROM T_CM_ENC_CASHOUT_STAT ecso,T_CM_ENC_CASHIN_STAT ecsi "
				+ "    WHERE ecso.ENC_DATE >= date_trunc('day',CURRENT_TIMESTAMP) "
				+ "        AND abs(ceil(extract(epoch from ecso.ENC_DATE-ecsi.CASH_IN_ENC_DATE)/3600)) < 1 "
				+ "        AND ecso.ATM_ID = ecsi.ATM_ID "
				+ "    GROUP BY ecso.ATM_ID, date_trunc('day',ecso.ENC_DATE) "
				+ "),"
				+ "plan_enc as ("
				+ "    SELECT ENCASHMENT_TYPE,ATM_ID,ENC_PLAN_ID,date_trunc('day',DATE_FORTHCOMING_ENCASHMENT) as ENC_DATE,"
				+ "    row_number() over (partition by ATM_ID,ENCASHMENT_TYPE order by ENC_PLAN_ID) as RNK "
				+ "    FROM T_CM_ENC_PLAN ep "
				+ "    WHERE ep.DATE_FORTHCOMING_ENCASHMENT >= date_trunc('day',CURRENT_TIMESTAMP) "
				+ "),"
				+ "next_encashment as ("
				+ "    SELECT ep.ATM_ID, MIN(ENC_PLAN_ID) as NEXT_ENC_ID "
				+ "    FROM plan_enc ep "
				+ "    left outer join ci_enc on (ci_enc.ATM_ID = ep.ATM_ID and ci_enc.ENC_DATE = ep.ENC_DATE) "
				+ "    left outer join co_enc on (co_enc.ATM_ID = ep.ATM_ID and co_enc.ENC_DATE = ep.ENC_DATE) "
				+ "    left outer join jt_enc on (jt_enc.ATM_ID = ep.ATM_ID and jt_enc.ENC_DATE = ep.ENC_DATE) "
				+ "    WHERE ep.ENC_DATE >= date_trunc('day',CURRENT_TIMESTAMP) and ("
				+ "        ep.ENCASHMENT_TYPE = 2 and ep.RNK > COALESCE(ci_enc.CNT,0) "
				+ "     OR ep.ENCASHMENT_TYPE = 3 and ep.RNK > COALESCE(co_enc.CNT,0) "
				+ "     OR ep.ENCASHMENT_TYPE = 1 and ep.RNK > COALESCE(jt_enc.CNT,0) "
				+ "    )"
				+ "    GROUP BY ep.ATM_ID),"
				+ "cm_cashout_curr_act_rem as ("
				+ "	select st.atm_id, st.stat_date,st.encashment_id, st.cass_curr as CURR_CODE, sum(CASS_REMAINING*st.CASS_VALUE) as CURR_REMAINING "
				+ "	from t_cm_cashout_cass_stat st "
				+ "	join t_cm_atm_cassettes ac on "
				+ "        (ac.atm_id = st.atm_id "
				+ "        and ac.cass_number = st.cass_number "
				+ "        and ac.cass_type = 1 "
				+ "        and COALESCE(ac.cass_state,0) = 0) "
				+ "where "
				+ "    1=1 "
				+ "group by st.atm_id, st.stat_date,st.encashment_id, st.cass_curr), "

				+ "cm_cashin_r_curr_act_rem as ( "
				+ "	select st.atm_id, st.stat_date, st.cash_in_encashment_id,"
				+ "	st.cass_curr as CURR_CODE, sum(CASS_REMAINING*st.CASS_VALUE) as CURR_REMAINING "
				+ "	from t_cm_cashin_r_cass_stat st "
				+ "	join t_cm_atm_cassettes ac on "
				+ "        (ac.atm_id = st.atm_id "
				+ "        and ac.cass_number = st.cass_number "
				+ "        and ac.cass_type = 3 "
				+ "        and COALESCE(ac.cass_state,0) = 0) "
				+ "where "
				+ "    1=1 "
				+ "group by st.atm_id, st.stat_date, st.cash_in_encashment_id,st.cass_curr) "

				+ "select i.external_atm_id, st.atm_id,st.cash_out_encashment_id as CASH_OUT_ENC_ID, st.cash_out_stat_date as CASH_OUT_STAT_DATE, "
				+ "  st.cash_in_encashment_id as CASH_IN_ENC_ID, "
				+ "  ci.bills_remaining as  cash_in_bills_remaining,st.cash_in_initial as cash_in_bills_initial,st.cash_in_r_initial as cash_in_r_bills_initial, "
				+ "  cr.bills_remaining as  reject_bills_remaining,st.reject_initial as reject_bills_initial, "
				+ "  i.state,i.city,i.street,"
				+ "  cs_m.curr_remaining as main_curr_remaining,cs_s.curr_remaining as sec_curr_remaining,cs_s2.curr_remaining as sec2_curr_remaining, "
				+ "  csr_m.curr_remaining as main_curr_rec_remaining,csr_s.curr_remaining as sec_curr_rec_remaining,csr_s2.curr_remaining as sec2_curr_rec_remaining, "
				+ "  i.main_curr_code,i.secondary_curr_code,i.secondary2_curr_code,"
				+ "  ci_m.code_a3 as main_code_a3,ci_s.code_a3 as secondary_code_a3,ci_s2.code_a3 as secondary2_code_a3,"
				+ "  st.out_of_cash_out_date,st.out_of_cash_out_curr,st.out_of_cash_out_resp,"
				+ "  st.out_of_cash_in_date,st.out_of_cash_in_resp,"
				+ "  st.last_withdrawal_hours,st.last_addition_hours,"
				+ "  st.last_update as STAT_LOAD_DATE,"
				+ "  i.NAME as ATM_NAME, st.CURR_REMAINING_ALERT,"
				+ "  i.state||', '||i.city||', '||i.street as ADDRESS,"
				+ "  COALESCE(ep.EMERGENCY_ENCASHMENT, st.EMERGENCY_ENCASHMENT) as EMERGENCY_ENCASHMENT,"
				+ "  COALESCE(ep.date_forthcoming_encashment, st.date_forthcoming_encashment) as date_forthcoming_encashment, "
				+ "  COALESCE(ep.date_previous_encashment, st.date_previous_encashment) as date_previous_encashment, st.planned_enc_summ,"
				+ "  i.TYPE as ATM_TYPE,"
				+ "  COALESCE(ep.IS_APPROVED, st.IS_APPROVED) as IS_APPROVED, st.CASH_IN_CAPACITY, st.CASH_IN_STAT_DATE,"
				+ "  ceil(extract(epoch from date_trunc('day',ep.date_forthcoming_encashment)-date_trunc('day',CURRENT_TIMESTAMP))/86400) as DAYS_UNTIL_ENCASHMENT,"
				+ "  cass.cass_State as CASH_IN_STATE, st.AVG_TRANS_IN_DAY, st.AVG_TRANS_IN_HOUR,"
				+ "  i.EXTERNAL_ATM_ID, st.ATM_PROBLEM, st.ATM_STATE, i.model "
				+ "  from t_cm_atm_actual_state st "
				+ "    join t_cm_atm i on(i.atm_id = st.atm_id) "
				+ "    left outer join t_cm_cashin_stat ci on (ci.cash_in_encashment_id = st.cash_in_encashment_id AND ci.stat_date = st.cash_in_stat_date) "
				+ "    left outer join t_cm_reject_stat cr on (cr.encashment_id = st.cash_out_encashment_id AND cr.stat_date = st.cash_out_stat_date) "
				+ "    left outer join cm_cashout_curr_act_rem cs_m on (cs_m.encashment_id = st.cash_out_encashment_id AND cs_m.stat_date = st.cash_out_stat_date and cs_m.curr_code = i.main_curr_code) "
				+ "    left outer join cm_cashout_curr_act_rem cs_s on (cs_s.encashment_id = st.cash_out_encashment_id AND cs_s.stat_date = st.cash_out_stat_date and cs_s.curr_code = i.secondary_curr_code) "
				+ "    left outer join cm_cashout_curr_act_rem cs_s2 on (cs_s2.encashment_id = st.cash_out_encashment_id AND cs_s2.stat_date = st.cash_out_stat_date and cs_s2.curr_code = i.secondary2_curr_code) "
				+ "    left outer join cm_cashout_curr_act_rem cs_s3 on (cs_s3.encashment_id = st.cash_out_encashment_id AND cs_s3.stat_date = st.cash_out_stat_date and cs_s3.curr_code = i.secondary3_curr_code) "
				+ "    left outer join cm_cashin_r_curr_act_rem csr_m on (csr_m.cash_in_encashment_id = st.cash_in_encashment_id AND csr_m.stat_date = st.cash_in_stat_date and csr_m.curr_code = i.main_curr_code) "
				+ "    left outer join cm_cashin_r_curr_act_rem csr_s on (csr_s.cash_in_encashment_id = st.cash_in_encashment_id AND csr_s.stat_date = st.cash_in_stat_date and csr_s.curr_code = i.secondary_curr_code) "
				+ "    left outer join cm_cashin_r_curr_act_rem csr_s2 on (csr_s2.cash_in_encashment_id = st.cash_in_encashment_id AND csr_s2.stat_date = st.cash_in_stat_date and csr_s2.curr_code = i.secondary2_curr_code) "
				+ "    left outer join cm_cashin_r_curr_act_rem csr_s3 on (csr_s3.cash_in_encashment_id = st.cash_in_encashment_id AND csr_s3.stat_date = st.cash_in_stat_date and csr_s3.curr_code = i.secondary3_curr_code) "
				+ "    left outer join t_cm_curr ci_m on (ci_m.code_n3 = i.main_curr_code) "
				+ "    left outer join t_cm_curr ci_s on (ci_s.code_n3 = i.secondary_curr_code) "
				+ "    left outer join t_cm_curr ci_s2 on (ci_s2.code_n3 = i.secondary2_curr_code) "
				+ "    left outer join t_cm_curr ci_s3 on (ci_s3.code_n3 = i.secondary3_curr_code) "
				+ "    left outer join next_encashment ne on (ne.atm_id = st.atm_id) "
				+ "    left outer join t_cm_enc_plan ep on (ep.atm_id = ne.atm_id and ep.enc_plan_id = ne.next_enc_id) "
				+ "    left outer join t_cm_atm_cassettes cass on (cass.atm_id = st.atm_id and cass.cass_type = 2) "
				+ "order by st.atm_id";
	}
}