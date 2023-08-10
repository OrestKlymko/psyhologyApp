CREATE TABLE customers
(
    id       BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(50)  NOT NULL,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE questions
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL ,
    question    VARCHAR(255),
    response VARCHAR(256),
    FOREIGN KEY (customer_id) REFERENCES customers (id)
)

