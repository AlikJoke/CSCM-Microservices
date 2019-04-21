CREATE TABLE T_CM_ROUTE
(
  ID            NUMERIC(9),
  ROUTE_DATE    DATE,
  ORG_ID        NUMERIC(10),
  COST_CURR     NUMERIC(3),
  COST          NUMERIC(10),
  RESULT_FLAG   NUMERIC(1),
  ROUTE_TIME    NUMERIC(9),
  ROUTE_LENGTH  NUMERIC(9),
  ROUTE_STATUS  NUMERIC(9)
);

COMMENT ON TABLE T_CM_ROUTE IS 'This table contains information about Routes on a Date';

COMMENT ON COLUMN T_CM_ROUTE.ID IS 'Route ID';

COMMENT ON COLUMN T_CM_ROUTE.ROUTE_DATE IS 'Route Date';

COMMENT ON COLUMN T_CM_ROUTE.ORG_ID IS 'ID of Organization';

COMMENT ON COLUMN T_CM_ROUTE.COST_CURR IS 'Route Cost Currency';

COMMENT ON COLUMN T_CM_ROUTE.COST IS 'Cost of Route';

COMMENT ON COLUMN T_CM_ROUTE.RESULT_FLAG IS 'Route Result: 0 - Constraints are OK, 1 - Time Constraints violated, 2 - Cars or Atms constraints violated, 3 - Summ constraint violated';

COMMENT ON COLUMN T_CM_ROUTE.ROUTE_TIME IS 'Route Duration';

COMMENT ON COLUMN T_CM_ROUTE.ROUTE_LENGTH IS 'Route Length';

COMMENT ON COLUMN T_CM_ROUTE.ROUTE_STATUS IS 'Route Status: 1 - Route Created, 2 - Route Approved, 3 - Route is in progress, 4 - Route Finished';



ALTER TABLE T_CM_ROUTE ADD
  CONSTRAINT T_CM_ROUTE_PK
  PRIMARY KEY
  (ID);