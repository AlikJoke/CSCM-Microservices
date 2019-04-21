CREATE OR REPLACE VIEW V_CM_R_CASHIN_R_DENOM_STAT (
   ATM_ID,
   CASH_IN_ENCASHMENT_ID,
   DENOM_CURR,
   DENOM_VALUE,
   DENOM_COUNT_IN,
   DENOM_COUNT_OUT,
   DENOM_TRANS_COUNT_IN,
   DENOM_TRANS_COUNT_OUT,
   CURR_TRANS_COUNT_IN,
   CURR_TRANS_COUNT_OUT,
   ENC_DATE,
   ENC_DATE_TO,
   LOADED )
AS
WITH
denom_encashment AS (
    SELECT
        atm_id,
        ecs.cash_in_encashment_id,
        ecs.cash_in_enc_date,
        ecsd.cass_value AS denom_value,
        ecsd.cass_curr AS denom_curr,
        SUM(ecsd.cass_count*(case ecsd.action_type when 2 then 1 else 0 end/*api_cm_aggr.CO_ENC_DET_LOADED*/)) AS denom_count
    FROM t_cm_enc_cashin_stat ecs
        LEFT OUTER JOIN t_cm_enc_cashin_stat_details ecsd ON (ecsd.cash_in_encashment_id = ecs.cash_in_encashment_id)
    GROUP BY atm_id,cash_in_enc_date,ecs.cash_in_encashment_id,cass_curr,cass_value
),
denom_takeoffs AS (
    SELECT
        atm_id,
        cash_in_encashment_id,
        cass_curr as denom_curr,
        cass_value as denom_value,
        SUM(cass_count_in) AS denom_count_in,
        SUM(cass_count_out) AS denom_count_out,
        SUM(cass_trans_count_in) AS trans_count_in,
        SUM(cass_trans_count_out) AS trans_count_out
    FROM T_CM_CASHIN_R_CASS_STAT
    GROUP BY atm_id,cash_in_encashment_id,cass_curr,cass_value
),
curr_takeoffs AS (
    SELECT
        atm_id,
        cash_in_encashment_id,
        curr_code,
        SUM(curr_trans_count_in) AS curr_trans_count_in,
        SUM(curr_trans_count_out) AS curr_trans_count_out
    FROM T_CM_CASHIN_R_CURR_STAT cstat
    GROUP BY atm_id,cash_in_encashment_id,curr_code
)
SELECT
    dt.atm_id,
    dt.cash_in_encashment_id,
    dt.denom_curr,
    dt.denom_value,
    dt.denom_count_in AS denom_in,
    dt.denom_count_out AS denom_out,
    dt.trans_count_in AS denom_trans_count_in,
    dt.trans_count_out AS denom_trans_count_out,
    ct.curr_trans_count_in AS curr_trans_count_in,
    ct.curr_trans_count_out AS curr_trans_count_out,
    ci.cash_in_enc_date as enc_date_from,
    co.cash_in_enc_date as enc_date_to,
    ci.denom_count as loaded
FROM denom_takeoffs dt
   JOIN curr_takeoffs ct ON (ct.atm_id = dt.atm_id AND ct.cash_in_encashment_id = dt.cash_in_encashment_id AND ct.curr_Code = dt.denom_curr)
 --  JOIN t_cm_enc_cashin_stat ci on (ci.atm_id = ct.atm_id and ci.cash_in_encashment_id = ct.cash_in_encashment_id)
   join denom_encashment ci on (dt.atm_id = ci.atm_id and dt.cash_in_encashment_id = ci.cash_in_encashment_id
            AND COALESCE(ci.denom_curr,dt.denom_curr) = dt.denom_curr AND COALESCE(ci.denom_value,dt.denom_value) = dt.denom_value)
    left OUTER JOIN v_cm_cashin_stat_enc2enc e2e on (ct.cash_in_encashment_id = e2e.cash_in_encashment_id)
    left OUTER JOIN t_cm_enc_cashin_stat co on (co.atm_id = ct.atm_id and co.cash_in_encashment_id = e2e.next_cash_in_encashment_id)
;
