CREATE TABLE T_CM_INTGR_PARAMS (
	last_utrnno                    NUMERIC(20),
    last_downtime_datetime         TIMESTAMP,
    last_trans_datetime            TIMESTAMP,
    cass_check_datetime            TIMESTAMP
 );
 
COMMENT ON TABLE T_CM_INTGR_PARAMS IS 'This table contains integration parameters';
COMMENT ON COLUMN T_CM_INTGR_PARAMS.CASS_CHECK_DATETIME IS 'Date and time of last cassettes remainders laoding from external system';
COMMENT ON COLUMN T_CM_INTGR_PARAMS.LAST_DOWNTIME_DATETIME IS 'Date and time of last loaded downtime';
COMMENT ON COLUMN T_CM_INTGR_PARAMS.LAST_TRANS_DATETIME IS 'Date and time of last loaded transaction';
COMMENT ON COLUMN T_CM_INTGR_PARAMS.LAST_UTRNNO IS 'ID of last loaded transaction';
