CREATE TABLE t_cm_enc_period_denom
    (enc_period_id                    NUMERIC(6) NOT NULL,
    denom_value                    NUMERIC(6) NOT NULL,
    denom_count                    NUMERIC(4) NOT NULL,
    denom_curr                     NUMERIC(3) NOT NULL)
;

COMMENT ON TABLE t_cm_enc_period_denom IS 'This table contains list of denominations that would be loaded on planned replenishment. If there are two records with the same denomination for one replenishment - that means that count of this denomination was splitted into several cassettes as there were empty cassettes after forecasting';
COMMENT ON COLUMN t_cm_enc_period_denom.denom_count IS 'Denomination count';
COMMENT ON COLUMN t_cm_enc_period_denom.denom_curr IS 'Denomination currency';
COMMENT ON COLUMN t_cm_enc_period_denom.denom_value IS 'Denomination';
COMMENT ON COLUMN t_cm_enc_period_denom.enc_period_id IS 'Planned replenishment ID';
