ALTER TABLE T_CM_ATM_ACTUAL_STATE_FILTER ADD
  CONSTRAINT T_CM_ATM_ACTUAL_STATE_FILTER_UID FOREIGN KEY (USER_ID) REFERENCES T_CM_USER (ID);