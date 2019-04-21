CREATE TABLE T_CM_ROUTE_ORG_ATTR
(
  ORG_ID        NUMERIC(10),
  ATTR_ID       NUMERIC(6),
  VALUE         VARCHAR(255),
  ATTR_IS_USED  NUMERIC
);

COMMENT ON TABLE T_CM_ROUTE_ORG_ATTR IS 'This table contains values of attributes of organization';

COMMENT ON COLUMN T_CM_ROUTE_ORG_ATTR.ORG_ID IS 'Unique ID identifying Organization';

COMMENT ON COLUMN T_CM_ROUTE_ORG_ATTR.ATTR_ID IS 'Unique ID identifying attribute';

COMMENT ON COLUMN T_CM_ROUTE_ORG_ATTR.VALUE IS 'Value of attribute';

COMMENT ON COLUMN T_CM_ROUTE_ORG_ATTR.ATTR_IS_USED IS 'Flag indicating whether attribute is used or not (1 - is used, 0 - not used)';
