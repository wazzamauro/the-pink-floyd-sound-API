DROP TABLE people IF EXISTS;
DROP TABLE musician IF EXISTS;


CREATE TABLE people  (
    people_id BIGINT IDENTITY NOT NULL PRIMARY KEY, 
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    age INTEGER
);

CREATE TABLE musician  (
    musician_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    age INTEGER,
    role VARCHAR(20),
);