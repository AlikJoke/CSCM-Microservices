CREATE TABLE T_CM_ENC_COMPARE_STAT
    (ATM_ID                         NUMERIC(9) NOT NULL,
    STAT_DATE                      TIMESTAMP NOT NULL,
    CURR_CODE                      NUMERIC(3) NOT NULL,
    CO_CURR_SUMM                   NUMERIC(15) NOT NULL,
    CO_REMAINING_START_DAY         NUMERIC(15) DEFAULT 0,
    CO_REMAINING_END_DAY           NUMERIC(15) DEFAULT 0,
    CURR_LOADED_TO_ATM             NUMERIC(15) DEFAULT 0,
    CURR_LOADED_FROM_ATM           NUMERIC(15) DEFAULT 0,
    EMERGENCY_ENCASHMENT           NUMERIC(1) DEFAULT 0,
    FORECAST                       NUMERIC(1) DEFAULT 0,
    CASH_ADD_ENCASHMENT            NUMERIC(1),
    CI_CURR_SUMM                   NUMERIC(15),
    CI_REMAINING_START_DAY         NUMERIC(15),
    CI_REMAINING_END_DAY           NUMERIC(15),
    CR_CURR_SUMM_IN                NUMERIC(15),
    CR_CURR_SUMM_OUT               NUMERIC(15),
    CR_REMAINING_START_DAY         NUMERIC(15),
    CR_REMAINING_END_DAY           NUMERIC(15)
);

CREATE UNIQUE INDEX I_CM_ENC_COMPARE_STAT_AI2SD2CC ON T_CM_ENC_COMPARE_STAT
  (
    ATM_ID                          ASC,
    STAT_DATE                       ASC,
    CURR_CODE                       ASC
);


COMMENT ON TABLE T_CM_ENC_COMPARE_STAT IS 'This table contains statistics that was forecasted for compare.';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.ATM_ID IS 'Unique ID identifying ATM';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.CASH_ADD_ENCASHMENT IS 'Flag indicating whether replenishment is with cash adding to cassettes';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.CI_CURR_SUMM IS 'Amount of currency inserted into cash-in cassettes ATM during the period';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.CI_REMAINING_END_DAY IS 'Amount of currency in cash-in cassettes of ATM at the end of the period';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.CI_REMAINING_START_DAY IS 'Amount of currency in cash-in cassettes of ATM at the beginnig of the period';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.CO_CURR_SUMM IS 'Amount of currency dispensed from cash-out cassettes of ATM during the period';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.CO_REMAINING_END_DAY IS 'Amount of currency in cash-out cassettes of ATM at the end of the period';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.CO_REMAINING_START_DAY IS 'Amount of currency in cash-out cassettes of ATM at the beginnig of the period';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.CR_CURR_SUMM_IN IS 'Amount of currency inserted into recycling cassettes ATM during the period';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.CR_CURR_SUMM_OUT IS 'Amount of currency dispensed from recycling  cassettes of ATM during the period';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.CR_REMAINING_END_DAY IS 'Amount of currency in recycling cassettes of ATM at the end of the period';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.CR_REMAINING_START_DAY IS 'Amount of currency in recycling cassettes of ATM at the beginnig of the period';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.CURR_CODE IS 'Currecny code';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.CURR_LOADED_FROM_ATM IS 'Amount of currency unloaded from ATM';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.CURR_LOADED_TO_ATM IS 'Amount of currency loaded to ATM';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.EMERGENCY_ENCASHMENT IS 'Flag indicating whether replenishment is emergency';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.FORECAST IS 'Flag indicating whether data is forecasted';
COMMENT ON COLUMN T_CM_ENC_COMPARE_STAT.STAT_DATE IS 'Period start date';
