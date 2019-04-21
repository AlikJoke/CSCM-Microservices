CREATE TABLE T_CM_ATM_DEMAND_HISTORY
    (ATM_ID                        NUMERIC(9) NOT NULL,
    CURRENCY                       NUMERIC(3) NOT NULL,
    CL_DATE                        TIMESTAMP,
    CURRENCY_MODE                  VARCHAR(8),
    DEMAND                         NUMERIC(12,3) DEFAULT 1);

CREATE UNIQUE INDEX I_CM_ADH_AID2CID2CDT ON T_CM_ATM_DEMAND_HISTORY(
    ATM_ID                          ASC,
    CL_DATE                         ASC,
    CURRENCY_MODE                   ASC,
    CURRENCY                        ASC
);



COMMENT ON TABLE T_CM_ATM_DEMAND_HISTORY IS 'This table contains dates and cash demand coefficients for ATM currencies';
COMMENT ON COLUMN T_CM_ATM_DEMAND_HISTORY.ATM_ID IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN T_CM_ATM_DEMAND_HISTORY.CL_DATE IS 'Date';
COMMENT ON COLUMN T_CM_ATM_DEMAND_HISTORY.CURRENCY IS 'Currency code';
COMMENT ON COLUMN T_CM_ATM_DEMAND_HISTORY.CURRENCY_MODE IS 'Currency mode: 1-''CASH_OUT'', 2-''CASH_IN'', 3-''REC_IN'', 4-''REC_OUT''';
COMMENT ON COLUMN T_CM_ATM_DEMAND_HISTORY.DEMAND IS 'Demand for date';
