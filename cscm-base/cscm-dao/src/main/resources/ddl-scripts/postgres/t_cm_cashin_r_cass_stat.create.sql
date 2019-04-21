CREATE TABLE t_cm_cashin_r_cass_stat
    (atm_id                         NUMERIC(9) NOT NULL,
    stat_date                      TIMESTAMP NOT NULL,
    cash_in_encashment_id          NUMERIC(6),
    cass_number                    NUMERIC(2),
    cass_value                     NUMERIC(6) NOT NULL,
    cass_curr                      NUMERIC(3) NOT NULL,
    cass_count_in                  NUMERIC(6) NOT NULL,
    cass_trans_count_in            NUMERIC(4),
    cass_trans_count_out           NUMERIC(4),
    cass_remaining                 NUMERIC(10),
    avail_coeff                    NUMERIC(4,2),
    cass_count_out                 NUMERIC(6)
);

CREATE UNIQUE INDEX i_cm_cicassst_ai2ei2sd2cv2 ON t_cm_cashin_r_cass_stat
  (
    atm_id                          ASC,
    cash_in_encashment_id                   ASC,
    stat_date                       ASC,
    cass_value                      ASC,
    cass_curr                       ASC,
    cass_number                     ASC
  );

ALTER TABLE t_cm_cashin_r_cass_stat
ADD CONSTRAINT c_ci_cass_denom CHECK (CASS_VALUE > 0);

COMMENT ON TABLE T_CM_CASHIN_R_CASS_STAT IS 'This table contains statistics periods for cash in cassettes with recycling';
COMMENT ON COLUMN T_CM_CASHIN_R_CASS_STAT.ATM_ID IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN T_CM_CASHIN_R_CASS_STAT.AVAIL_COEFF IS 'Coefficient that indicates ATM availability for statistic period';
COMMENT ON COLUMN T_CM_CASHIN_R_CASS_STAT.CASH_IN_ENCASHMENT_ID IS 'Internal Cash Management cash in replenishment identifier';
COMMENT ON COLUMN T_CM_CASHIN_R_CASS_STAT.CASS_COUNT_IN IS 'Count of bills inserted during the period';
COMMENT ON COLUMN T_CM_CASHIN_R_CASS_STAT.CASS_COUNT_OUT IS 'Count of bills dispensed during the period';
COMMENT ON COLUMN T_CM_CASHIN_R_CASS_STAT.CASS_CURR IS 'Currency of bills in cassette';
COMMENT ON COLUMN T_CM_CASHIN_R_CASS_STAT.CASS_NUMBER IS 'Recycling cassette number';
COMMENT ON COLUMN T_CM_CASHIN_R_CASS_STAT.CASS_REMAINING IS 'Count of bills remaining in cassette in the end of period';
COMMENT ON COLUMN T_CM_CASHIN_R_CASS_STAT.CASS_TRANS_COUNT_IN IS 'Count of cash-in transactions with cassette for the period';
COMMENT ON COLUMN T_CM_CASHIN_R_CASS_STAT.CASS_TRANS_COUNT_OUT IS 'Count of cash-out transactions with cassette for the period';
COMMENT ON COLUMN T_CM_CASHIN_R_CASS_STAT.CASS_VALUE IS 'Denomination of bills in cassette';
COMMENT ON COLUMN T_CM_CASHIN_R_CASS_STAT.STAT_DATE IS 'Beginning of statistics period';