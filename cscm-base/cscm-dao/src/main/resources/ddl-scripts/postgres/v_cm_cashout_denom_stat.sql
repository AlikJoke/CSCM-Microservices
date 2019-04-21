CREATE OR REPLACE VIEW v_cm_cashout_denom_stat (
   atm_id,
   stat_date,
   encashment_id,
   denom_value,
   denom_curr,
   denom_count,
   denom_trans_count,
   denom_remaining )
AS
(
SELECT 
ATM_ID,
STAT_DATE,
ENCASHMENT_ID,
CASS_VALUE as DENOM_VALUE,
CASS_CURR as DENOM_CURR,
sum(CASS_COUNT) as DENOM_COUNT,
sum(CASS_TRANS_COUNT) as DENOM_TRANS_COUNT,
sum(CASS_REMAINING) as DENOM_REMAINING
FROM 
t_cm_cashout_cass_stat
GROUP BY 
ATM_ID,
STAT_DATE,
ENCASHMENT_ID,
CASS_VALUE,
CASS_CURR
);

COMMENT ON VIEW v_cm_cashout_denom_stat IS 'This table contains statistics periods for cash out denominations.';

COMMENT ON COLUMN v_cm_cashout_denom_stat.atm_id IS 'Unique ID identifying the ATM';

COMMENT ON COLUMN v_cm_cashout_denom_stat.denom_count IS 'Count of bills unloaded during the period';

COMMENT ON COLUMN v_cm_cashout_denom_stat.denom_curr IS 'Denomination currency';

COMMENT ON COLUMN v_cm_cashout_denom_stat.denom_remaining IS 'Count of bills remaining in cassette in the end of period';

COMMENT ON COLUMN v_cm_cashout_denom_stat.denom_trans_count IS 'Count of transaction with denomination for the period';

COMMENT ON COLUMN v_cm_cashout_denom_stat.denom_value IS 'Denomination';

COMMENT ON COLUMN v_cm_cashout_denom_stat.encashment_id IS 'Internal Cash Management cash out replenishment identifier';

COMMENT ON COLUMN v_cm_cashout_denom_stat.stat_date IS 'Beginning of statistics period';
