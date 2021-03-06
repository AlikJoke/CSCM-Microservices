CREATE TABLE T_CM_TASK (
  	TASK_ID      	NUMERIC(4)        	NOT NULL,
  	DESCRIPTION  	VARCHAR(500),
  	CRON         	VARCHAR(100),
  	TYPE         	NUMERIC(2),
  	NAME         	VARCHAR(50),
  	USER_ID         NUMERIC(10) NOT NULL
);

COMMENT ON TABLE 	T_CM_TASK 				IS 'This table contains sheduled tasks';
COMMENT ON COLUMN 	T_CM_TASK.TASK_ID 		IS 'Task ID';
COMMENT ON COLUMN 	T_CM_TASK.DESCRIPTION	IS 'Task description';
COMMENT ON COLUMN 	T_CM_TASK.CRON 			IS 'CRON expression';
COMMENT ON COLUMN 	T_CM_TASK.TYPE 			IS 'Task type';
COMMENT ON COLUMN 	T_CM_TASK.NAME 			IS 'Task name';
COMMENT ON COLUMN   T_CM_TASK.USER_ID 		IS 'User ID';