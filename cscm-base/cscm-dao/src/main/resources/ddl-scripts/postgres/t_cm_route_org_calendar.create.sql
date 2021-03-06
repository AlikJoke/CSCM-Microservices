CREATE TABLE T_CM_ROUTE_ORG_CALENDAR
(
  ORG_ID   NUMERIC(10),
  CL_DATE  TIMESTAMP
);

COMMENT ON TABLE T_CM_ROUTE_ORG_CALENDAR IS 'This table contains available calendars for Organizations';

COMMENT ON COLUMN T_CM_ROUTE_ORG_CALENDAR.ORG_ID IS 'Unique ID identifying the calendar';

COMMENT ON COLUMN T_CM_ROUTE_ORG_CALENDAR.CL_DATE IS 'Date (hour accuracy)';
