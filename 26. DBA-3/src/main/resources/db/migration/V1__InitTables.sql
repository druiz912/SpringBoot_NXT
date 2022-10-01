CREATE TABLE person(
    id BIGSERIAL PRIMARY KEY,
    "name" TEXT NOT NULL,
    surname TEXT NOT NULL,
    mail TEXT NOT NULL,
    unique (name)
);