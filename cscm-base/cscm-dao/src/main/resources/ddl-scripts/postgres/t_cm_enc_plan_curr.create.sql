CREATE TABLE T_CM_ENC_PLAN_CURR (
  	ENC_PLAN_ID      	NUMERIC(6)	NOT NULL,
  	CURR_CODE        	NUMERIC(3) 	NOT NULL,
  	CURR_SUMM        	NUMERIC(15)	NOT NULL,
  	CURR_AVG_DEMAND  	NUMERIC(15)	NOT NULL
);

COMMENT ON TABLE 	T_CM_ENC_PLAN_CURR 					IS 'This table contains list of currencies that would be loaded on planned replenishment';
COMMENT ON COLUMN 	T_CM_ENC_PLAN_CURR.ENC_PLAN_ID 		IS 'Planned replenishment ID';
COMMENT ON COLUMN 	T_CM_ENC_PLAN_CURR.CURR_CODE 		IS 'Currency code';
COMMENT ON COLUMN 	T_CM_ENC_PLAN_CURR.CURR_SUMM 		IS 'Currency summ';
COMMENT ON COLUMN 	T_CM_ENC_PLAN_CURR.CURR_AVG_DEMAND 	IS 'Currency average demand';


