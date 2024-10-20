CREATE TABLE invoices
(
    id         UUID PRIMARY KEY,
    "order_id" VARCHAR(255),
    amount     BIGINT,
    state      VARCHAR(255)
);