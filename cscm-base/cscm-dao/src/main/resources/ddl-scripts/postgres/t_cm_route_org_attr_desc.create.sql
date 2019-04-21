CREATE TABLE T_CM_ROUTE_ORG_ATTR_DESC
(
  ID        NUMERIC(6),
  DESCX     VARCHAR(255),
  REQUIRED  NUMERIC(1)
);

COMMENT ON TABLE T_CM_ROUTE_ORG_ATTR_DESC IS '''This table contains list of Organization attributes';

COMMENT ON COLUMN T_CM_ROUTE_ORG_ATTR_DESC.ID IS 'Unique ID identifying attribute';

COMMENT ON COLUMN T_CM_ROUTE_ORG_ATTR_DESC.DESCX IS 'Attribute description';

COMMENT ON COLUMN T_CM_ROUTE_ORG_ATTR_DESC.REQUIRED IS 'Flag indicating whether attribute is required or not (1 - required, 0 - not required)';
