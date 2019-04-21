ALTER TABLE t_cm_reject_stat
ADD CONSTRAINT t_cm_reject_stat_r01 FOREIGN KEY (atm_id)
REFERENCES t_cm_atm (atm_id);

ALTER TABLE t_cm_reject_stat
ADD CONSTRAINT t_cm_reject_stat_r02 FOREIGN KEY (encashment_id)
REFERENCES t_cm_enc_cashout_stat (encashment_id);