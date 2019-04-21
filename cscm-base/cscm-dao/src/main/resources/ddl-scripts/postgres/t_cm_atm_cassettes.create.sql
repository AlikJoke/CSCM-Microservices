CREATE TABLE t_cm_atm_cassettes
    (atm_id                        NUMERIC(9),
    cass_type                      NUMERIC(1),
    cass_value					   NUMERIC(2),
    cass_number                    NUMERIC(1),
    cass_curr                      NUMERIC(3),
    cass_state                     NUMERIC(1),
    cass_is_present                INTEGER DEFAULT 1,
    cass_notes					   NUMERIC(4),
    cass_capacity				   NUMERIC(9)
 );
 
CREATE UNIQUE INDEX I_CM_ACAS_AID2CT2CN ON T_CM_ATM_CASSETTES (
    atm_id                          ASC,
    CASS_TYPE,
    CASS_NUMBER
);
 
