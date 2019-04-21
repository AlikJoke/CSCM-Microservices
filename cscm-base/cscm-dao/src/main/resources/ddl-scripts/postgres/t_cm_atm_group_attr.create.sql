CREATE TABLE T_CM_ATM_GROUP_ATTR (
  	ATM_GROUP_ID	NUMERIC(6)         	NOT NULL,
  	ATTR_ID       	NUMERIC(6)           NOT NULL,
  	VALUE         	VARCHAR(255)  NOT NULL,
  	ATTR_IS_USED  	NUMERIC(1)
);

COMMENT ON TABLE 	T_CM_ATM_GROUP_ATTR 				IS 'This tables contains values of attributes of ATM groups';
COMMENT ON COLUMN 	T_CM_ATM_GROUP_ATTR.ATM_GROUP_ID 	IS 'Unique ID identifying ATM group';
COMMENT ON COLUMN 	T_CM_ATM_GROUP_ATTR.ATTR_ID 		IS 'Unique ID identifying attribute';
COMMENT ON COLUMN 	T_CM_ATM_GROUP_ATTR.VALUE 			IS 'Value of attribute';
COMMENT ON COLUMN 	T_CM_ATM_GROUP_ATTR.ATTR_IS_USED 	IS 'Flag indicating whether attribute is used or not (1 - is used, 0 - not used)';