CREATE OR REPLACE VIEW v_cm_cashin_r_curr_act_rem (
   atm_id,
   stat_date,
   cash_in_encashment_id,
   curr_code,
   curr_remaining )
AS
select st.atm_id, st.stat_date, st.cash_in_encashment_id,
st.cass_curr as CURR_CODE, sum(CASS_REMAINING*st.CASS_VALUE) as CURR_REMAINING
from t_cm_cashin_r_cass_stat st
    join t_cm_atm_cassettes ac on 
        (ac.atm_id = st.atm_id 
        and ac.cass_number = st.cass_number 
        and ac.cass_type = 3
        and COALESCE(ac.cass_state,0) = 0)
where 
    1=1
group by st.atm_id, st.stat_date, st.cash_in_encashment_id,st.cass_curr;
