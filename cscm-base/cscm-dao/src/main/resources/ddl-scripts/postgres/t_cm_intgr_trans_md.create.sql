CREATE TABLE t_cm_intgr_trans_md
    (terminal_id                   VARCHAR(6),
    oper_id                        NUMERIC(20),
    datetime                       TIMESTAMP,
    oper_type                      NUMERIC(2),
    amount                         NUMERIC(12),
    note_retracted                 NUMERIC(4),
    note_rejected                  NUMERIC(4),
    note_cash_in                   NUMERIC(4)
);

CREATE UNIQUE INDEX i_cm_intgr_trans_md_head_idx ON t_cm_intgr_trans_md
  (
    terminal_id                     ASC,
    datetime                        ASC,
    oper_type                       ASC,
    oper_id                         ASC
  );

COMMENT ON TABLE t_cm_intgr_trans_md IS 'This table contains transactions loaded from external system (multi-dispenser file format). It is used only for integrational purpose';
COMMENT ON COLUMN t_cm_intgr_trans_md.amount IS 'Transaction amount';
COMMENT ON COLUMN t_cm_intgr_trans_md.datetime IS 'Transaction time';
COMMENT ON COLUMN t_cm_intgr_trans_md.note_cash_in IS 'Number of bills loaded to cash-in';
COMMENT ON COLUMN t_cm_intgr_trans_md.note_rejected IS 'Number of bills dropped to reject';
COMMENT ON COLUMN t_cm_intgr_trans_md.note_retracted IS 'Number of bills dropped to retract';
COMMENT ON COLUMN t_cm_intgr_trans_md.oper_id IS 'Transaction ID';
COMMENT ON COLUMN t_cm_intgr_trans_md.oper_type IS 'Transaction type ID';
COMMENT ON COLUMN t_cm_intgr_trans_md.terminal_id IS 'Unique ID identifying the ATM';
