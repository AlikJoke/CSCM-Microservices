CREATE OR REPLACE VIEW v_cm_enc_plan_cass (
   atm_id,
   enc_date,
   cass_val,
   cass_bills,
   cass_type )
AS
SELECT enc.ATM_ID,enc.DATE_FORTHCOMING_ENCASHMENT as ENC_DATE,
    encDet.DENOM_VALUE as CASS_VAL,
    encDet.DENOM_COUNT as CASS_BILLS,
    encDet.DENOM_CURR as CASS_TYPE
FROM
    T_CM_ENC_PLAN enc
join T_CM_ENC_PLAN_DENOM encDet on (enc.ENC_PLAN_ID = encDet.ENC_PLAN_ID);