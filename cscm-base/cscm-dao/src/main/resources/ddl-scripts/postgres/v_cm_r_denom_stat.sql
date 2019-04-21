CREATE OR REPLACE VIEW v_cm_r_denom_stat (
   ATM_ID, 
   ENCASHMENT_ID, 
   DENOM_CURR, 
   DENOM_VALUE, 
   DENOM_OUT, 
   DENOM_TRANS_COUNT, 
   CURR_TRANS_COUNT, 
   DENOM_IN, 
   ENC_DATE, 
   ENC_DATE_TO)
AS
WITH
denom_encashment AS (
    SELECT
        atm_id,
        ecs.encashment_id,
        ecs.enc_date,
        ecsd.cass_value AS denom_value,
        ecsd.cass_curr AS denom_curr,
        SUM(ecsd.cass_count) AS denom_count
    FROM t_cm_enc_cashout_stat ecs
        JOIN t_cm_enc_cashout_stat_details ecsd ON (ecsd.encashment_id = ecs.encashment_id)
    WHERE ecsd.action_type = 2 /*api_cm_aggr.CO_ENC_DET_LOADED*/
    GROUP BY atm_id,enc_date,ecs.encashment_id,cass_curr,cass_value
),
denom_takeoffs AS (
    SELECT
        atm_id,
        encashment_id,
        cass_curr as denom_curr,
        cass_value as denom_value,
        SUM(cass_count) AS denom_count,
        SUM(cass_trans_count) AS trans_count
    FROM T_CM_CASHOUT_CASS_STAT
    GROUP BY atm_id,encashment_id,cass_curr,cass_value
),
curr_takeoffs AS (
    SELECT
        atm_id,
        encashment_id,
        curr_code,
        SUM(curr_trans_count) AS curr_trans_count
    FROM T_CM_CASHOUT_CURR_STAT cstat
    GROUP BY atm_id,encashment_id,curr_code
)
SELECT
    dt.atm_id,
    dt.encashment_id,
    dt.denom_curr,
    dt.denom_value,
    dt.denom_count AS denom_out,
    dt.trans_count AS denom_trans_count,
    ct.curr_trans_count AS curr_trans_count,
    de.denom_count as denom_in,
    de.enc_date,
    co.enc_date as enc_date_to
FROM denom_takeoffs dt
   JOIN curr_takeoffs ct ON (ct.atm_id = dt.atm_id AND ct.encashment_id = dt.encashment_id AND ct.curr_Code = dt.denom_curr)
   join denom_encashment de on (dt.atm_id = de.atm_id and dt.encashment_id = de.encashment_id
            AND de.denom_curr = dt.denom_curr AND de.denom_value = dt.denom_value)
    left OUTER JOIN v_cm_cashout_stat_enc2enc e2e on (ct.encashment_id = e2e.encashment_id)
    left OUTER JOIN t_cm_enc_cashout_stat co on (co.atm_id = ct.atm_id and co.encashment_id = e2e.next_encashment_id);
