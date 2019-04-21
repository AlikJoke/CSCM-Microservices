CREATE TABLE T_CM_JASPER_REPORT
(
  ID             NUMERIC(6),
  INST_ID        VARCHAR(4),
  NAME           VARCHAR(30),
  DESCX          VARCHAR(200),
  SRC_FILE_NAME  VARCHAR(50),
  SRC_LOAD_DATE  TIMESTAMP,
  SRC            BYTEA,
  CREATOR_ID     NUMERIC(10),
  SYSTEM_FLAG    NUMERIC(1)                      DEFAULT 0
);

CREATE UNIQUE INDEX I_CM_JASPER_REPORT_ID ON T_CM_JASPER_REPORT (ID);

ALTER TABLE T_CM_JASPER_REPORT ADD 
	CONSTRAINT PK_CM_JASPER_REPORT PRIMARY KEY USING INDEX I_CM_JASPER_REPORT_ID;