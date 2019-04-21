CREATE OR REPLACE VIEW V_CM_CASHOUT_CURR_STAT (
   ATM_ID,
   STAT_DATE,
   CURR_CODE,
   CURR_SUMM )
AS
select atm_Id, stat_date, curr_code, sum(CASS_COUNT*CASS_VALUE) as CURR_SUMM
from
(
    select atm_id, stat_date,
        CASS_CURR as CURR_CODE,
        CASS_COUNT, CASS_VALUE
    from t_cm_cashout_cass_stat
    where COALESCE(avail_coeff,1) > 0.8
    union all
    select atm_id, stat_date,
        CASS_CURR as CURR_CODE,
        CASS_COUNT_OUT as CASS_COUNT, CASS_VALUE
    from t_cm_cashin_r_cass_stat
    where COALESCE(avail_coeff,1) > 0.8
) cocs
group by atm_id, stat_date, curr_code;
