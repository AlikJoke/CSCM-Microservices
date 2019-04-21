CREATE TABLE t_cm_audit_event_data
    (id                             NUMERIC(12),
    datetime                       TIMESTAMP,
    object_type                    VARCHAR(100),
    action_type                    NUMERIC(3),
    object_id                      VARCHAR(50),
    login                          VARCHAR(40),
    sign                           VARCHAR(100)
);

CREATE UNIQUE INDEX i_cm_aud_event_id_obj_type ON t_cm_audit_event_data
  (
    id                              ASC,
    object_type                     ASC
  );


COMMENT ON TABLE t_cm_audit_event_data IS 'This table contains events that were audited by system';
COMMENT ON COLUMN t_cm_audit_event_data.action_type IS 'Type of action that was performed by user';
COMMENT ON COLUMN t_cm_audit_event_data.datetime IS 'Date';
COMMENT ON COLUMN t_cm_audit_event_data.id IS 'Unique ID identifying audited event';
COMMENT ON COLUMN t_cm_audit_event_data.login IS 'Login of user that performed action';
COMMENT ON COLUMN t_cm_audit_event_data.object_id IS 'ID of audited object';
COMMENT ON COLUMN t_cm_audit_event_data.object_type IS 'Type of audited object';
COMMENT ON COLUMN t_cm_audit_event_data.sign IS 'Digital sign of audited event';