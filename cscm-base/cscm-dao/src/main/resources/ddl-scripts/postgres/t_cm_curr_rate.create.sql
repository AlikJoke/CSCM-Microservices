CREATE TABLE T_CM_CURR_RATE (
  	CURR_CODE    NUMERIC(3) 													NOT NULL,
  	CURR_RATE    NUMERIC,
  	ATM_INST_ID  VARCHAR(4) 			  				 				NOT NULL,
  	RATE_DATE    TIMESTAMP
);

COMMENT ON TABLE 	T_CM_CURR_RATE 					IS 'This table contains refinancing rate for currency';
COMMENT ON COLUMN 	T_CM_CURR_RATE.CURR_CODE 		IS 'Numeric currency code';
COMMENT ON COLUMN 	T_CM_CURR_RATE.CURR_RATE 		IS 'Refinancing rate';
COMMENT ON COLUMN 	T_CM_CURR_RATE.ATM_INST_ID 		IS 'Unique ID identifying institute';
COMMENT ON COLUMN 	T_CM_CURR_RATE.RATE_DATE 		IS 'Rate date';


