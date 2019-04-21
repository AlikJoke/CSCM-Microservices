CREATE OR REPLACE VIEW V_CM_ATM_SALARY_DAYS (
   CL_ID,
   DATE_START,
   DATE_END,
   DAY_START,
   DAY_END,
   MONTH_START,
   MONTH_END,
   CORRS_DATE_START,
   CORRS_DATE_END,
   CORRS_DAY_START,
   CORRS_DAY_END,
   CORRS_MONTH_START,
   CORRS_MONTH_END )
AS
with salary_dates as
(
    SELECT CL_ID, CL_DATE from
    t_cm_calendar_days
    where cl_day_type = 17
), salary_start as
(
    SELECT row_number() over (partition by sd1.CL_ID order by sd1.CL_DATE desc) as RNK, CL_ID, CL_DATE
    from salary_dates sd1
    where not exists (
        select null from salary_dates sd2
        where
            sd2.CL_ID = sd1.CL_ID
            and
            sd2.CL_DATE = sd1.CL_DATE - interval '1 hour')
    and not exists (
        select null from salary_dates sd3
        where
            sd3.CL_ID = sd1.CL_ID
            and
            date_trunc('day',sd3.CL_DATE) = date_trunc('day',sd1.CL_DATE) - interval '1 day'
    )
), salary_end as
(
    SELECT row_number() over (partition by sd1.CL_ID order by sd1.CL_DATE desc) as RNK, CL_ID, CL_DATE
    from salary_dates sd1
    where not exists (
        select null from salary_dates sd2
        where
            sd2.CL_ID = sd1.CL_ID
            and
            sd2.CL_DATE = sd1.CL_DATE + interval '1 hour')
    and not exists (
        select null from salary_dates sd3
        where
            sd3.CL_ID = sd1.CL_ID
            and
            date_trunc('day',sd3.CL_DATE) = date_trunc('day',sd1.CL_DATE) + interval '1 day')
)
, salary_periods as
(
    select
        st.cl_id,
        st.cl_date as DATE_START,
        se.cl_date as DATE_END,
        date_trunc('day',st.cl_date - date_trunc('month',st.cl_date)) as DAY_START,
        date_trunc('day',se.cl_date - date_trunc('month',se.cl_date)) as DAY_END,
        date_trunc('month',st.cl_date) as MONTH_START,
        date_trunc('month',se.cl_date) as MONTH_END,
        row_number() over (partition by st.CL_ID,date_trunc('month',st.cl_date) order by st.CL_DATE) as PERIOD_NUM,
        count(1) over (partition by st.CL_ID,date_trunc('month',st.cl_date)) as PERIOD_CNT,
        row_number() over (partition by st.CL_ID order by st.CL_DATE) as PERIOD_ID
    from salary_start st,salary_end se
    where
        st.rnk= se.rnk
        and
        st.cl_id = se.cl_id
)
select
    src.cl_id,
    CASE WHEN src.day_start = src.day_End then src.date_start - interval '1 day' ELSE src.date_start END as DATE_START,
    CASE WHEN src.day_start = src.day_End then src.date_end + interval '1 day' ELSE src.date_end END as DATE_END,
    src.day_start,
    src.day_end,
    src.month_start,
    src.month_end,
    CASE WHEN corrs.day_start = corrs.day_End then corrs.date_start - interval '1 day' ELSE corrs.date_start END as CORRS_DATE_START,
    CASE WHEN corrs.day_start = corrs.day_End then corrs.date_end + interval '1 day' ELSE corrs.date_end END as CORRS_DATE_END,
    corrs.day_start as corrs_day_start,
    corrs.day_end as corrs_day_end,
    corrs.month_start as corrs_month_start,
    corrs.month_end as corrs_month_end
from
    salary_periods src,
    salary_periods corrs
where
    src.cl_id = corrs.cl_id
    and
    src.period_id > corrs.period_id
    and
    corrs.month_start = (select max(month_start) from salary_periods tmp where tmp.cl_id = src.cl_id and tmp.month_start < src.month_start)
    and (
        src.period_cnt <= corrs.period_cnt and src.period_num = corrs.period_num
        or
        src.period_cnt > corrs.period_cnt and (
            src.period_num <= corrs.period_cnt and src.period_num = corrs.period_num
            or
            src.period_num > corrs.period_cnt and src.period_num = (corrs.period_num+corrs.period_cnt)
            )
    )
order by src.date_start;
