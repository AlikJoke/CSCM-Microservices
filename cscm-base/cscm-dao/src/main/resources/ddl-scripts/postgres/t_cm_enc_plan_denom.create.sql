CREATE TABLE T_CM_ENC_PLAN_DENOM (
  	ENC_PLAN_ID  	NUMERIC(6)	NOT NULL,
  	DENOM_VALUE  	NUMERIC(6)	NOT NULL,
  	DENOM_COUNT  	NUMERIC(4)	NOT NULL,
  	DENOM_CURR   	NUMERIC(3)	NOT NULL
);

COMMENT ON TABLE 	T_CM_ENC_PLAN_DENOM 			IS 'This table contains list of denominations that would be loaded on planned replenishment. If there are two records with the same denomination for one replenishment - that means that count of this denomination was splitted into several cassettes as there were empty cassettes after forecasting.';
COMMENT ON COLUMN 	T_CM_ENC_PLAN_DENOM.ENC_PLAN_ID IS 'Planned replenishment ID';
COMMENT ON COLUMN 	T_CM_ENC_PLAN_DENOM.DENOM_VALUE IS 'Denomination';
COMMENT ON COLUMN 	T_CM_ENC_PLAN_DENOM.DENOM_COUNT IS 'Denomination count';
COMMENT ON COLUMN 	T_CM_ENC_PLAN_DENOM.DENOM_CURR 	IS 'Denomination currency';


