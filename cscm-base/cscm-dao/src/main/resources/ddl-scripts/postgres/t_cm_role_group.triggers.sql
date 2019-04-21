CREATE FUNCTION TR_BUPD_CM_ROLE_GROUP_TRIGGER_FUNCTION () RETURNS trigger AS  
'BEGIN 
NEW.ID := OLD.ID;
RETURN NEW;
END;'
 LANGUAGE  plpgsql;
CREATE TRIGGER TR_BUPD_CM_ROLE_GROUP
BEFORE UPDATE ON T_CM_ROLE_GROUP FOR EACH ROW
EXECUTE PROCEDURE TR_BUPD_CM_ROLE_GROUP_TRIGGER_FUNCTION();
/