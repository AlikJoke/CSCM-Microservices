ALTER TABLE t_cm_cashin_r_curr_stat
ADD CONSTRAINT fk_cm_cashin_r_curr_stat_eid FOREIGN KEY (cash_in_encashment_id)
REFERENCES t_cm_enc_cashin_stat (cash_in_encashment_id);

ALTER TABLE t_cm_cashin_r_curr_stat
ADD CONSTRAINT fk_cm_cashin_r_curr_stat_aid FOREIGN KEY (atm_id)
REFERENCES t_cm_atm (atm_id);

ALTER TABLE t_cm_cashin_r_curr_stat
ADD CONSTRAINT fk_cm_cashin_r_curr_stat_cc FOREIGN KEY (curr_code)
REFERENCES t_cm_curr (code_n3);