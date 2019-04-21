CREATE TEMPORARY TABLE T_CM_TEMP_ENC_REPORT
    (remaining                      NUMERIC(15) NOT NULL,
    curr_code                      VARCHAR(7) NOT NULL,
    stat_date                      TIMESTAMP,
    end_of_stats_date              NUMERIC(1))
ON COMMIT DELETE ROWS;

COMMENT ON TABLE T_CM_TEMP_ENC_REPORT IS 'This table contains statisitcal and forecasted ATM currencies remainders for replenishment details report.';
COMMENT ON COLUMN T_CM_TEMP_ENC_REPORT.CURR_CODE IS 'Currency code';
COMMENT ON COLUMN T_CM_TEMP_ENC_REPORT.END_OF_STATS_DATE IS 'Flag indicationg whether date is last statisitcal period present';
COMMENT ON COLUMN T_CM_TEMP_ENC_REPORT.REMAINING IS 'Remaining amount of currency';
COMMENT ON COLUMN T_CM_TEMP_ENC_REPORT.STAT_DATE IS 'Datetime';
