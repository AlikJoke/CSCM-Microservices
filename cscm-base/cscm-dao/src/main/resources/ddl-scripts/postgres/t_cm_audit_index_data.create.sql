CREATE TABLE t_cm_audit_index_data
    (id                             NUMERIC(12),
    event_id                       NUMERIC(12),
    field_name                     VARCHAR(100),
    field_value                    VARCHAR(1000)
);

CREATE UNIQUE INDEX i_cm_aud_indx_id_evt_id ON t_cm_audit_index_data
  (
    id                              ASC,
    event_id                        ASC
  );

COMMENT ON TABLE t_cm_audit_index_data IS 'This table contains index fields of events that were audited by system';
COMMENT ON COLUMN t_cm_audit_index_data.event_id IS 'Audited event ID';
COMMENT ON COLUMN t_cm_audit_index_data.field_name IS 'Name of field';
COMMENT ON COLUMN t_cm_audit_index_data.field_value IS 'Value of field';
COMMENT ON COLUMN t_cm_audit_index_data.id IS 'Unique ID identifying index field data';