CREATE TABLE t_cm_cashin_denom_stat
    (atm_id                         NUMERIC(9) NOT NULL,
    stat_date                      TIMESTAMP NOT NULL,
    cash_in_encashment_id          NUMERIC(6),
    denom_curr                     NUMERIC(3) NOT NULL,
    denom_count                    NUMERIC(5),
    denom_value                    NUMERIC(6)
);

COMMENT ON TABLE T_CM_CASHIN_DENOM_STAT IS 'This table contains cash in statistics periods';
COMMENT ON COLUMN T_CM_CASHIN_DENOM_STAT.ATM_ID IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN T_CM_CASHIN_DENOM_STAT.CASH_IN_ENCASHMENT_ID IS 'Internal Cash Management cash in replenishment identifier';
COMMENT ON COLUMN T_CM_CASHIN_DENOM_STAT.DENOM_COUNT IS 'Count of bills inserted during the period';
COMMENT ON COLUMN T_CM_CASHIN_DENOM_STAT.DENOM_CURR IS 'Currecny code';
COMMENT ON COLUMN T_CM_CASHIN_DENOM_STAT.DENOM_VALUE IS 'Bills denomination';
COMMENT ON COLUMN T_CM_CASHIN_DENOM_STAT.STAT_DATE IS 'Beginning of statistics period';

CREATE UNIQUE INDEX I_CM_CIDS_AISDCIED ON T_CM_CASHIN_DENOM_STAT
(
    ATM_ID                          ASC,
    STAT_DATE                       ASC,
    CASH_IN_ENCASHMENT_ID           ASC,
    DENOM_CURR                      ASC,
    DENOM_VALUE                     ASC
);