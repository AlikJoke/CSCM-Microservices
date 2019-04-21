ALTER TABLE t_cm_enc_compare_denom
ADD CONSTRAINT fk_cm_enc_compare_denom_dc FOREIGN KEY (denom_curr)
REFERENCES t_cm_curr (code_n3);

ALTER TABLE t_cm_enc_compare_denom
ADD CONSTRAINT fk_cm_enc_compare_denom_epi FOREIGN KEY (enc_compare_id)
REFERENCES t_cm_enc_compare (ID);