CREATE TABLE tasks (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    status VARCHAR(20) NOT NULL DEFAULT 'TODO',
    priority VARCHAR(20) NOT NULL DEFAULT 'MEDIUM',
    due_date DATETIME NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    user_id BIGINT NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_tasks_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_tasks_user_id
    ON tasks(user_id);

CREATE INDEX idx_tasks_status
    ON tasks(status);

CREATE INDEX idx_tasks_due_date
    ON tasks(due_date);