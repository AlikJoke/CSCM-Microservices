CREATE TABLE T_CM_INTGR_TRANS (
    ATM_ID            				NUMERIC(9),
    UTRNNO               			NUMERIC(20),
    DATETIME               			TIMESTAMP,
    TRANS_TYPE_IND               	NUMERIC(2),
    AMOUNT               			NUMERIC(12),
    BILL_RETRACT               		NUMERIC(4),
    BILL_REJECT               		NUMERIC(4),
    BILL_CASH_IN               		NUMERIC(4),
    BILL_CASS1               		NUMERIC(6),
    DENOM_CASS1               		NUMERIC(6),
    CURRENCY_CASS1               	NUMERIC(3),
    TYPE_CASS1	                    NUMERIC(2),
    BILL_CASS2               		NUMERIC(6),
    DENOM_CASS2               		NUMERIC(6),
    CURRENCY_CASS2               	NUMERIC(3),
    TYPE_CASS2	                    NUMERIC(2),
    BILL_CASS3               		NUMERIC(6),
    DENOM_CASS3               		NUMERIC(6),
    CURRENCY_CASS3               	NUMERIC(3),
    TYPE_CASS3	                    NUMERIC(2),
    BILL_CASS4               		NUMERIC(6),
    DENOM_CASS4               		NUMERIC(6),
    CURRENCY_CASS4             		NUMERIC(3),
    TYPE_CASS4	                    NUMERIC(2)
);

CREATE UNIQUE INDEX i_cm_intgr_trans_idx ON t_cm_intgr_trans (
    atm_id                          ASC,
    datetime                        ASC,
    trans_type_ind                  ASC,
    utrnno                          ASC
);

COMMENT ON TABLE 	T_CM_INTGR_TRANS 					IS 'This table contains transactions loaded from external system. It is used only for integration purpose.';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.ATM_ID				IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.UTRNNO	 			IS 'Transaction ID';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.DATETIME	 		IS 'Transaction time';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.TRANS_TYPE_IND 	IS 'Transaction type ID';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.AMOUNT	 			IS 'Transaction amount';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.BILL_RETRACT	 	IS 'Number of bills dropped to retract';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.BILL_REJECT	 	IS 'Number of bills dropped to reject';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.BILL_CASH_IN	 	IS 'Number of bills loaded to cash-in';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.BILL_CASS1	 		IS 'Number of bills dispensed from cassette 1';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.DENOM_CASS1	 	IS 'Denomination of cassette 1';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.CURRENCY_CASS1	 	IS 'Currency of cassete 1';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.BILL_CASS2	 		IS 'Number of bills dispensed from cassette 2';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.DENOM_CASS2	 	IS 'Denomination of cassette 2';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.CURRENCY_CASS2	 	IS 'Currency of cassete 2';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.BILL_CASS3	 		IS 'Number of bills dispensed from cassette 3';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.DENOM_CASS3	 	IS 'Denomination of cassette 3';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.CURRENCY_CASS3	 	IS 'Currency of cassete 3';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.BILL_CASS4	 		IS 'Number of bills dispensed from cassette 4';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.DENOM_CASS4	 	IS 'Denomination of cassette 4';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.CURRENCY_CASS4	 	IS 'Currency of cassete 4';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.TYPE_CASS1 		IS 'Type of cassette 1';
COMMENT ON COLUMN	T_CM_INTGR_TRANS.TYPE_CASS2 		IS 'Type of cassette 2';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.TYPE_CASS3 		IS 'Type of cassette 3';
COMMENT ON COLUMN 	T_CM_INTGR_TRANS.TYPE_CASS4 		IS 'Type of cassette 4';

