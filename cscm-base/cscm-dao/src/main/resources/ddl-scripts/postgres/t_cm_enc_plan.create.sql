CREATE TABLE T_CM_ENC_PLAN
    (ENC_PLAN_ID                    NUMERIC(6) NOT NULL,
    ATM_ID                         NUMERIC(9) NOT NULL,
    DATE_PREVIOUS_ENCASHMENT       TIMESTAMP,
    INTERVAL_ENC_LAST_TO_FORTH     NUMERIC(4),
    DATE_FORTHCOMING_ENCASHMENT    TIMESTAMP,
    INTERVAL_ENC_FORTH_TO_FUTURE   NUMERIC(4),
    DATE_FUTURE_ENCASHMENT         TIMESTAMP,
    IS_APPROVED                    NUMERIC(1) DEFAULT 0,
    ENC_LOSTS_CURR_CODE            NUMERIC(3),
    FORECAST_RESP_CODE             NUMERIC(2) DEFAULT 0,
    EMERGENCY_ENCASHMENT           NUMERIC(1) DEFAULT 0,
    APPROVE_ID                     NUMERIC(4) DEFAULT 0,
    CONFIRM_ID                     NUMERIC(4) DEFAULT 0,
    ENC_LOSTS                      NUMERIC(15),
    ENC_PRICE                      NUMERIC(15),
    ENC_REQ_ID                     NUMERIC(6),
    CASH_ADD_ENCASHMENT            NUMERIC(1),
    ENCASHMENT_TYPE                NUMERIC(1),
    ENC_LOSTS_JOINT                NUMERIC(15),
    ENC_LOSTS_SPLIT                NUMERIC(15),
    ENCASHMENT_TYPE_BY_LOSTS       NUMERIC(1),
    ENC_PRICE_CASH_IN              NUMERIC(15),
    ENC_PRICE_CASH_OUT             NUMERIC(15),
    ENC_PRICE_BOTH_IN_OUT          NUMERIC(15)
);

CREATE UNIQUE INDEX I_CM_ENC_PLAN_EPI ON T_CM_ENC_PLAN(
    ENC_PLAN_ID                     ASC
);

ALTER TABLE T_CM_ENC_PLAN
ADD CONSTRAINT PK_CM_ENC_PLAN PRIMARY KEY (ENC_PLAN_ID);

COMMENT ON TABLE T_CM_ENC_PLAN IS 'This table contains planned encashments.';
COMMENT ON COLUMN T_CM_ENC_PLAN.APPROVE_ID IS 'Person ID of user that approved replenishment';
COMMENT ON COLUMN T_CM_ENC_PLAN.ATM_ID IS 'Unique ID identifying ATM';
COMMENT ON COLUMN T_CM_ENC_PLAN.CASH_ADD_ENCASHMENT IS 'Flag indicating whether replenishment is with cash adding to cassettes';
COMMENT ON COLUMN T_CM_ENC_PLAN.CONFIRM_ID IS 'Person ID of user that confrimed replenishment';
COMMENT ON COLUMN T_CM_ENC_PLAN.DATE_FORTHCOMING_ENCASHMENT IS 'Date of planned replenishment';
COMMENT ON COLUMN T_CM_ENC_PLAN.DATE_FUTURE_ENCASHMENT IS 'Date of future replenishment';
COMMENT ON COLUMN T_CM_ENC_PLAN.DATE_PREVIOUS_ENCASHMENT IS 'Date of previous replenishment';
COMMENT ON COLUMN T_CM_ENC_PLAN.EMERGENCY_ENCASHMENT IS 'Flag indicating whether replenishment is emergency';
COMMENT ON COLUMN T_CM_ENC_PLAN.ENC_LOSTS IS 'Replenishment losts';
COMMENT ON COLUMN T_CM_ENC_PLAN.ENC_LOSTS_CURR_CODE IS 'Code of currency in which replenishment sum is defined';
COMMENT ON COLUMN T_CM_ENC_PLAN.ENC_LOSTS_JOINT IS 'Replenishment losts in case of joint replenishment';
COMMENT ON COLUMN T_CM_ENC_PLAN.ENC_LOSTS_SPLIT IS 'Replenishment losts in case of separate replenishments';
COMMENT ON COLUMN T_CM_ENC_PLAN.ENC_PLAN_ID IS 'Planned replenishment ID';
COMMENT ON COLUMN T_CM_ENC_PLAN.ENC_PRICE IS 'Replenishment price';
COMMENT ON COLUMN T_CM_ENC_PLAN.ENC_PRICE_BOTH_IN_OUT IS 'Price of joint replenishment';
COMMENT ON COLUMN T_CM_ENC_PLAN.ENC_PRICE_CASH_IN IS 'Price of cash-in replenishment';
COMMENT ON COLUMN T_CM_ENC_PLAN.ENC_PRICE_CASH_OUT IS 'Price of cash-out replenishment';
COMMENT ON COLUMN T_CM_ENC_PLAN.ENC_REQ_ID IS 'ID of replenishment request';
COMMENT ON COLUMN T_CM_ENC_PLAN.ENCASHMENT_TYPE IS 'ID of encashment type';
COMMENT ON COLUMN T_CM_ENC_PLAN.ENCASHMENT_TYPE_BY_LOSTS IS 'ID of encashment type if it is caused by losts';
COMMENT ON COLUMN T_CM_ENC_PLAN.FORECAST_RESP_CODE IS 'Response code of cash in frorecasting procedure';
COMMENT ON COLUMN T_CM_ENC_PLAN.INTERVAL_ENC_FORTH_TO_FUTURE IS 'Interval between planned replenishment and future replenishment';
COMMENT ON COLUMN T_CM_ENC_PLAN.INTERVAL_ENC_LAST_TO_FORTH IS 'Interval between last replenishment and planned replenishment';
COMMENT ON COLUMN T_CM_ENC_PLAN.IS_APPROVED IS 'Flag indicating whether replenishment is approved or not';
