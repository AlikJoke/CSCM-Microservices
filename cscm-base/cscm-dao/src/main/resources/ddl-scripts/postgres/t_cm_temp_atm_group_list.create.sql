CREATE TEMPORARY TABLE t_cm_temp_atm_group_list
    (id NUMERIC(9))
ON COMMIT DELETE ROWS;

COMMENT ON TABLE t_cm_temp_atm_group_list IS 'This table contains temporary list of ATM Groups for user selection';
COMMENT ON COLUMN t_cm_temp_atm_group_list.id IS 'Unique ID identifying ATM Group';