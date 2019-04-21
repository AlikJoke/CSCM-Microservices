CREATE OR REPLACE VIEW v_cm_calendar_days (
   cl_id,
   cl_date,
   cnt_demand,
   cnt_enc_avail,
   cl_day_type,
   cl_day_enc_avail,
   cl_hour,
   mixed_demand_day,
   mixed_enc_avail_day )
AS
with t as (
    select CL_ID,date_trunc('day',CL_DATE) as CL_DATE,
        count(distinct CL_DAY_TYPE) as CNT_DEMAND,
        sum(distinct CL_DAY_TYPE) as SUM_DEMAND,
        count(distinct CL_DAY_ENC_AVAIL) as CNT_ENC_AVAIL,
        sum(distinct CL_DAY_ENC_AVAIL) as SUM_ENC_AVAIL,
        count (1) as HOUR_CNT
    FROM t_cm_calendar_days
    group BY CL_ID,date_trunc('day',CL_DATE)
)
select ccd.CL_ID,ccd.CL_DATE
,COALESCE(t.cnt_demand,0) as cnt_demand,COALESCE(t.cnt_enc_avail,0) as cnt_enc_avail,
    ccd.CL_DAY_TYPE as CL_DAY_TYPE,
    COALESCE(ccd.CL_DAY_ENC_AVAIL,1) as CL_DAY_ENC_AVAIL,
     to_number(to_char(ccd.CL_DATE, 'HH24'),'FM99') as CL_HOUR,
    case when COALESCE(t.cnt_demand,0) > 1 OR (COALESCE(t.sum_demand,0) > 1 AND HOUR_CNT < 24 AND HOUR_CNT > 0) then 1 else 0 end as MIXED_DEMAND_DAY,
    case when COALESCE(t.cnt_enc_avail,0) > 1 OR (COALESCE(t.sum_enc_avail,0) != 1 AND  HOUR_CNT < 24 AND HOUR_CNT > 0) then 1 else 0 end as MIXED_ENC_AVAIL_DAY
from t_cm_calendar_days ccd
left outer join t on (date_trunc('day',ccd.CL_DATE) = t.CL_DATE and ccd.CL_ID = t.CL_ID);
