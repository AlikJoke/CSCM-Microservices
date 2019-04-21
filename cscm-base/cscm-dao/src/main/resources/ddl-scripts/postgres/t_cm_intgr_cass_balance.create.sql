CREATE TABLE t_cm_intgr_cass_balance (
    atm_id                         NUMERIC(9),
    cass_type                      NUMERIC(1),
    cass_number                    NUMERIC(1),
    cass_remaining_load            NUMERIC(5),
    cass_remaining_calc            NUMERIC(5) DEFAULT 0,
    balance_status                 NUMERIC(1,0) DEFAULT 0
);

CREATE UNIQUE INDEX I_CM_ICB_AID2CT2CN ON t_cm_intgr_cass_balance (
    atm_id                          ASC,
    CASS_TYPE,
    CASS_NUMBER
 );

COMMENT ON TABLE T_CM_INTGR_CASS_BALANCE IS 'This table contains current amounts of bills in ATM cassettes loaded from external system and CashManagement statistical tables.';
COMMENT ON COLUMN T_CM_INTGR_CASS_BALANCE.ATM_ID IS 'Unique ID identifying ATM';
COMMENT ON COLUMN T_CM_INTGR_CASS_BALANCE.CASS_NUMBER IS 'Cassette number';
COMMENT ON COLUMN T_CM_INTGR_CASS_BALANCE.CASS_REMAINING_CALC IS 'Current amount of bills in cassette taken from CashManagement statistical tables';
COMMENT ON COLUMN T_CM_INTGR_CASS_BALANCE.CASS_REMAINING_LOAD IS 'Current amount of bills in cassette loaded for check from external system';
COMMENT ON COLUMN T_CM_INTGR_CASS_BALANCE.CASS_TYPE IS 'Cassette type - cash-in, cash-out, recycling';
COMMENT ON COLUMN T_CM_INTGR_CASS_BALANCE.BALANCE_STATUS IS 'Flag indicating whether balance is trusted or not (1- balance is not trusted)';