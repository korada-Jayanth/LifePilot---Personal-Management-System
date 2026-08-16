CREATE TABLE goals (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    start_date DATE NOT NULL,
    target_date DATE NOT NULL,
    status VARCHAR(30) NOT NULL,
    user_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,

    PRIMARY KEY (id),

    CONSTRAINT fk_goals_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);