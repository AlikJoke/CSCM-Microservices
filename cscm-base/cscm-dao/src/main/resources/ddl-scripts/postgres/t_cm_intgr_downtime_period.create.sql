CREATE TABLE t_cm_intgr_downtime_period (
	pid                            VARCHAR(6),
    start_date                     TIMESTAMP,
    end_date                       TIMESTAMP,
    downtime_type_ind              NUMERIC(1)
);

COMMENT ON TABLE t_cm_intgr_downtime_period IS 'This table contains downtime stats loaded from external system';
COMMENT ON COLUMN t_cm_intgr_downtime_period.end_date IS 'End time - ATM is up';
COMMENT ON COLUMN t_cm_intgr_downtime_period.pid IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN t_cm_intgr_downtime_period.start_date IS 'Start time - ATM is down';
COMMENT ON COLUMN t_cm_intgr_downtime_period.downtime_type_ind IS 'Downtime type indicator';
