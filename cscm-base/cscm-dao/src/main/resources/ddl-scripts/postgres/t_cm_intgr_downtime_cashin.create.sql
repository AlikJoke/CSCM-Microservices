CREATE TABLE t_cm_intgr_downtime_cashin(
	pid                            VARCHAR(6),
    stat_date                      TIMESTAMP,
    avail_coeff                    NUMERIC(4,2)
);

CREATE UNIQUE INDEX i_cm_intgr_dt_ci_aid_sd ON t_cm_intgr_downtime_cashin (
    pid                             ASC,
    stat_date                       ASC
);


COMMENT ON TABLE t_cm_intgr_downtime_cashin IS 'This table contains downtime statistics for cash in cassettes loaded from external system';
COMMENT ON COLUMN t_cm_intgr_downtime_cashin.avail_coeff IS 'Cash in availability coefficient';
COMMENT ON COLUMN t_cm_intgr_downtime_cashin.pid IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN t_cm_intgr_downtime_cashin.stat_date IS 'Bginnig of statisitcs period';
