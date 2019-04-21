CREATE TEMPORARY TABLE t_cm_temp_atm_list
    (id NUMERIC(9))
ON COMMIT DELETE ROWS;

COMMENT ON TABLE t_cm_temp_atm_list IS 'This table contains temporary list of ATMs for user selection';
COMMENT ON COLUMN t_cm_temp_atm_list.id IS 'Unique ID identifying ATM';