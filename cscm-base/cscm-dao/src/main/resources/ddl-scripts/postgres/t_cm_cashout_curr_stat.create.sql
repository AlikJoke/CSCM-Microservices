CREATE TABLE T_CM_CASHOUT_CURR_STAT (
  	ATM_ID            NUMERIC(9)            NOT NULL,
  	STAT_DATE         TIMESTAMP                        NOT NULL,
  	ENCASHMENT_ID     NUMERIC(6),
  	CURR_CODE         NUMERIC(3)                   NOT NULL,
  	CURR_SUMM         NUMERIC(15)                  NOT NULL,
  	CURR_TRANS_COUNT  NUMERIC(6),
  	CURR_REMAINING    NUMERIC(15)
);

COMMENT ON TABLE 	T_CM_CASHOUT_CURR_STAT 					IS 'This table contains statistics periods for cash out currencies';
COMMENT ON COLUMN 	T_CM_CASHOUT_CURR_STAT.ATM_ID 			IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN 	T_CM_CASHOUT_CURR_STAT.STAT_DATE 		IS 'Beginning of statistics period';
COMMENT ON COLUMN 	T_CM_CASHOUT_CURR_STAT.ENCASHMENT_ID 	IS 'Internal Cash Management cash out replenishment identifier';
COMMENT ON COLUMN 	T_CM_CASHOUT_CURR_STAT.CURR_CODE 		IS 'Currency code';
COMMENT ON COLUMN 	T_CM_CASHOUT_CURR_STAT.CURR_SUMM 		IS 'Amount of currency unloaded during the period';
COMMENT ON COLUMN 	T_CM_CASHOUT_CURR_STAT.CURR_TRANS_COUNT IS 'Count of transactions for period';
COMMENT ON COLUMN 	T_CM_CASHOUT_CURR_STAT.CURR_REMAINING 	IS 'Amount of currency remaining in the end of period';

CREATE UNIQUE INDEX I_CM_COCURRST_AI2SD2EI2CC ON T_CM_CASHOUT_CURR_STAT (ATM_ID, STAT_DATE, ENCASHMENT_ID, CURR_CODE);