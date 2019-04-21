CREATE TABLE T_CM_CURR_CONVERT_RATE(
 	CNVT_DATE       TIMESTAMP                          NOT NULL,
  	SRC_CURR_CODE   NUMERIC(4)                     NOT NULL,
  	DEST_CURR_CODE  NUMERIC(4)                     NOT NULL,
  	CNVT_RATE       NUMERIC(24,12),
  	MULTIPLE_FLAG   VARCHAR(1)              DEFAULT 'M'                   NOT NULL,
    SRC_INST_ID     VARCHAR(4) 			  				 				NOT NULL,
    DEST_INST_ID    VARCHAR(4) 			  				 				NOT NULL
);

COMMENT ON TABLE 	T_CM_CURR_CONVERT_RATE 					IS 'This table contains conversion rates for currencies';
COMMENT ON COLUMN 	T_CM_CURR_CONVERT_RATE.CNVT_DATE 		IS 'Conversion date';
COMMENT ON COLUMN 	T_CM_CURR_CONVERT_RATE.SRC_CURR_CODE 	IS 'Source currency code';
COMMENT ON COLUMN 	T_CM_CURR_CONVERT_RATE.DEST_CURR_CODE 	IS 'Destination currency code';
COMMENT ON COLUMN 	T_CM_CURR_CONVERT_RATE.CNVT_RATE 		IS 'Conversion Rate';
COMMENT ON COLUMN 	T_CM_CURR_CONVERT_RATE.MULTIPLE_FLAG 	IS 'Multiply flag:(M ? Source to Destination,D ? Destination to Source)';
COMMENT ON COLUMN	T_CM_CURR_CONVERT_RATE.SRC_INST_ID 		IS 'Source currency institutuion ID';
COMMENT ON COLUMN 	T_CM_CURR_CONVERT_RATE.DEST_INST_ID		IS 'Destination currency institutuion ID';
