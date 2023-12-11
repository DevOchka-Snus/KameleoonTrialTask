
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    expiration_date TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS quotes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    rating BIGINT,
    content VARCHAR(1000) NOT NULL,
    expiration_date TIMESTAMP,
    updating_date TIMESTAMP,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS votes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(255) NOT NULL,
    user_id BIGINT,
    quote_id BIGINT,
    expiration_date TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (quote_id) REFERENCES quotes(id)
    );
