CREATE OR REPLACE VIEW v_cm_atm_curr (
   atm_id,
   main_curr_code,
   main_curr_rate,
   secondary_curr_code,
   sec_curr_rate,
   secondary2_curr_code,
   sec2_curr_rate,
   secondary3_curr_code,
   sec3_curr_rate
   )
AS
(WITH cr as (
    SELECT MAX(RATE_DATE) as RATE_DATE,CURR_CODE,ATM_INST_ID
    FROM T_CM_CURR_RATE cr
    group by CURR_CODE,ATM_INST_ID
)
SELECT ATM_ID,ai.MAIN_CURR_CODE,COALESCE(cr_m.CURR_RATE,0) as MAIN_CURR_RATE,
COALESCE(ai.SECONDARY_CURR_CODE,0),COALESCE(cr_s.CURR_RATE,0) as SEC_CURR_RATE,
COALESCE(ai.SECONDARY2_CURR_CODE,0),COALESCE(cr_s2.CURR_RATE,0) as SEC2_CURR_RATE,
COALESCE(ai.SECONDARY3_CURR_CODE,0),COALESCE(cr_s3.CURR_RATE,0) as SEC3_CURR_RATE
FROM T_CM_ATM ai
left outer join cr cr1 on(ai.INST_ID = cr1.ATM_INST_ID and cr1.curr_code=ai.main_curr_code)
left outer join cr cr2 on(ai.INST_ID = cr2.ATM_INST_ID and cr2.curr_code=ai.secondary_curr_code)
left outer join cr cr3 on(ai.INST_ID = cr3.ATM_INST_ID and cr3.curr_code=ai.secondary2_curr_code)
left outer join cr cr4 on(ai.INST_ID = cr4.ATM_INST_ID and cr4.curr_code=ai.secondary3_curr_code)
join T_CM_CURR_RATE cr_m on (cr1.RATE_DATE = cr_m.RATE_DATE and cr1.CURR_CODE=cr_m.CURR_CODE and cr1.ATM_INST_ID = cr_m.ATM_INST_ID
    and cr_m.curr_code=ai.main_curr_code)
left outer join T_CM_CURR_RATE cr_s on (cr2.RATE_DATE = cr_s.RATE_DATE and cr2.CURR_CODE=cr_s.CURR_CODE and cr2.ATM_INST_ID = cr_s.ATM_INST_ID
    and cr_s.curr_code=ai.secondary_curr_code)
left outer join T_CM_CURR_RATE cr_s2 on (cr3.RATE_DATE = cr_s2.RATE_DATE and cr3.CURR_CODE=cr_s2.CURR_CODE and cr3.ATM_INST_ID = cr_s2.ATM_INST_ID
and cr_s2.curr_code=ai.secondary2_curr_code)
left outer join T_CM_CURR_RATE cr_s3 on (cr4.RATE_DATE = cr_s3.RATE_DATE and cr4.CURR_CODE=cr_s3.CURR_CODE and cr4.ATM_INST_ID = cr_s3.ATM_INST_ID
and cr_s3.curr_code=ai.secondary3_curr_code)
WHERE
1=1);

COMMENT ON VIEW v_cm_atm_curr IS 'View for information about refinancing rates for ATM currencies.';

COMMENT ON COLUMN v_cm_atm_curr.atm_id IS 'Unique ID identifying the ATM';

COMMENT ON COLUMN v_cm_atm_curr.main_curr_code IS 'Main currency code';

COMMENT ON COLUMN v_cm_atm_curr.main_curr_rate IS 'Refinancing rate for main currency';

COMMENT ON COLUMN v_cm_atm_curr.sec_curr_rate IS 'Refinancing rate for secondary currency';

COMMENT ON COLUMN v_cm_atm_curr.sec2_curr_rate IS 'Refinancing rate for secondary(2) currency';

COMMENT ON COLUMN v_cm_atm_curr.sec3_curr_rate IS 'Refinancing rate for secondary(3) currency';

COMMENT ON COLUMN v_cm_atm_curr.secondary_curr_code IS 'Secondary currency code';

COMMENT ON COLUMN v_cm_atm_curr.secondary2_curr_code IS 'Secondary(2) currency code';

COMMENT ON COLUMN v_cm_atm_curr.secondary3_curr_code IS 'Secondary(3) currency code';