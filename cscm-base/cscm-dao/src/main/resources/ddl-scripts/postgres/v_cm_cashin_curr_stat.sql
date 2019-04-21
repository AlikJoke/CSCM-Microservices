CREATE OR REPLACE VIEW V_CM_CASHIN_CURR_STAT (
   ATM_ID,
   STAT_DATE,
   CURR_CODE,
   CURR_SUMM )
AS
select atm_Id, stat_date, curr_code, sum(CURR_SUMM) as CURR_SUMM
from
(
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
    group by atm_id, stat_date, cash_in_encashment_id, denom_curr
    union all
    select atm_id, stat_date,
        CASS_CURR as CURR_CODE,
        sum(CASS_COUNT_IN) as CURR_SUMM
    from t_cm_cashin_r_cass_stat
    where COALESCE(avail_coeff,1) > 0.8
    group by atm_id, stat_date, CASS_CURR
) cics
group by atm_id, stat_date, curr_code;
