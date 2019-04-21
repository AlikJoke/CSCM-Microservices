CREATE TABLE T_CM_ATM_CALENDAR_DAYS (
  	atm_id                         NUMERIC(9) NOT NULL,
    currency                       NUMERIC(3) NOT NULL,
    cl_date                        TIMESTAMP,
    cl_day_type                    NUMERIC(2),
    coeff                          NUMERIC(12,6) DEFAULT 1,
    user_defined                   NUMERIC(1) DEFAULT 0,
    currency_mode                  NUMERIC(1),
    user_disabled                  NUMERIC(1) DEFAULT 0
);

COMMENT ON TABLE T_CM_ATM_CALENDAR_DAYS IS 'This table contains dates and cash demand coefficients for ATM currencies';
COMMENT ON COLUMN T_CM_ATM_CALENDAR_DAYS.ATM_ID IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN T_CM_ATM_CALENDAR_DAYS.CL_DATE IS 'Date (with time)';
COMMENT ON COLUMN T_CM_ATM_CALENDAR_DAYS.CL_DAY_TYPE IS 'Demand type for hour';
COMMENT ON COLUMN T_CM_ATM_CALENDAR_DAYS.COEFF IS 'Demand coefficient for hour';
COMMENT ON COLUMN T_CM_ATM_CALENDAR_DAYS.CURRENCY IS 'Currency code';
COMMENT ON COLUMN T_CM_ATM_CALENDAR_DAYS.CURRENCY_MODE IS 'Currency mode: 1-''CASH_OUT'', 2-''CASH_IN'', 3-''REC_IN'', 4-''REC_OUT''';
COMMENT ON COLUMN T_CM_ATM_CALENDAR_DAYS.USER_DEFINED IS 'Flag indicates whether type and coefficient were defined by user';
COMMENT ON COLUMN T_CM_ATM_CALENDAR_DAYS.USER_DISABLED IS 'Flag indicates whether type and coefficient were disabled by user';

CREATE UNIQUE INDEX I_CM_ACD_AID2CID2CDT ON T_CM_ATM_CALENDAR_DAYS (
    atm_id                          ASC,
    cl_date                         ASC,
    currency_mode                   ASC,
    currency                        ASC
  );
