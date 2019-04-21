CREATE TABLE t_cm_atm_default_coeffs
    (atm_id                        NUMERIC(9),
    currency                       NUMERIC(3),
    currency_mode                  NUMERIC(1),
    extra_low_coeff                NUMERIC(12),
    extra_high_coeff               NUMERIC(12),
    weekends_multiplier            NUMERIC(12),
    holidays_multiplier            NUMERIC(12),
    night_multiplier               NUMERIC(12)
);

CREATE UNIQUE INDEX I_CM_ADC_AID2CC2CM ON T_CM_ATM_DEFAULT_COEFFS (
    atm_id                          ASC,
    CURRENCY,
    CURRENCY_MODE
);

COMMENT ON TABLE T_CM_ATM_DEFAULT_COEFFS IS 'This table contains cash demand coefficient multipliers for ATM currencies';
COMMENT ON COLUMN T_CM_ATM_DEFAULT_COEFFS.ATM_ID IS 'Unique ID identifying ATM';
COMMENT ON COLUMN T_CM_ATM_DEFAULT_COEFFS.CURRENCY IS 'Currency code';
COMMENT ON COLUMN T_CM_ATM_DEFAULT_COEFFS.CURRENCY_MODE IS 'Currency mode - ''CASH_IN'' or ''CASH_OUT''';
COMMENT ON COLUMN T_CM_ATM_DEFAULT_COEFFS.EXTRA_HIGH_COEFF IS 'Demand coefficient for hours with abnormally high demand';
COMMENT ON COLUMN T_CM_ATM_DEFAULT_COEFFS.EXTRA_LOW_COEFF IS 'Demand coefficient for hours with abnormally low demand';
COMMENT ON COLUMN T_CM_ATM_DEFAULT_COEFFS.HOLIDAYS_MULTIPLIER IS 'Demand coefficient for holiday hours';
COMMENT ON COLUMN T_CM_ATM_DEFAULT_COEFFS.NIGHT_MULTIPLIER IS 'Demand coefficient for night hours';
COMMENT ON COLUMN T_CM_ATM_DEFAULT_COEFFS.WEEKENDS_MULTIPLIER IS 'Demand coefficient for weekend hours';