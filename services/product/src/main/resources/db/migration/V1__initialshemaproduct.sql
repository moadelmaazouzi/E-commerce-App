CREATE TABLE IF NOT EXISTS category (
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product (
   id INTEGER NOT NULL PRIMARY KEY,
   name VARCHAR(255),
    description VARCHAR(255),
    availableQuantity DOUBLE PRECISION,
    price DECIMAL(38, 2),
    category_id INTEGER,
    CONSTRAINT fk_cat FOREIGN KEY (category_id) REFERENCES category(id)
);



