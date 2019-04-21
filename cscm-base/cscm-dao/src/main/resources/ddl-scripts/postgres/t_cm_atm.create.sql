CREATE TABLE T_CM_ATM (
  	atm_id                         NUMERIC(9) NOT NULL,
    street                         VARCHAR(250),
    city                           VARCHAR(150),
    state                          VARCHAR(150),
    main_curr_code                 NUMERIC(3),
    secondary_curr_code            NUMERIC(3),
    secondary2_curr_code           NUMERIC(3),
    secondary3_curr_code           NUMERIC(3),
    inst_id                        VARCHAR(4) NOT NULL,
    external_atm_id                VARCHAR(16),
    name                           VARCHAR(150),
    calendar_id                    NUMERIC(9),
    type                           NUMERIC(2),
    latitude              		   VARCHAR(20),
    longitude             		   VARCHAR(20),
    main_curr_enabled              NUMERIC(1,0),
    model 						   VARCHAR(128)
);


COMMENT ON TABLE T_CM_ATM IS 'This table contains ATMs.';
COMMENT ON COLUMN T_CM_ATM.ATM_ID IS 'Unique ID identifying ATM';
COMMENT ON COLUMN T_CM_ATM.CALENDAR_ID IS 'Unique ID identifiying technical calendar for ATM';
COMMENT ON COLUMN T_CM_ATM.CITY IS 'City';
COMMENT ON COLUMN T_CM_ATM.EXTERNAL_ATM_ID IS 'Unique ID identifying ATM in external system';
COMMENT ON COLUMN T_CM_ATM.INST_ID IS 'Unique ID identifying institute';
COMMENT ON COLUMN T_CM_ATM.MAIN_CURR_CODE IS 'Main currency code';
COMMENT ON COLUMN T_CM_ATM.NAME IS 'Name';
COMMENT ON COLUMN T_CM_ATM.SECONDARY_CURR_CODE IS 'Secondary currency code';
COMMENT ON COLUMN T_CM_ATM.SECONDARY2_CURR_CODE IS 'Secondary(2) currency code';
COMMENT ON COLUMN T_CM_ATM.SECONDARY3_CURR_CODE IS 'Secondary(3) currency code';
COMMENT ON COLUMN T_CM_ATM.STATE IS 'State (region)';
COMMENT ON COLUMN T_CM_ATM.STREET IS 'Street';
COMMENT ON COLUMN T_CM_ATM.TYPE IS 'ATM type';
COMMENT ON COLUMN T_CM_ATM.LATITUDE IS 'ATM coordinates Latitude';
COMMENT ON COLUMN T_CM_ATM.LONGITUDE IS 'ATM coordinates Longitude';
COMMENT ON COLUMN T_CM_ATM.MAIN_CURR_ENABLED IS 'Flag indicating whether forecasting uses main currency';

CREATE UNIQUE INDEX I_CM_ATM_ID ON T_CM_ATM (ATM_ID);

ALTER TABLE T_CM_ATM ADD 
  	CONSTRAINT PK_CM_ATM PRIMARY KEY USING INDEX I_CM_ATM_ID;