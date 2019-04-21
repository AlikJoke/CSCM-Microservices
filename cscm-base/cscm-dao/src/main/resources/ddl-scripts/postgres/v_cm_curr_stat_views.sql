CREATE OR REPLACE VIEW V_CM_CURR_STAT_CASH_IN (
   ATM_ID,
   STAT_DATE,
   CURR_CODE,
   CURR_SUMM )
AS
select atm_Id, stat_date, denom_curr as curr_code,
    sum(DENOM_COUNT) as CURR_SUMM
    from t_cm_cashin_denom_stat cs
    where
        NOT EXISTS (
            select null
            from t_cm_cashin_stat csh
            where cs.atm_id = csh.atm_id
                and cs.stat_date = csh.stat_date
                and COALESCE(csh.avail_coeff,1) < 0.8
        )
    group by atm_id, stat_date, cash_in_encashment_id, denom_curr;

CREATE OR REPLACE VIEW V_CM_CURR_STAT_CASH_OUT (
   ATM_ID,
   STAT_DATE,
   CURR_CODE,
   CURR_SUMM )
AS
select atm_Id, stat_date, CASS_CURR as CURR_CODE, sum(CASS_COUNT*CASS_VALUE) as CURR_SUMM
from t_cm_cashout_cass_stat
where COALESCE(avail_coeff,1) > 0.8
group by atm_id, stat_date, cass_curr;

CREATE OR REPLACE VIEW V_CM_CURR_STAT_REC_IN (
   ATM_ID,
   STAT_DATE,
   CURR_CODE,
   CURR_SUMM )
AS
select atm_id, stat_date,
        CASS_CURR as CURR_CODE,
        sum(CASS_COUNT_IN*CASS_VALUE) as CURR_SUMM
    from t_cm_cashin_r_cass_stat
    where COALESCE(avail_coeff,1) > 0.8
    group by atm_id, stat_date, CASS_CURR;

CREATE OR REPLACE VIEW V_CM_CURR_STAT_REC_OUT (
   ATM_ID,
   STAT_DATE,
   CURR_CODE,
   CURR_SUMM )
AS
select atm_Id, stat_date, CASS_CURR, sum(CASS_COUNT_OUT*CASS_VALUE) as CURR_SUMM
from t_cm_cashin_r_cass_stat
where COALESCE(avail_coeff,1) > 0.8
group by atm_id, stat_date, CASS_CURR;