CREATE TABLE T_CM_ROUTE_ATM2ORG
(
  ATM_ID  NUMERIC(9),
  ORG_ID  NUMERIC(10)
);

COMMENT ON TABLE T_CM_ROUTE_ATM2ORG IS 'This table contains ATMs linking to Organization';

COMMENT ON COLUMN T_CM_ROUTE_ATM2ORG.ATM_ID IS 'ATM ID';

COMMENT ON COLUMN T_CM_ROUTE_ATM2ORG.ORG_ID IS 'Organization ID';


ALTER TABLE T_CM_ROUTE_ATM2ORG ADD
  CONSTRAINT T_CM_ROUTE_ATM2ORG_PK
  PRIMARY KEY
  (ATM_ID, ORG_ID);
