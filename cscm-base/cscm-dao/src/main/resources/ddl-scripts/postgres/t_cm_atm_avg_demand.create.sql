CREATE TABLE t_cm_atm_avg_demand
    (atm_id                        NUMERIC(9) NOT NULL,
    main_curr_ci                   NUMERIC(12),
    main_curr_co                   NUMERIC(12),
    main_curr_ci_last_hour_diff    NUMERIC(12),
    main_curr_co_last_hour_diff    NUMERIC(12),
    main_curr_ci_last_three_hours  NUMERIC(12),
    main_curr_co_last_three_hours  NUMERIC(12),
    sec_curr_ci                    NUMERIC(12),
    sec_curr_co                    NUMERIC(12),
    sec_curr_ci_last_hour_diff     NUMERIC(12),
    sec_curr_co_last_hour_diff     NUMERIC(12),
    sec_curr_ci_last_three_hours   NUMERIC(12),
    sec_curr_co_last_three_hours   NUMERIC(12),
    sec2_curr_ci                   NUMERIC(12),
    sec2_curr_co                   NUMERIC(12),
    sec2_curr_ci_last_hour_diff    NUMERIC(12),
    sec2_curr_co_last_hour_diff    NUMERIC(12),
    sec2_curr_ci_last_three_hours  NUMERIC(12),
    sec2_curr_co_last_three_hours  NUMERIC(12),
    sec3_curr_ci                   NUMERIC(12),
    sec3_curr_co                   NUMERIC(12),
    sec3_curr_ci_last_hour_diff    NUMERIC(12),
    sec3_curr_co_last_hour_diff    NUMERIC(12),
    sec3_curr_ci_last_three_hours  NUMERIC(12),
    sec3_curr_co_last_three_hours  NUMERIC(12)
);

COMMENT ON TABLE T_CM_ATM_AVG_DEMAND IS 'This table contains calculated cash demand for ATM currencies';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.ATM_ID IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.MAIN_CURR_CI IS 'Average hourly demand of main currency for cash inserts';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.MAIN_CURR_CI_LAST_HOUR_DIFF IS 'Difference between roecasted and statistical demand of main currency for cash inserts';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.MAIN_CURR_CI_LAST_THREE_HOURS IS 'Statistical hourly demand of main currency for last three hours for cash inserts';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.MAIN_CURR_CO IS 'Average hourly demand of main currency for cash withdrawals';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.MAIN_CURR_CO_LAST_HOUR_DIFF IS 'Difference between roecasted and statistical demand of main currency for cash withdrawals';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.MAIN_CURR_CO_LAST_THREE_HOURS IS 'Statistical hourly demand of main currency for last three hours for cash withdrawals';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC_CURR_CI IS 'Average hourly demand of secondary currency for cash inserts';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC_CURR_CI_LAST_HOUR_DIFF IS 'Statistical hourly demand of secondary currency for last three hours for cash inserts';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC_CURR_CI_LAST_THREE_HOURS IS 'Difference between roecasted and statistical demand of secondary currency for cash inserts';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC_CURR_CO IS 'Average hourly demand of secondary currency for cash withdrawals';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC_CURR_CO_LAST_HOUR_DIFF IS 'Statistical hourly demand of secondary currency for last three hours for cash withdrawals';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC_CURR_CO_LAST_THREE_HOURS IS 'Difference between roecasted and statistical demand of secondary currency for cash withdrawals';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC2_CURR_CI IS 'Average hourly demand of tertiary currency for cash inserts';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC2_CURR_CI_LAST_HOUR_DIFF IS 'Statistical hourly demand of tertiary currency for last three hours for cash inserts';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC2_CURR_CI_LAST_THREE_HOURS IS 'Difference between roecasted and statistical demand of tertiary currency for cash inserts';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC2_CURR_CO IS 'Average hourly demand of tertiary currency for cash withdrawals';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC2_CURR_CO_LAST_HOUR_DIFF IS 'Statistical hourly demand of tertiary currency for last three hours for cash withdrawals';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC2_CURR_CO_LAST_THREE_HOURS IS 'Difference between roecasted and statistical demand of tertiary currency for cash withdrawals';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC3_CURR_CI IS 'Average hourly demand of quaternary currency for cash inserts';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC3_CURR_CI_LAST_HOUR_DIFF IS 'Statistical hourly demand of quaternary currency for last three hours for cash inserts';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC3_CURR_CI_LAST_THREE_HOURS IS 'Difference between roecasted and statistical demand of quaternary currency for cash inserts';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC3_CURR_CO IS 'Average hourly demand of quaternary currency for cash withdrawals';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC3_CURR_CO_LAST_HOUR_DIFF IS 'Statistical hourly demand of quaternary currency for last three hours for cash withdrawals';
COMMENT ON COLUMN T_CM_ATM_AVG_DEMAND.SEC3_CURR_CO_LAST_THREE_HOURS IS 'Difference between roecasted and statistical demand of quaternary currency for cash withdrawals';

ALTER TABLE t_cm_atm_avg_demand ADD CONSTRAINT t_cm_atm_avg_demand_pk PRIMARY KEY(ATM_ID);