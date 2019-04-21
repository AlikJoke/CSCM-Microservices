CREATE TABLE T_CM_ROUTE_NODES
(
  PIDA      NUMERIC(9),
  PIDB      NUMERIC(9),
  DISTANCE  NUMERIC(9),
  TIME      NUMERIC(9)
);

COMMENT ON TABLE T_CM_ROUTE_NODES IS 'This table contains Distance and Duration Matrix for Routing';

COMMENT ON COLUMN T_CM_ROUTE_NODES.PIDA IS 'Point A ID (ATM or Depot ID )';

COMMENT ON COLUMN T_CM_ROUTE_NODES.PIDB IS 'Point B ID (ATM or Depot ID )';

COMMENT ON COLUMN T_CM_ROUTE_NODES.DISTANCE IS 'Distance of route between points';

COMMENT ON COLUMN T_CM_ROUTE_NODES.TIME IS 'Duration of route between points';
