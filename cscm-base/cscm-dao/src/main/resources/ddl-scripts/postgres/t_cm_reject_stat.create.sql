CREATE TABLE t_cm_reject_stat (
	atm_id                      NUMERIC(9) NOT NULL,
    stat_date                      TIMESTAMP NOT NULL,
    encashment_id                  NUMERIC(6),
    bills_count                    NUMERIC(6) NOT NULL,
    bills_remaining                NUMERIC(5)
 );
 
CREATE UNIQUE INDEX cr_stat_enc_atm_date_idx ON t_cm_reject_stat (
    atm_id                          ASC,
    encashment_id                   ASC,
    stat_date                       ASC
 );

COMMENT ON TABLE t_cm_reject_stat IS 'This table contains reject statistics periods';
COMMENT ON COLUMN t_cm_reject_stat.atm_id IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN t_cm_reject_stat.bills_count IS 'Count of bills loaded during the period';
COMMENT ON COLUMN t_cm_reject_stat.bills_remaining IS 'Volume of free space left in the end of period';
COMMENT ON COLUMN t_cm_reject_stat.encashment_id IS 'Internal Cash Management replenishment identifier';
COMMENT ON COLUMN t_cm_reject_stat.stat_date IS 'Beginning of statistics period';
