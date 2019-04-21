CREATE TABLE T_CM_ATM_ACTUAL_STATE
    (ATM_ID                        NUMERIC(9) NOT NULL,
    CASH_OUT_STAT_DATE             TIMESTAMP,
    CASH_OUT_ENCASHMENT_ID         NUMERIC(6),
    CASH_IN_STAT_DATE              TIMESTAMP,
    CASH_IN_ENCASHMENT_ID          NUMERIC(6),
    CASH_IN_CAPACITY               NUMERIC(6),
    LAST_UPDATE                    TIMESTAMP NOT NULL,
    OUT_OF_CASH_OUT_DATE           TIMESTAMP,
    OUT_OF_CASH_OUT_CURR           NUMERIC(3),
    OUT_OF_CASH_OUT_RESP           NUMERIC(2),
    CASH_IN_INITIAL                NUMERIC(6),
    REJECT_INITIAL                 NUMERIC(6),
    OUT_OF_CASH_IN_RESP            NUMERIC(2),
    LAST_WITHDRAWAL_HOURS          NUMERIC(4),
    LAST_ADDITION_HOURS            NUMERIC(4),
    CURR_REMAINING_ALERT           NUMERIC(1),
    CASH_IN_R_INITIAL              NUMERIC(6),
    OUT_OF_CASH_IN_DATE            TIMESTAMP,
    ATM_STATE               	   NUMERIC(1),
    ATM_PROBLEM					   NUMERIC(1),
    AVG_TRANS_IN_HOUR              NUMERIC(6),
    AVG_TRANS_IN_DAY               NUMERIC(6),
    EUR_DEMAND_VALUE			   NUMERIC(6),
    EUR_EXHAUSTION_DATE			   TIMESTAMP,
    USD_DEMAND_VALUE			   NUMERIC(6),
    USD_EXHAUSTION_DATE			   TIMESTAMP,
    RUR_DEMAND_VALUE			   NUMERIC(6),
    RUR_EXHAUSTION_DATE			   TIMESTAMP
);

COMMENT ON TABLE T_CM_ATM_ACTUAL_STATE IS 'This table contains information of last loaded ATM statistcs and date when ATM is out of cash (for each currency presented in ATM)';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.ATM_ID IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.CASH_IN_ENCASHMENT_ID IS 'Identifier of last loaded CASH_IN replenishment';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.CASH_IN_INITIAL IS 'Initial volume of CASH_IN';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.CASH_IN_R_INITIAL IS 'Initial volume of recycling cassette';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.CASH_IN_STAT_DATE IS 'Date of last loaded CASH_IN replenishment';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.CASH_OUT_ENCASHMENT_ID IS 'Identifier of last loaded CASH_OUT replenishment';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.CASH_OUT_STAT_DATE IS 'Date of last loaded CASH_OUT replenishment';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.CURR_REMAINING_ALERT IS 'Flag that indicates that alert should be shown to user because of low level of cash in dispenser';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.LAST_ADDITION_HOURS IS 'Time passed since last opertaion with cash in';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.LAST_UPDATE IS 'Date of last state update';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.LAST_WITHDRAWAL_HOURS IS 'Time passed since last opertaion with dispenser';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.OUT_OF_CASH_IN_DATE IS 'Date  when cash in space in ATM ends';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.OUT_OF_CASH_IN_RESP IS 'Response of computing date  when cash in space in ATM ends';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.OUT_OF_CASH_OUT_CURR IS 'Code of currency that will end first in dispenser';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.OUT_OF_CASH_OUT_DATE IS 'Date when one of  ATM dispenser''s currencies ends';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.OUT_OF_CASH_OUT_RESP IS 'Response of computing date  when one of  ATM dispenser''s currencies ends';
COMMENT ON COLUMN T_CM_ATM_ACTUAL_STATE.REJECT_INITIAL IS 'Initial volume of reject/retract';

ALTER TABLE t_cm_atm_actual_state ADD CONSTRAINT t_cm_atm_actual_State_pk PRIMARY KEY(ATM_ID);