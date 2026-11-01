CREATE TABLE orders
(
    id                 UUID PRIMARY KEY,
    firstname          VARCHAR(255) NOT NULL,
    lastname           VARCHAR(255) NOT NULL,
    email              VARCHAR(255) NOT NULL,
    street             VARCHAR(255) NOT NULL,
    city               VARCHAR(255) NOT NULL,
    zip                VARCHAR(20)  NOT NULL,
    state              VARCHAR(255) NOT NULL,
    delivery_date      DATE,
    "invoice_id"       UUID,
    "mode_of_dispatch" VARCHAR(255)
);

CREATE TABLE items
(
    id    UUID PRIMARY KEY,
    name  VARCHAR(255),
    price DECIMAL(10, 2),
    image VARCHAR(255)
);

CREATE TABLE order_items
(
    id       UUID PRIMARY KEY,
    order_id UUID    NOT NULL,
    item_id  UUID    NOT NULL,
    quantity INTEGER NOT NULL,
    ready    BOOLEAN NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items (id) ON DELETE CASCADE
);