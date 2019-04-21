CREATE OR REPLACE VIEW v_cm_cashin_stat_enc2enc (
   cash_in_encashment_id,
   next_cash_in_encashment_id )
AS
SELECT
    ecs.CASH_IN_ENCASHMENT_ID,
    COALESCE(min(ecs_next.CASH_IN_ENCASHMENT_ID),-999) AS NEXT_CASH_IN_ENCASHMENT_ID
FROM t_cm_enc_cashin_stat ecs
    left outer JOIN t_cm_enc_cashin_stat ecs_next ON (ecs.ATM_ID = ecs_next.ATM_ID)
WHERE
    ecs_next.CASH_IN_ENCASHMENT_ID > ecs.CASH_IN_ENCASHMENT_ID
GROUP BY ecs.CASH_IN_ENCASHMENT_ID
ORDER BY ecs.CASH_IN_ENCASHMENT_ID;
