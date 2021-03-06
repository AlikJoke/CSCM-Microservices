CREATE TABLE T_CM_CURR_DENOM (
  	CURR_CODE  NUMERIC(4)	NOT NULL,
  	DENOM      NUMERIC(6)	NOT NULL
);

COMMENT ON TABLE 	T_CM_CURR_DENOM 			IS 'This table contains denominations for currency';
COMMENT ON COLUMN 	T_CM_CURR_DENOM.CURR_CODE 	IS 'Numeric currency code';
COMMENT ON COLUMN 	T_CM_CURR_DENOM.DENOM 		IS 'Denomination';