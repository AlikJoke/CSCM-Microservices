CREATE TABLE T_CM_ENC_CASHIN_STAT_DETAILS (
	CASH_IN_ENCASHMENT_ID  NUMERIC(6),
	CASS_VALUE     NUMERIC(6)    NOT NULL,
	CASS_CURR      NUMERIC(3)    NOT NULL,
	CASS_COUNT     NUMERIC(6)    NOT NULL,
	ACTION_TYPE    NUMERIC(1),
	CASS_NUMBER    NUMERIC(2)
);

COMMENT ON TABLE T_CM_ENC_CASHIN_STAT_DETAILS IS 'This table contains cassette details for loaded cash in replenishments';
COMMENT ON COLUMN T_CM_ENC_CASHIN_STAT_DETAILS.ACTION_TYPE IS 'Action type (1-bills unload,2-bills upload)';
COMMENT ON COLUMN T_CM_ENC_CASHIN_STAT_DETAILS.CASH_IN_ENCASHMENT_ID IS 'Internal Cash Management cash in replenishment identifier';
COMMENT ON COLUMN T_CM_ENC_CASHIN_STAT_DETAILS.CASS_COUNT IS 'Count of bills in cassette';
COMMENT ON COLUMN T_CM_ENC_CASHIN_STAT_DETAILS.CASS_CURR IS 'Cuurency of bills in cassette';
COMMENT ON COLUMN T_CM_ENC_CASHIN_STAT_DETAILS.CASS_NUMBER IS 'Cassette number';
COMMENT ON COLUMN T_CM_ENC_CASHIN_STAT_DETAILS.CASS_VALUE IS 'Denominations of bills in cassette';

CREATE UNIQUE INDEX I_CM_ECISTDET_EID2CN ON T_CM_ENC_CASHIN_STAT_DETAILS (CASH_IN_ENCASHMENT_ID, CASS_NUMBER, ACTION_TYPE);


