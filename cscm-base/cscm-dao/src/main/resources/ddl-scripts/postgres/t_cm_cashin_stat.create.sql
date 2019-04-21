CREATE TABLE T_CM_CASHIN_STAT (
  	ATM_ID                 NUMERIC(9)             NOT NULL,
  	STAT_DATE              TIMESTAMP                   NOT NULL,
  	CASH_IN_ENCASHMENT_ID  NUMERIC(6),
  	BILLS_COUNT            NUMERIC(6)              NOT NULL,
  	BILLS_REMAINING        NUMERIC(5),
  	AVAIL_COEFF            NUMERIC(4,2)
);

COMMENT ON TABLE 	T_CM_CASHIN_STAT 						IS 'This table contains cash in statistics periods';
COMMENT ON COLUMN 	T_CM_CASHIN_STAT.ATM_ID 				IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN 	T_CM_CASHIN_STAT.STAT_DATE 				IS 'Beginning of statistics period';
COMMENT ON COLUMN 	T_CM_CASHIN_STAT.CASH_IN_ENCASHMENT_ID 	IS 'Internal Cash Management cash in replenishment identifier';
COMMENT ON COLUMN 	T_CM_CASHIN_STAT.BILLS_COUNT 			IS 'Count of bills loaded during the period';
COMMENT ON COLUMN 	T_CM_CASHIN_STAT.BILLS_REMAINING 		IS 'Volume of free space left in the end of period';
COMMENT ON COLUMN 	T_CM_CASHIN_STAT.AVAIL_COEFF 			IS 'Coefficient that indicates ATM availability for statistic period';

CREATE UNIQUE INDEX I_CM_CASHIN_STAT_AID2CEID2SD ON T_CM_CASHIN_STAT (ATM_ID, CASH_IN_ENCASHMENT_ID, STAT_DATE);