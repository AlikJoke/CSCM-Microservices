CREATE OR REPLACE VIEW v_cm_cashout_stat_enc2enc (
   encashment_id,
   next_encashment_id )
AS
SELECT
    ecs.ENCASHMENT_ID,
    COALESCE(min(ecs_next.ENCASHMENT_ID),-999) AS NEXT_ENCASHMENT_ID
FROM t_cm_enc_cashout_stat ecs
    left outer JOIN t_cm_enc_cashout_stat ecs_next ON (ecs.ATM_ID = ecs_next.ATM_ID)
WHERE
    ecs_next.ENCASHMENT_ID > ecs.ENCASHMENT_ID
GROUP BY ecs.ENCASHMENT_ID
ORDER BY ecs.ENCASHMENT_ID;
