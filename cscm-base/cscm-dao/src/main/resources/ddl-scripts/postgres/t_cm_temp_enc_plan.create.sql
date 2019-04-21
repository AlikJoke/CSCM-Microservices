CREATE TEMPORARY TABLE t_cm_temp_enc_plan
    (enc_plan_id                    NUMERIC(6) NOT NULL,
    atm_id                         NUMERIC(9) NOT NULL,
    date_forthcoming_encashment    TIMESTAMP)
ON COMMIT DELETE ROWS;

COMMENT ON TABLE T_CM_TEMP_ENC_PLAN IS 'This table contains planned encashments generated by forecasting for user-defined date.';
COMMENT ON COLUMN t_cm_temp_enc_plan.atm_id IS 'Unique ID identifying ATM';
COMMENT ON COLUMN t_cm_temp_enc_plan.date_forthcoming_encashment IS 'Date of planned replenishment';
COMMENT ON COLUMN t_cm_temp_enc_plan.enc_plan_id IS 'Planned replenishment ID';
