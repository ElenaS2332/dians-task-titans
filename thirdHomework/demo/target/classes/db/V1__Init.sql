CREATE TABLE wineries (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100),
                          latitude DOUBLE PRECISION,
                          longitude DOUBLE PRECISION,
                          location VARCHAR(100),
                          image_url VARCHAR(100),
                          description VARCHAR(1000),
                          image VARCHAR(255)
);

CREATE TABLE reviews (
                         id SERIAL PRIMARY KEY,
                         score INT,
                         comment VARCHAR(255),
                         winery_id BIGINT REFERENCES wineries(id)
);

CREATE TABLE users (
                       id INT PRIMARY KEY ,
                       uid INT UNIQUE,
                       name VARCHAR(100),
                       surname VARCHAR(100),
                       password VARCHAR(100),
                       username VARCHAR(255),
                       fullname VARCHAR(255)
);