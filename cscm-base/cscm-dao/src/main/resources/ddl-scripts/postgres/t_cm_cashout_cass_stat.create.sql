CREATE TABLE T_CM_CASHOUT_CASS_STAT (
  	ATM_ID            NUMERIC(9)            NOT NULL,
  	STAT_DATE         TIMESTAMP                        NOT NULL,
  	ENCASHMENT_ID     NUMERIC(6),
  	CASS_NUMBER       NUMERIC(2),
  	CASS_VALUE        NUMERIC(6)                   NOT NULL,
  	CASS_CURR         NUMERIC(3)                   NOT NULL,
  	CASS_COUNT        NUMERIC(6)                   NOT NULL,
  	CASS_TRANS_COUNT  NUMERIC(4)                   NOT NULL,
  	CASS_REMAINING    NUMERIC(10),
  	AVAIL_COEFF       NUMERIC(4,2),
  	CASS_CURR_DEMAND       NUMERIC(9)  NOT NULL,
  	CASS_CURR_EXHAUSTION_DATE TIMESTAMP  NOT NULL
);

ALTER TABLE t_cm_cashout_cass_stat ADD CONSTRAINT c_co_cass_denom CHECK (CASS_VALUE > 0);

COMMENT ON TABLE 	T_CM_CASHOUT_CASS_STAT 					IS 'This table contains statistics periods for cash out cassettes';
COMMENT ON COLUMN 	T_CM_CASHOUT_CASS_STAT.ATM_ID 			IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN 	T_CM_CASHOUT_CASS_STAT.STAT_DATE 		IS 'Beginning of statistics period';
COMMENT ON COLUMN 	T_CM_CASHOUT_CASS_STAT.ENCASHMENT_ID 	IS 'Internal Cash Management cash out replenishment identifier';
COMMENT ON COLUMN 	T_CM_CASHOUT_CASS_STAT.CASS_NUMBER 		IS 'Cash out cassette number';
COMMENT ON COLUMN 	T_CM_CASHOUT_CASS_STAT.CASS_VALUE 		IS 'Denomination of bills in cassette';
COMMENT ON COLUMN 	T_CM_CASHOUT_CASS_STAT.CASS_CURR 		IS 'Currency of bills in cassette';
COMMENT ON COLUMN 	T_CM_CASHOUT_CASS_STAT.CASS_COUNT 		IS 'Count of bills unloaded during the period';
COMMENT ON COLUMN 	T_CM_CASHOUT_CASS_STAT.CASS_TRANS_COUNT IS 'Count of transaction with cassette for the period';
COMMENT ON COLUMN 	T_CM_CASHOUT_CASS_STAT.CASS_REMAINING 	IS 'Count of bills remaining in cassette in the end of period';
COMMENT ON COLUMN 	T_CM_CASHOUT_CASS_STAT.AVAIL_COEFF 		IS 'Coefficient that indicates ATM availability for statistic period';

CREATE UNIQUE INDEX I_CM_COCASSST_AI2EI2SD2CV2 ON T_CM_CASHOUT_CASS_STAT (ATM_ID, ENCASHMENT_ID, STAT_DATE, CASS_VALUE, CASS_CURR, CASS_NUMBER);