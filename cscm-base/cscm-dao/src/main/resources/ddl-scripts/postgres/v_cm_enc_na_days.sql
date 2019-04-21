CREATE OR REPLACE VIEW V_CM_ENC_NA_DAYS (
   CL_ID,
   DATE_START,
   DATE_END )
AS
with encna_dates as
(
    SELECT CL_ID, CL_DATE from
    t_cm_calendar_days
    where CL_DAY_ENC_AVAIL = 0
), encna_start as
(
    SELECT row_number() over (partition by sd1.CL_ID order by sd1.CL_DATE desc) as RNK, CL_ID, CL_DATE
    from encna_dates sd1
    where not exists (
        select null from encna_dates sd2
        where
            sd2.CL_ID = sd1.CL_ID
            and
            sd2.CL_DATE = sd1.CL_DATE - interval '1 hour')
), encna_end as
(
    SELECT row_number() over (partition by sd1.CL_ID order by sd1.CL_DATE desc) as RNK, CL_ID, CL_DATE
    from encna_dates sd1
    where not exists (
        select null from encna_dates sd2
        where
            sd2.CL_ID = sd1.CL_ID
            and
            sd2.CL_DATE = sd1.CL_DATE + interval '1 hour')
)
, encna_periods as
(
    select
        st.cl_id,
        st.cl_date as DATE_START,
        se.cl_date as DATE_END
    from encna_start st,encna_end se
    where
        st.rnk= se.rnk
        and
        st.cl_id = se.cl_id
)
select
    src.cl_id,
    src.date_start,
    src.date_end
from
    encna_periods src
order by src.cl_id,src.date_start;