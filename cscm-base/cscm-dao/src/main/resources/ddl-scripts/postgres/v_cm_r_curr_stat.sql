CREATE OR REPLACE VIEW v_cm_r_curr_stat (
   atm_id,
   date_from,
   date_to,
   curr_summ,
   curr_trans_count,
   curr_code,
   encashment_id,
   stat_date,
   left_before_cash_add,
   loaded,
   unloaded )
AS
WITH
--loaded
curr_encashment_in AS (
    SELECT
        atm_id,
        ecs.encashment_id,
        ecs.enc_date,
        ecsd.cass_curr AS curr_code,
        SUM(ecsd.cass_count*ecsd.cass_value*(case ecsd.action_type when 2/*api_cm_aggr.CO_ENC_DET_LOADED*/ then 0 else 1 END)) AS denom_left_before_cash_add,
        SUM(ecsd.cass_count*ecsd.cass_value*(case ecsd.action_type when 4/*api_cm_aggr.CO_ENC_DET_NOT_UNLOADED_CA*/ then 0 else 1 END)) AS denom_count
    FROM t_cm_enc_cashout_stat ecs
        JOIN t_cm_enc_cashout_stat_details ecsd ON (ecsd.encashment_id = ecs.encashment_id)
    WHERE ecsd.action_type in(2,4/*api_cm_aggr.CO_ENC_DET_LOADED,api_cm_aggr.CO_ENC_DET_NOT_UNLOADED_CA*/)
    GROUP BY atm_id,enc_date,ecs.encashment_id,cass_curr
),
--reamins
curr_encashment_out AS (
    SELECT
        atm_id,
        ecs.encashment_id,
        ecs.enc_date,
        ecsd.cass_curr AS curr_code,
        SUM(ecsd.cass_count*ecsd.cass_value) AS denom_count
    FROM t_cm_enc_cashout_stat ecs
        JOIN t_cm_enc_cashout_stat_details ecsd ON (ecsd.encashment_id = ecs.encashment_id)
    WHERE ecsd.action_type in(1,3,4/*api_cm_aggr.CO_ENC_DET_UNLOADED,api_cm_aggr.CO_ENC_DET_NOT_UNLOADED,api_cm_aggr.CO_ENC_DET_NOT_UNLOADED_CA*/)
    GROUP BY atm_id,enc_date,ecs.encashment_id,cass_curr
),
curr_takeoffs AS (
    SELECT
        atm_id,
        stat_date,
        encashment_id,
        curr_code,
        SUM(curr_summ) AS curr_summ,
        SUM(curr_trans_Count) AS curr_trans_Count
    FROM T_CM_CASHOUT_CURR_STAT cstat
    GROUP BY atm_id,stat_date,encashment_id,curr_code
)
SELECT
    ct.atm_id,
    ci.enc_date as date_from,
    co.enc_date as date_to,
    ct.curr_summ as curr_summ,
    ct.curr_trans_count as curr_trans_count,
    ct.curr_code,
    ct.encashment_id,
    ct.stat_date,
    ci.denom_left_before_cash_add as left_before_cash_add,
    ci.denom_count as loaded,
    co.denom_count AS unloaded
FROM curr_takeoffs ct
    JOIN curr_encashment_in ci on (ci.atm_id = ct.atm_id and ci.encashment_id = ct.encashment_id
            AND ci.curr_code = ct.curr_code)
    left OUTER JOIN v_cm_cashout_stat_enc2enc e2e on (ct.encashment_id = e2e.encashment_id)
    left OUTER JOIN curr_encashment_out co on (co.atm_id = ct.atm_id and co.encashment_id = e2e.next_encashment_id
            AND co.curr_code = ct.curr_code);