CREATE TABLE t_cm_intgr_trans_cash_in
    (atm_id                        NUMERIC(9),
    utrnno                         NUMERIC(20),
    datetime                       TIMESTAMP,
    trans_type_ind                 NUMERIC(2),
    bill_denom                     NUMERIC(6),
    bill_curr                      NUMERIC(3),
    bill_num                       NUMERIC(4)
);

CREATE UNIQUE INDEX I_CM_ITCI_IDX ON t_cm_intgr_trans_cash_in
  (
    atm_id                          ASC,
    datetime                        ASC,
    utrnno                          ASC,
    bill_denom                      ASC,
    bill_curr                       ASC
  );
  
COMMENT ON TABLE T_CM_INTGR_TRANS_CASH_IN IS 'This table contains cash in denominations transactions loaded from external system. It is used only for integration purpose.';
COMMENT ON COLUMN T_CM_INTGR_TRANS_CASH_IN.ATM_ID IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN T_CM_INTGR_TRANS_CASH_IN.BILL_CURR IS 'Bills currency';
COMMENT ON COLUMN T_CM_INTGR_TRANS_CASH_IN.BILL_DENOM IS 'Bills denomination';
COMMENT ON COLUMN T_CM_INTGR_TRANS_CASH_IN.BILL_NUM IS 'Number of bills';
COMMENT ON COLUMN T_CM_INTGR_TRANS_CASH_IN.DATETIME IS 'Transaction time';
COMMENT ON COLUMN T_CM_INTGR_TRANS_CASH_IN.TRANS_TYPE_IND IS 'Transaction type ID';
COMMENT ON COLUMN T_CM_INTGR_TRANS_CASH_IN.UTRNNO IS 'Transaction ID';
