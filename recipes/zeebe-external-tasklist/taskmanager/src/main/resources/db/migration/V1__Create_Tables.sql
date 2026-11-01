CREATE TABLE IF NOT EXISTS user_task
(
    id                     BIGINT NOT NULL,
    element_id             VARCHAR(255),
    process_instance_key   BIGINT NOT NULL,
    bpmn_process_id        VARCHAR(255),
    process_definition_key BIGINT NOT NULL,
    variables              VARCHAR(255),
    expires_at             TIMESTAMP WITHOUT TIME ZONE,
    assignee               VARCHAR(255),
    task_state             VARCHAR(255),
    created_at             TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_user_task PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS process_variable
(
    id                     UUID PRIMARY KEY,
    process_instance_key   BIGINT NOT NULL,
    bpmn_process_id        VARCHAR(255),
    process_definition_key BIGINT,
    name                   VARCHAR(255) NOT NULL,
    value                  VARCHAR(255) NOT NULL
);