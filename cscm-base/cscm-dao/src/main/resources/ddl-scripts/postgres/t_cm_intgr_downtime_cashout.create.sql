CREATE TABLE t_cm_intgr_downtime_cashout (
	pid                            VARCHAR(6),
    stat_date                      TIMESTAMP,
    avail_coeff                    NUMERIC(4,2)
);

CREATE UNIQUE INDEX i_cm_intgr_dt_co_aid_sd ON t_cm_intgr_downtime_cashout (
    pid                             ASC,
    stat_date                       ASC
);

COMMENT ON TABLE t_cm_intgr_downtime_cashout IS 'This table contains downtime stats for cash out cassettes loaded from external system';
COMMENT ON COLUMN t_cm_intgr_downtime_cashout.avail_coeff IS 'Dispenser availability coefficient';
COMMENT ON COLUMN t_cm_intgr_downtime_cashout.pid IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN t_cm_intgr_downtime_cashout.stat_date IS 'Stat hour';
