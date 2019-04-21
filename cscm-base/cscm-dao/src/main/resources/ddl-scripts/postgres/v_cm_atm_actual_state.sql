CREATE OR REPLACE VIEW V_CM_ATM_ACTUAL_STATE (
   ATM_ID,
   CASH_OUT_ENC_ID,
   CASH_OUT_STAT_DATE,
   CASH_IN_ENC_ID,
   CASH_IN_BILLS_REMAINING,
   CASH_IN_BILLS_INITIAL,
   CASH_IN_R_BILLS_INITIAL,
   REJECT_BILLS_REMAINING,
   REJECT_BILLS_INITIAL,
   STATE,
   CITY,
   STREET,
   MAIN_CURR_REMAINING,
   SEC_CURR_REMAINING,
   SEC2_CURR_REMAINING,
   SEC3_CURR_REMAINING,
   MAIN_CURR_REC_REMAINING,
   SEC_CURR_REC_REMAINING,
   SEC2_CURR_REC_REMAINING,
   SEC3_CURR_REC_REMAINING,
   MAIN_CURR_CODE,
   SECONDARY_CURR_CODE,
   SECONDARY2_CURR_CODE,
   SECONDARY3_CURR_CODE,
   MAIN_CODE_A3,
   SECONDARY_CODE_A3,
   SECONDARY2_CODE_A3,
   SECONDARY3_CODE_A3,
   OUT_OF_CASH_OUT_DATE,
   OUT_OF_CASH_OUT_CURR,
   OUT_OF_CASH_OUT_RESP,
   OUT_OF_CASH_IN_DATE,
   OUT_OF_CASH_IN_RESP,
   LAST_WITHDRAWAL_HOURS,
   LAST_ADDITION_HOURS,
   STAT_LOAD_DATE,
   ATM_NAME,
   CURR_REMAINING_ALERT,
   ADDRESS,
   EMERGENCY_ENCASHMENT,
   DATE_FORTHCOMING_ENCASHMENT,
   ATM_TYPE,
   IS_APPROVED,
   DAYS_UNTIL_ENCASHMENT,
   CASH_IN_STATE,
   EXTERNAL_ATM_ID)
AS
with
ci_enc as (
    SELECT ATM_ID,count(1) as CNT, date_trunc('day',ecs.CASH_IN_ENC_DATE) as ENC_DATE
    FROM T_CM_ENC_CASHIN_STAT ecs
    WHERE ecs.CASH_IN_ENC_DATE >= date_trunc('day',CURRENT_TIMESTAMP)
    GROUP BY ATM_ID, date_trunc('day',ecs.CASH_IN_ENC_DATE)
),
co_enc as (
    SELECT ATM_ID,count(1) as CNT, date_trunc('day',ecs.ENC_DATE) as ENC_DATE
    FROM T_CM_ENC_CASHOUT_STAT ecs
    WHERE ecs.ENC_DATE >= date_trunc('day',CURRENT_TIMESTAMP)
    GROUP BY ATM_ID,date_trunc('day',ecs.ENC_DATE)
),
jt_enc as (
    SELECT ecso.ATM_ID,count(1) as CNT, date_trunc('day',ecso.ENC_DATE) as ENC_DATE
    FROM T_CM_ENC_CASHOUT_STAT ecso,T_CM_ENC_CASHIN_STAT ecsi
    WHERE ecso.ENC_DATE >= date_trunc('day',CURRENT_TIMESTAMP)
        AND abs(ceil(extract(epoch from ecso.ENC_DATE-ecsi.CASH_IN_ENC_DATE)/3600)) < 1
        AND ecso.ATM_ID = ecsi.ATM_ID
    GROUP BY ecso.ATM_ID, date_trunc('day',ecso.ENC_DATE)
),
plan_enc as (
    SELECT ENCASHMENT_TYPE,ATM_ID,ENC_PLAN_ID,date_trunc('day',DATE_FORTHCOMING_ENCASHMENT) as ENC_DATE ,
    row_number() over (partition by ATM_ID,ENCASHMENT_TYPE order by ENC_PLAN_ID) as RNK
    FROM T_CM_ENC_PLAN ep
    WHERE ep.DATE_FORTHCOMING_ENCASHMENT >= date_trunc('day',CURRENT_TIMESTAMP)
),
next_encashment as (
    SELECT ep.ATM_ID, MIN(ENC_PLAN_ID) as NEXT_ENC_ID
    FROM plan_enc ep
    left outer join ci_enc on (ci_enc.ATM_ID = ep.ATM_ID and ci_enc.ENC_DATE = ep.ENC_DATE)
    left outer join co_enc on (co_enc.ATM_ID = ep.ATM_ID and co_enc.ENC_DATE = ep.ENC_DATE)
    left outer join jt_enc on (jt_enc.ATM_ID = ep.ATM_ID and jt_enc.ENC_DATE = ep.ENC_DATE)
    WHERE ep.ENC_DATE >= date_trunc('day',CURRENT_TIMESTAMP) and (
        ep.ENCASHMENT_TYPE = 2 and ep.RNK > COALESCE(ci_enc.CNT,0)
     OR ep.ENCASHMENT_TYPE = 3 and ep.RNK > COALESCE(co_enc.CNT,0)
     OR ep.ENCASHMENT_TYPE = 1 and ep.RNK > COALESCE(jt_enc.CNT,0)
    )
    GROUP BY ep.ATM_ID)
select st.atm_id,st.cash_out_encashment_id as CASH_OUT_ENC_ID, st.cash_out_stat_date as CASH_OUT_STAT_DATE,
  st.cash_in_encashment_id as CASH_IN_ENC_ID,
  ci.bills_remaining as  cash_in_bills_remaining,st.cash_in_initial as cash_in_bills_initial,st.cash_in_r_initial as cash_in_r_bills_initial,
  cr.bills_remaining as  reject_bills_remaining,st.reject_initial as reject_bills_initial,
  i.state,i.city,i.street,
  cs_m.curr_remaining as main_curr_remaining,cs_s.curr_remaining as sec_curr_remaining,cs_s2.curr_remaining as sec2_curr_remaining,
  csr_m.curr_remaining as main_curr_rec_remaining,csr_s.curr_remaining as sec_curr_rec_remaining,csr_s2.curr_remaining as sec2_curr_rec_remaining,
  i.main_curr_code,i.secondary_curr_code,i.secondary2_curr_code,
  ci_m.code_a3 as main_code_a3,ci_s.code_a3 as secondary_code_a3,ci_s2.code_a3 as secondary2_code_a3,
  st.out_of_cash_out_date,st.out_of_cash_out_curr,st.out_of_cash_out_resp,
  st.out_of_cash_in_date,st.out_of_cash_in_resp,
  st.last_withdrawal_hours,st.last_addition_hours,
  st.last_update as STAT_LOAD_DATE,
  i.NAME as ATM_NAME, st.CURR_REMAINING_ALERT,
  i.state||', '||i.city||', '||i.street as ADDRESS,
  ep.EMERGENCY_ENCASHMENT,
  ep.date_forthcoming_encashment,
  i.TYPE as ATM_TYPE,
  ep.IS_APPROVED,
  ceil(extract(epoch from date_trunc('day',ep.date_forthcoming_encashment)-date_trunc('day',CURRENT_TIMESTAMP))/86400) as DAYS_UNTIL_ENCASHMENT,
  cass.cass_State as CASH_IN_STATE,
  i.EXTERNAL_ATM_ID
  from t_cm_atm_actual_state st
    join t_cm_atm i on(i.atm_id = st.atm_id)
    left outer join t_cm_cashin_stat ci on (ci.cash_in_encashment_id = st.cash_in_encashment_id AND ci.stat_date = st.cash_in_stat_date)
    left outer join t_cm_reject_stat cr on (cr.encashment_id = st.cash_out_encashment_id AND cr.stat_date = st.cash_out_stat_date)
    left outer join v_cm_cashout_curr_act_rem cs_m on (cs_m.encashment_id = st.cash_out_encashment_id AND cs_m.stat_date = st.cash_out_stat_date and cs_m.curr_code = i.main_curr_code)
    left outer join v_cm_cashout_curr_act_rem cs_s on (cs_s.encashment_id = st.cash_out_encashment_id AND cs_s.stat_date = st.cash_out_stat_date and cs_s.curr_code = i.secondary_curr_code)
    left outer join v_cm_cashout_curr_act_rem cs_s2 on (cs_s2.encashment_id = st.cash_out_encashment_id AND cs_s2.stat_date = st.cash_out_stat_date and cs_s2.curr_code = i.secondary2_curr_code)
    left outer join v_cm_cashout_curr_act_rem cs_s3 on (cs_s3.encashment_id = st.cash_out_encashment_id AND cs_s3.stat_date = st.cash_out_stat_date and cs_s3.curr_code = i.secondary3_curr_code)
    left outer join v_cm_cashin_r_curr_act_rem csr_m on (csr_m.cash_in_encashment_id = st.cash_in_encashment_id AND csr_m.stat_date = st.cash_in_stat_date and csr_m.curr_code = i.main_curr_code)
    left outer join v_cm_cashin_r_curr_act_rem csr_s on (csr_s.cash_in_encashment_id = st.cash_in_encashment_id AND csr_s.stat_date = st.cash_in_stat_date and csr_s.curr_code = i.secondary_curr_code)
    left outer join v_cm_cashin_r_curr_act_rem csr_s2 on (csr_s2.cash_in_encashment_id = st.cash_in_encashment_id AND csr_s2.stat_date = st.cash_in_stat_date and csr_s2.curr_code = i.secondary2_curr_code)
    left outer join v_cm_cashin_r_curr_act_rem csr_s3 on (csr_s3.cash_in_encashment_id = st.cash_in_encashment_id AND csr_s3.stat_date = st.cash_in_stat_date and csr_s3.curr_code = i.secondary3_curr_code)
    left outer join t_cm_curr ci_m on (ci_m.code_n3 = i.main_curr_code)
    left outer join t_cm_curr ci_s on (ci_s.code_n3 = i.secondary_curr_code)
    left outer join t_cm_curr ci_s2 on (ci_s2.code_n3 = i.secondary2_curr_code)
    left outer join t_cm_curr ci_s3 on (ci_s3.code_n3 = i.secondary3_curr_code)
    left outer join next_encashment ne on (ne.atm_id = st.atm_id)
    left outer join t_cm_enc_plan ep on (ep.atm_id = ne.atm_id and ep.enc_plan_id = ne.next_enc_id)
    left outer join t_cm_atm_cassettes cass on (cass.atm_id = st.atm_id and cass.cass_type = 2)
order by st.atm_id;

COMMENT ON VIEW V_CM_ATM_ACTUAL_STATE IS 'View for extended information of actual state of ATMs.';
