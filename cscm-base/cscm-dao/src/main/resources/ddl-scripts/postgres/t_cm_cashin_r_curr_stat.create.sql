CREATE TABLE t_cm_cashin_r_curr_stat
    (atm_id                        NUMERIC(9) NOT NULL,
    stat_date                      TIMESTAMP NOT NULL,
    cash_in_encashment_id          NUMERIC(6),
    curr_code                      NUMERIC(3) NOT NULL,
    curr_summ_in                   NUMERIC(15) NOT NULL,
    curr_summ_out                  NUMERIC(15) NOT NULL,
    curr_trans_count_in            NUMERIC(6),
    curr_trans_count_out           NUMERIC(6),
    curr_remaining                 NUMERIC(15)
);

CREATE UNIQUE INDEX i_cm_circurrst_ai2sd2ei2cc ON t_cm_cashin_r_curr_stat
  (
    atm_id                          ASC,
    stat_date                       ASC,
    cash_in_encashment_id           ASC,
    curr_code                       ASC
  );


COMMENT ON TABLE T_CM_CASHIN_R_CURR_STAT IS 'This table contains statistics periods for cash out currencies';
COMMENT ON COLUMN T_CM_CASHIN_R_CURR_STAT.ATM_ID IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN T_CM_CASHIN_R_CURR_STAT.CASH_IN_ENCASHMENT_ID IS 'Internal Cash Management cash in replenishment identifier';
COMMENT ON COLUMN T_CM_CASHIN_R_CURR_STAT.CURR_CODE IS 'Currency code';
COMMENT ON COLUMN T_CM_CASHIN_R_CURR_STAT.CURR_REMAINING IS 'Amount of currency remaining in the end of period';
COMMENT ON COLUMN T_CM_CASHIN_R_CURR_STAT.CURR_SUMM_IN IS 'Amount of currency inserted during the period';
COMMENT ON COLUMN T_CM_CASHIN_R_CURR_STAT.CURR_SUMM_OUT IS 'Amount of currency dispensed during the period';
COMMENT ON COLUMN T_CM_CASHIN_R_CURR_STAT.CURR_TRANS_COUNT_IN IS 'Count of cash-in transactions for period';
COMMENT ON COLUMN T_CM_CASHIN_R_CURR_STAT.CURR_TRANS_COUNT_OUT IS 'Count of cash-out transactions for period';
COMMENT ON COLUMN T_CM_CASHIN_R_CURR_STAT.STAT_DATE IS 'Beginning of statistics period';
