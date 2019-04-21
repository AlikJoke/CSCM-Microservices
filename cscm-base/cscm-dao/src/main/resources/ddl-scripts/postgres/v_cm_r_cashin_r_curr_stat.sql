CREATE OR REPLACE VIEW V_CM_R_CASHIN_R_CURR_STAT (
   ATM_ID,
   DATE_FROM,
   DATE_TO,
   CURR_SUMM_IN,
   CURR_TRANS_COUNT_IN,
   CURR_SUMM_OUT,
   CURR_TRANS_COUNT_OUT,
   CURR_CODE,
   CASH_IN_ENCASHMENT_ID,
   LOADED,
   UNLOADED )
AS
WITH
curr_encashment_in AS (
    SELECT
        atm_id,
        ecs.cash_in_encashment_id,
        ecs.cash_in_enc_date,
        ecsd.cass_curr AS curr_code,
        SUM(ecsd.cass_count*ecsd.cass_value*(case ecsd.action_type when 2 then 1 else 0 end)) AS denom_count
    FROM t_cm_enc_cashin_stat ecs
        LEFT OUTER JOIN t_cm_enc_cashin_stat_details ecsd ON (ecsd.cash_in_encashment_id = ecs.cash_in_encashment_id)
    GROUP BY atm_id,cash_in_enc_date,ecs.cash_in_encashment_id,cass_curr
),
--reamins
curr_encashment_out AS (
    SELECT
        atm_id,
        ecs.cash_in_encashment_id,
        ecs.cash_in_enc_date,
        ecsd.cass_curr AS curr_code,
        SUM(ecsd.cass_count*ecsd.cass_value*(case ecsd.action_type when 1 then 1 when 3 then 1 else 0 end)) AS denom_count
    FROM t_cm_enc_cashin_stat ecs
        LEFT OUTER JOIN t_cm_enc_cashin_stat_details ecsd ON (ecsd.cash_in_encashment_id = ecs.cash_in_encashment_id)
    GROUP BY atm_id,cash_in_enc_date,ecs.cash_in_encashment_id,cass_curr
),
curr_takeoffs AS (
    SELECT
        atm_id,
        --stat_date,
        cash_in_encashment_id,
        curr_code,
        SUM(curr_summ_in) AS curr_summ_in,
        SUM(curr_trans_count_in) AS curr_trans_count_in,
        SUM(curr_summ_out) AS curr_summ_out,
        SUM(curr_trans_count_out) AS curr_trans_count_out
    FROM T_CM_CASHIN_R_CURR_STAT cstat
    GROUP BY atm_id
    --,stat_date
    ,cash_in_encashment_id,curr_code
)
SELECT
    ct.atm_id,
    ci.cash_in_enc_date as date_from,
    co.cash_in_enc_date as date_to,
    ct.curr_summ_in as curr_summ_in,
    ct.curr_trans_count_in as curr_trans_count_in,
    ct.curr_summ_out as curr_summ_out,
    ct.curr_trans_count_out as curr_trans_count_out,
    ct.curr_code,
    ct.cash_in_encashment_id,
    --ct.stat_date,
    ci.denom_count as loaded,
    CASE WHEN COALESCE(co.denom_count,0) > 0 THEN co.denom_count else COALESCE(ci.denom_count,0)+ct.curr_summ_in - ct.curr_summ_out END AS unloaded
FROM curr_takeoffs ct
     JOIN curr_encashment_in ci on (ci.atm_id = ct.atm_id and ci.cash_in_encashment_id = ct.cash_in_encashment_id
            AND COALESCE(ci.curr_code,ct.curr_code) = ct.curr_code)
    left OUTER JOIN v_cm_cashin_stat_enc2enc e2e on (ct.cash_in_encashment_id = e2e.cash_in_encashment_id)
    left OUTER JOIN curr_encashment_out co on (co.atm_id = ct.atm_id and co.cash_in_encashment_id = e2e.next_cash_in_encashment_id
             AND COALESCE(co.curr_code,ct.curr_code) = ct.curr_code);
