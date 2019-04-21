CREATE TABLE t_cm_enc_plan_request (
	id                             NUMERIC(6) NOT NULL,
    request_date                   TIMESTAMP NOT NULL,
    name                           VARCHAR(40) NOT NULL,
    description                    VARCHAR(200),
    user_id                        NUMERIC(10) NOT NULL
 );

COMMENT ON TABLE t_cm_enc_plan_request IS 'This table contains replenishment requests';
COMMENT ON COLUMN t_cm_enc_plan_request.description IS 'Description';
COMMENT ON COLUMN t_cm_enc_plan_request.id IS 'Unique ID identifying request';
COMMENT ON COLUMN t_cm_enc_plan_request.name IS 'Name';
COMMENT ON COLUMN t_cm_enc_plan_request.request_date IS 'Date of request';
COMMENT ON COLUMN t_cm_enc_plan_request.user_id IS 'ID if user created request';

CREATE UNIQUE INDEX i_cm_enc_plan_request_id ON t_cm_enc_plan_request (
    id                              ASC
);

ALTER TABLE t_cm_enc_plan_request ADD CONSTRAINT pk_cm_enc_plan_request PRIMARY KEY (id);
