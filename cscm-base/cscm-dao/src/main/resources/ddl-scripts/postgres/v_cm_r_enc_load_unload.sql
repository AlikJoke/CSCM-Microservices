CREATE OR REPLACE VIEW V_CM_R_ENC_LOAD_UNLOAD (
   ATM_ID,
   GROUP_TYPE_ID,
   ATM_GROUP_ID,
   ADDRESS,
   ENC_DATE,
   CASS_CURR_LOADED,
   CASS_CURR_TAKEN,
   CASS_SUMM_LOADED,
   CASS_SUMM_TAKEN,
   EXTERNAL_ATM_ID )
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
enc_load_unload as(
SELECT
    ci.atm_id,
    ci.enc_date,
    ci.curr_code,
    --ct.stat_date,
    ci.denom_count as loaded,
    co.denom_count AS unloaded
FROM curr_encashment_in ci
    left OUTER JOIN curr_encashment_out co on (co.atm_id = ci.atm_id and co.encashment_id = ci.encashment_id
            AND ci.curr_code = co.curr_code)
)
select a.ATM_ID,
    ag.TYPE_ID as group_type_id,
    ag.id as atm_group_id,
    street ||', '||city||', '||state as ADDRESS,
    ecs.ENC_DATE,
    ecs.CURR_CODE as CASS_CURR_LOADED,
    ecs.CURR_CODE as CASS_CURR_TAKEn,
    ecs.LOADED as CASS_SUMM_LOADED,
    ecs.UNLOADED as CASS_SUMM_TAKEN,
    a.EXTERNAL_ATM_ID
from t_cm_atm2atm_group a2ag
    join t_cm_atm a on (a2ag.ATM_ID = a.ATM_ID)
    join t_cm_atm_Group ag on (a2ag.ATM_GROUP_ID = ag.ID)
    join enc_load_unload ecs on (ecs.atm_id = a2ag.atm_id)
;
