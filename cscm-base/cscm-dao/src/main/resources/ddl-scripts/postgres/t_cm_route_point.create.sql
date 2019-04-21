CREATE TABLE T_CM_ROUTE_POINT
(
  TYP           NUMERIC(1),
  ORD           NUMERIC(3),
  ROUTE_ID      NUMERIC(10),
  POINT_SRC_ID  NUMERIC(6),
  POINT_TIME    NUMERIC(9),
  VISITED_FLAG  NUMERIC(1)  DEFAULT 0,
  REORDER_FLAG  NUMERIC(1)  DEFAULT 0
);

COMMENT ON TABLE T_CM_ROUTE_POINT IS 'This table contains points of a Route';

COMMENT ON COLUMN T_CM_ROUTE_POINT.TYP IS 'Point type: 0 - free point (not in a route), 1 - normal point (point in a route)';

COMMENT ON COLUMN T_CM_ROUTE_POINT.ORD IS 'Order of point in a route';

COMMENT ON COLUMN T_CM_ROUTE_POINT.ROUTE_ID IS 'Route ID';

COMMENT ON COLUMN T_CM_ROUTE_POINT.POINT_SRC_ID IS 'Replenishment ID';

COMMENT ON COLUMN T_CM_ROUTE_POINT.POINT_TIME IS 'Arrival time to point';

COMMENT ON COLUMN T_CM_ROUTE_POINT.VISITED_FLAG IS 'Route visited flag: 0- point needs to be visited, 1 - point has been already visited';

COMMENT ON COLUMN T_CM_ROUTE_POINT.REORDER_FLAG IS 'Point reorder flag: 0 - point hasn''t been reorderesd, 1 - point has been reordered in route';
