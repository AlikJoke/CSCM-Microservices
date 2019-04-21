INSERT INTO T_CM_USER (
	ID, 
	LOGIN, 
	PASSWORD_HASH, 
	DESCRIPTION, 
	STATUS_ID,
	NAME, 
	POSITION, 
	PHONE_NUMBER, 
	LOCKING, 
	NEED_PASSWORD_CHANGE,
	BAD_ATTEMPT_COUNT, 
	PASSWORD_CHANGE_DATETIME, 
	LAST_LOGON_DATETIME, 
	LOCALE
) VALUES (
	1, 
	'admin', 
	'e6xJ735/sxf54Z9quS+GGV/10gslzl+4tyBic+g7mhbLBfezeP+uzAGd7PUh3hVxtC8hBY1RxcRf'||CHR(10)||'f+PwitimgA==',
	'Default administrator (password: admin1)', 
	1,  
	'Default administrator', 
    NULL, 
    NULL,
    0, 
    0, 
    0, 
    CURRENT_TIMESTAMP, 
    NULL, 
    'en_US'
);

COMMIT;