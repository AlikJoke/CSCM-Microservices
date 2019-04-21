CREATE OR REPLACE VIEW v_cm_user2roles (
   user_id,
   role_id )
AS
select distinct USER_ID,ROLE_ID
from t_cm_user2roles
union
select USER_ID,rg2r.ROLE_ID
from t_cm_user2role_groups u2rg
join t_cm_role_group2roles rg2r on (rg2r.ROLE_GROUP_ID = u2rg.ROLE_GROUP_ID)
;
