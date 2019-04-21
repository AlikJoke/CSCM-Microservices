CREATE OR REPLACE VIEW v_cm_curr_convert_rate (
   src_curr_code,
   dest_curr_code,
   cnvt_rate,
   multiple_flag,
   src_inst_id,
   dest_inst_id,
   CNVT_DATE)
AS
WITH last_rates as (
    SELECT MAX(CNVT_DATE) as RATE_DATE,SRC_CURR_CODE,DEST_CURR_CODE,SRC_INST_ID,DEST_INST_ID
    FROM T_CM_CURR_CONVERT_RATE cr
    group by SRC_CURR_CODE,DEST_CURR_CODE,SRC_INST_ID,DEST_INST_ID
)
SELECT
rates.SRC_CURR_CODE,
rates.DEST_CURR_CODE,
rates.CNVT_RATE,
rates.MULTIPLE_FLAG,
rates.SRC_INST_ID,
rates.DEST_INST_ID,
rates.CNVT_DATE
FROM last_rates
    join T_CM_CURR_CONVERT_RATE rates on
        (rates.CNVT_DATE = last_rates.RATE_DATE
        and rates.SRC_CURR_CODE= last_rates.SRC_CURR_CODE
        and rates.DEST_CURR_CODE= last_rates.DEST_CURR_CODE
        and rates.SRC_INST_ID = last_rates.SRC_INST_ID
        and rates.DEST_INST_ID = last_rates.DEST_INST_ID)
WHERE
1=1;
