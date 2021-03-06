INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME) VALUES('param.password.new.same.old.control.enabled');
INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME) VALUES('param.password.min.length');                 
INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME) VALUES('param.auth.lock.bad.attempts.enabled');      
INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME) VALUES('param.auth.lock.bad.attempts.count');        
INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME) VALUES('param.auth.unlock.bad.attempts.enabled');    
INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME) VALUES('param.auth.unlock.bad.attempts.time');       
INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME) VALUES('param.auth.lock.no.using.enabled');          
INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME) VALUES('param.auth.lock.no.using.time');             
INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME) VALUES('param.auth.periodic.password.change.enabled');
INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME) VALUES('param.auth.periodic.password.change.time');  
--INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME) VALUES('param.jasper.directory.win');                
--INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME) VALUES('param.jasper.directory.unix');               
INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME,VALUE) VALUES('param.help.url','http://10.7.34.60:7001/svcm-help');
INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME,VALUE) VALUES('param.replenishment.double.check.enabled','true');
INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME,VALUE) VALUES('param.display.datatable.rows','50');
INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME,VALUE) VALUES('param.replenishment.main.curr.usage.enabled','true');
INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME,VALUE) VALUES('param.display.routes.module','false');
INSERT INTO T_CM_APPLICATION_PARAMETERS (NAME,VALUE) VALUES('param.cache.life.time','2');

COMMIT;
