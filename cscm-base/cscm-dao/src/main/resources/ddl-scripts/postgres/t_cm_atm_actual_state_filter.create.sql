CREATE TABLE T_CM_ATM_ACTUAL_STATE_FILTER
    (USER_ID                       NUMERIC(10) NOT NULL,
    atm_state             		   VARCHAR(10),
    atm_problem         		   VARCHAR(10),
    days_to_cash_end               NUMERIC(6),
    cash_state			           VARCHAR(10),
    device_type                    NUMERIC(1),
    enc_state			           VARCHAR(10),
    ID							   NUMERIC(6),
    name						   VARCHAR(255),
    description					   VARCHAR(255)
);

ALTER TABLE T_CM_ATM_ACTUAL_STATE_FILTER ADD CONSTRAINT T_CM_ATM_ACTUAL_STATE_FILTER_PK PRIMARY KEY(ID);