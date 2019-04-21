CREATE TABLE T_CM_ENC_PERIOD_CURR (
  	ENC_PERIOD_ID  	NUMERIC(6)         	NOT NULL,
  	CURR_CODE_A3   	VARCHAR(3)  	NOT NULL,
  	CURR_SUMM      	VARCHAR(15) 	NOT NULL
);

COMMENT ON TABLE 	T_CM_ENC_PERIOD_CURR 				IS 'This table contains list of currencies that would be loaded on planned replenishment';
COMMENT ON COLUMN 	T_CM_ENC_PERIOD_CURR.ENC_PERIOD_ID	IS 'Planned replenishment ID';
COMMENT ON COLUMN 	T_CM_ENC_PERIOD_CURR.CURR_CODE_A3 	IS 'Currency code';
COMMENT ON COLUMN 	T_CM_ENC_PERIOD_CURR.CURR_SUMM 		IS 'Currency summ';