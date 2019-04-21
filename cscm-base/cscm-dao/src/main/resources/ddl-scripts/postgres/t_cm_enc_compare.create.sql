CREATE TABLE T_CM_ENC_COMPARE
    (ID                             NUMERIC(6) NOT NULL,
    ATM_ID                         NUMERIC(9) NOT NULL,
    DATE_FORTHCOMING_ENCASHMENT    TIMESTAMP,
    ENCASHMENT_TYPE                NUMERIC(1),
    FORECAST_RESP_CODE             NUMERIC(2) DEFAULT 0,
    EMERGENCY_ENCASHMENT           NUMERIC(1) DEFAULT 0,
    ENC_LOSTS                      NUMERIC(15),
    ENC_PRICE                      NUMERIC(15),
    ENC_LOSTS_CURR                 NUMERIC(3),
    CASH_IN_EXISTS                 NUMERIC(1)
);

CREATE UNIQUE INDEX I_CM_ENC_COMPARE_EPI ON T_CM_ENC_COMPARE(
    ID                              ASC
);


ALTER TABLE T_CM_ENC_COMPARE
ADD CONSTRAINT PK_CM_ENC_COMPARE PRIMARY KEY (ID);

COMMENT ON TABLE T_CM_ENC_COMPARE IS 'This table contains planned replenishments for compare';
COMMENT ON COLUMN T_CM_ENC_COMPARE.ATM_ID IS 'Unique ID identifying ATM';
COMMENT ON COLUMN T_CM_ENC_COMPARE.CASH_IN_EXISTS IS 'Flag indicating whether cash-in is present in ATM';
COMMENT ON COLUMN T_CM_ENC_COMPARE.DATE_FORTHCOMING_ENCASHMENT IS 'Date of planned replenishment';
COMMENT ON COLUMN T_CM_ENC_COMPARE.EMERGENCY_ENCASHMENT IS 'Flag indicating whether replenishment is emergency';
COMMENT ON COLUMN T_CM_ENC_COMPARE.ENC_LOSTS IS 'Replenishment losts';
COMMENT ON COLUMN T_CM_ENC_COMPARE.ENC_LOSTS_CURR IS 'Code of currency in which replenishment losts are defined';
COMMENT ON COLUMN T_CM_ENC_COMPARE.ENC_PRICE IS 'Replenishment price';
COMMENT ON COLUMN T_CM_ENC_COMPARE.ENCASHMENT_TYPE IS 'Encashment type';
COMMENT ON COLUMN T_CM_ENC_COMPARE.FORECAST_RESP_CODE IS 'Response code of frorecasting procedure';
COMMENT ON COLUMN T_CM_ENC_COMPARE.ID IS 'Planned replenishment ID';