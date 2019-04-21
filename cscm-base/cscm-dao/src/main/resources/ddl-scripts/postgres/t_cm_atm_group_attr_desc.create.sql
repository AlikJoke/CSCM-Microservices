CREATE TABLE T_CM_ATM_GROUP_ATTR_DESC (
  	ATTR_ID     NUMERIC(6)         	NOT NULL,
  	DESCX       VARCHAR(255)  NOT NULL,
  	REQUIRED    NUMERIC(1),
 	GROUP_TYPE  NUMERIC(3)
);

COMMENT ON TABLE 	T_CM_ATM_GROUP_ATTR_DESC 			IS 'This table contains list of ATM group attributes';
COMMENT ON COLUMN 	T_CM_ATM_GROUP_ATTR_DESC.ATTR_ID 	IS 'Unique ID identifying attribute';
COMMENT ON COLUMN 	T_CM_ATM_GROUP_ATTR_DESC.DESCX 		IS 'Attribute description';
COMMENT ON COLUMN 	T_CM_ATM_GROUP_ATTR_DESC.REQUIRED 	IS 'Flag indicating whether attribute is required or not (1 - required, 0 - not required)';
COMMENT ON COLUMN 	T_CM_ATM_GROUP_ATTR_DESC.GROUP_TYPE IS 'Group type';
