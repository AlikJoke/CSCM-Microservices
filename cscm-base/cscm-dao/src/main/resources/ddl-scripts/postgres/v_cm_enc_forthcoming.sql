CREATE OR REPLACE VIEW V_CM_ENC_FORTHCOMING (
   ATM_ID,
   ENCASHMENT_TYPE,
   ENC_PLAN_ID,
   DATE_FORTHCOMING_ENCASHMENT )
AS
with
ci_enc as (
    SELECT ATM_ID,count(1) as CNT, date_trunc('day',ecs.CASH_IN_ENC_DATE) as ENC_DATE
    FROM T_CM_ENC_CASHIN_STAT ecs
    WHERE ecs.CASH_IN_ENC_DATE >= date_trunc('day',CURRENT_TIMESTAMP)
    GROUP BY ATM_ID, date_trunc('day',ecs.CASH_IN_ENC_DATE)
),
co_enc as (
    SELECT ATM_ID,count(1) as CNT, date_trunc('day',ecs.ENC_DATE) as ENC_DATE
    FROM T_CM_ENC_CASHOUT_STAT ecs
    WHERE ecs.ENC_DATE >= date_trunc('day',CURRENT_TIMESTAMP)
    GROUP BY ATM_ID,date_trunc('day',ecs.ENC_DATE)
),
jt_enc as (
    SELECT ecso.ATM_ID,count(1) as CNT, date_trunc('day',ecso.ENC_DATE) as ENC_DATE
    FROM T_CM_ENC_CASHOUT_STAT ecso,T_CM_ENC_CASHIN_STAT ecsi
    WHERE ecso.ENC_DATE >= date_trunc('day',CURRENT_TIMESTAMP)
        AND abs(ceil(extract(epoch from ecso.ENC_DATE - ecsi.CASH_IN_ENC_DATE)/3600)) < 1
        AND ecso.ATM_ID = ecsi.ATM_ID
    GROUP BY ecso.ATM_ID, date_trunc('day',ecso.ENC_DATE)
),
plan_enc as (
    SELECT ENCASHMENT_TYPE,ATM_ID,ENC_PLAN_ID,date_trunc('day',DATE_FORTHCOMING_ENCASHMENT) as ENC_DATE ,
    row_number() over (partition by ATM_ID,ENCASHMENT_TYPE order by ENC_PLAN_ID) as RNK
    FROM T_CM_ENC_PLAN ep
    WHERE ep.DATE_FORTHCOMING_ENCASHMENT >= date_trunc('day',CURRENT_TIMESTAMP)
    AND ep.IS_APPROVED = 1
)
SELECT ep.ATM_ID,ep.ENCASHMENT_TYPE, ep.ENC_PLAN_ID, ep.ENC_DATE
FROM plan_enc ep
    left outer join ci_enc on (ci_enc.ATM_ID = ep.ATM_ID and ci_enc.ENC_DATE = ep.ENC_DATE)
    left outer join co_enc on (co_enc.ATM_ID = ep.ATM_ID and co_enc.ENC_DATE = ep.ENC_DATE)
    left outer join jt_enc on (jt_enc.ATM_ID = ep.ATM_ID and jt_enc.ENC_DATE = ep.ENC_DATE)
WHERE ep.ENCASHMENT_TYPE = 2 and ep.RNK > COALESCE(ci_enc.CNT,0)
     OR ep.ENCASHMENT_TYPE = 3 and ep.RNK > COALESCE(co_enc.CNT,0)
     OR ep.ENCASHMENT_TYPE = 1 and ep.RNK > COALESCE(jt_enc.CNT,0);
