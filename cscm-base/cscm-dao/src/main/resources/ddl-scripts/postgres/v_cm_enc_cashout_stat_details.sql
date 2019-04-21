CREATE OR REPLACE VIEW v_cm_enc_cashout_stat_details (
   encashment_id,
   denom_value,
   denom_curr,
   denom_count,
   action_type )
AS
(
SELECT 
ENCASHMENT_ID,
CASS_VALUE as DENOM_VALUE,
CASS_CURR as DENOM_CURR,
sum(CASS_COUNT) as DENOM_COUNT,
ACTION_TYPE as ACTION_TYPE
FROM 
t_cm_enc_cashout_stat_details
GROUP BY 
ENCASHMENT_ID,
CASS_VALUE,
CASS_CURR,
ACTION_TYPE
);

COMMENT ON VIEW v_cm_enc_cashout_stat_details IS 'This table contains denomination details for loaded cash out replenishments.';

COMMENT ON COLUMN v_cm_enc_cashout_stat_details.action_type IS 'Action type (1-bills unload,2-bills upload)';

COMMENT ON COLUMN v_cm_enc_cashout_stat_details.denom_count IS 'Count of denomination';

COMMENT ON COLUMN v_cm_enc_cashout_stat_details.denom_curr IS 'Denomination currency';

COMMENT ON COLUMN v_cm_enc_cashout_stat_details.denom_value IS 'Denomination';

COMMENT ON COLUMN v_cm_enc_cashout_stat_details.encashment_id IS 'Internal Cash Management cash out replenishment identifier';
