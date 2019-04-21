ALTER TABLE t_cm_cashin_denom_stat
ADD CONSTRAINT fk_cm_cashin_curr_stat_aid FOREIGN KEY (atm_id)
REFERENCES t_cm_atm (atm_id);

ALTER TABLE t_cm_cashin_denom_stat
ADD CONSTRAINT fk_cm_cashin_curr_stat_ceid FOREIGN KEY (cash_in_encashment_id)
REFERENCES t_cm_enc_cashin_stat (cash_in_encashment_id);