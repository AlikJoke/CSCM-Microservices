CREATE TABLE t_cm_audit_xml_data
    (id                             NUMERIC(12),
    event_id                       NUMERIC(12),
    xml_details                    TEXT,
    complex_types                  VARCHAR(1000),
    xml_linked_fields              TEXT
);

CREATE UNIQUE INDEX i_cm_aud_xml_id_evt_id ON t_cm_audit_xml_data
  (
    id                              ASC,
    event_id                        ASC
  );

COMMENT ON TABLE t_cm_audit_xml_data IS 'This table contains xml data of events that were audited by system';
COMMENT ON COLUMN t_cm_audit_xml_data.complex_types IS 'List of Java types presented in XML';
COMMENT ON COLUMN t_cm_audit_xml_data.event_id IS 'Audited event ID';
COMMENT ON COLUMN t_cm_audit_xml_data.id IS 'Unique ID identifying XML data';
COMMENT ON COLUMN t_cm_audit_xml_data.xml_details IS 'XML details of audited action';
COMMENT ON COLUMN t_cm_audit_xml_data.xml_linked_fields IS 'XML containg IDs of linked events';