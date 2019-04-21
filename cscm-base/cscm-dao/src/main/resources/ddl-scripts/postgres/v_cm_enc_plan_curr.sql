CREATE OR REPLACE VIEW v_cm_enc_plan_curr (
   enc_plan_id,
   main_curr_code,
   main_curr_summ,
   main_curr_avg_demand,
   main_curr_code_a3,
   secondary_curr_code,
   sec_curr_summ,
   sec_curr_avg_demand,
   sec_curr_code_a3,
   secondary2_curr_code,
   sec2_curr_summ,
   sec2_curr_avg_demand,
   sec2_curr_code_a3,
   secondary3_curr_code,
   sec3_curr_summ,
   sec3_curr_avg_demand,
   sec3_curr_code_a3 )
AS
SELECT enc.ENC_PLAN_ID,
ai.main_curr_code,  ENC_DET_m.CURR_SUMM as main_curr_summ, ENC_DET_m.CURR_AVG_DEMAND as main_curr_avg_demand,ci_m.code_a3 as MAIN_CURR_CODE_A3,
ai.secondary_curr_code , ENC_DET_s.CURR_SUMM as sec_curr_summ , ENC_DET_s.CURR_AVG_DEMAND as sec_curr_avg_demand,ci_s.code_a3 as SEC_CURR_CODE_A3,
ai.secondary2_curr_code, ENC_DET_s2.CURR_SUMM as sec2_curr_summ , ENC_DET_s2.CURR_AVG_DEMAND as sec2_curr_avg_demand,ci_s2.code_a3 as SEC2_CURR_CODE_A3,
ai.secondary3_curr_code, ENC_DET_s3.CURR_SUMM as sec3_curr_summ , ENC_DET_s3.CURR_AVG_DEMAND as sec3_curr_avg_demand,ci_s3.code_a3 as SEC3_CURR_CODE_A3
FROM t_cm_enc_plan enc
    join t_cm_atm ai on (enc.ATM_ID = ai.ATM_ID)
join t_cm_enc_plan_curr ENC_DET_m on (enc.ENC_PLAN_ID = enc_det_m.ENC_PLAN_ID and enc_det_m.curr_code = ai.main_curr_code)
left outer join t_cm_enc_plan_curr ENC_DET_s on (enc.ENC_PLAN_ID = enc_det_s.ENC_PLAN_ID and enc_det_s.curr_code = ai.secondary_curr_code)
left outer join t_cm_enc_plan_curr ENC_DET_s2 on (enc.ENC_PLAN_ID = enc_det_s2.ENC_PLAN_ID and enc_det_s2.curr_code = ai.secondary2_curr_code)
left outer join t_cm_enc_plan_curr ENC_DET_s3 on (enc.ENC_PLAN_ID = enc_det_s3.ENC_PLAN_ID and enc_det_s3.curr_code = ai.secondary3_curr_code)
join T_CM_CURR ci_m on(ai.main_curr_code = ci_m.code_n3)
left outer join T_CM_CURR ci_s on(ai.secondary_curr_code = ci_s.code_n3)
left outer join T_CM_CURR ci_s2 on(ai.secondary2_curr_code = ci_s2.code_n3)
left outer join T_CM_CURR ci_s3 on(ai.secondary3_curr_code = ci_s3.code_n3)
;

COMMENT ON VIEW v_cm_enc_plan_curr IS 'View for extended information about currencies of planned encashment. ';

COMMENT ON COLUMN v_cm_enc_plan_curr.enc_plan_id IS 'Planned replenishment ID';

COMMENT ON COLUMN v_cm_enc_plan_curr.main_curr_avg_demand IS 'Main currency average demand';

COMMENT ON COLUMN v_cm_enc_plan_curr.main_curr_code IS 'Main currency code';

COMMENT ON COLUMN v_cm_enc_plan_curr.main_curr_code_a3 IS 'Main currency letter code';

COMMENT ON COLUMN v_cm_enc_plan_curr.main_curr_summ IS 'Main currency summ';

COMMENT ON COLUMN v_cm_enc_plan_curr.sec_curr_avg_demand IS 'Secondary currency average demand';

COMMENT ON COLUMN v_cm_enc_plan_curr.sec_curr_code_a3 IS 'Secondary currency letter code';

COMMENT ON COLUMN v_cm_enc_plan_curr.sec_curr_summ IS 'Secondary currency summ';

COMMENT ON COLUMN v_cm_enc_plan_curr.sec2_curr_avg_demand IS 'Secondary(2) currency average demand';

COMMENT ON COLUMN v_cm_enc_plan_curr.sec2_curr_code_a3 IS 'Secondary(2) currency average demand';

COMMENT ON COLUMN v_cm_enc_plan_curr.sec2_curr_summ IS 'Secondary(2) currency summ';

COMMENT ON COLUMN v_cm_enc_plan_curr.secondary_curr_code IS 'Secondary currency code';

COMMENT ON COLUMN v_cm_enc_plan_curr.secondary2_curr_code IS 'Secondary(2) currency code';

COMMENT ON COLUMN v_cm_enc_plan_curr.sec3_curr_avg_demand IS 'Secondary(3) currency average demand';

COMMENT ON COLUMN v_cm_enc_plan_curr.sec3_curr_code_a3 IS 'Secondary(3) currency average demand';

COMMENT ON COLUMN v_cm_enc_plan_curr.sec3_curr_summ IS 'Secondary(3) currency summ';

COMMENT ON COLUMN v_cm_enc_plan_curr.secondary3_curr_code IS 'Secondary(3) currency code';