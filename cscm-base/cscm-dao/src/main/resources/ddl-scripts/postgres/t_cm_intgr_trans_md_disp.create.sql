CREATE TABLE t_cm_intgr_trans_md_disp
    (oper_id                        NUMERIC(20),
    disp_number                    NUMERIC(2),
    face                           NUMERIC(6),
    currency                       NUMERIC(3),
    note_dispensed                 NUMERIC(4),
    note_remained                  NUMERIC(4)
);

COMMENT ON TABLE t_cm_intgr_trans_md_disp IS 'This table contains transaction dispensers loaded from external system (multi-dispenser file format). It is used only for integrational purpose';
COMMENT ON COLUMN t_cm_intgr_trans_md_disp.currency IS 'Currency of dispenser';
COMMENT ON COLUMN t_cm_intgr_trans_md_disp.face IS 'Denomination of dispenser';
COMMENT ON COLUMN t_cm_intgr_trans_md_disp.note_dispensed IS 'Number of motes dispensed';
COMMENT ON COLUMN t_cm_intgr_trans_md_disp.note_remained IS 'Number of motes remained';
COMMENT ON COLUMN t_cm_intgr_trans_md_disp.oper_id IS 'Transaction ID';
COMMENT ON COLUMN t_cm_intgr_trans_md_disp.disp_number IS 'Dispenser number';