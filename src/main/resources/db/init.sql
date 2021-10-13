DROP TABLE IF EXISTS tz_tasks;
DROP SEQUENCE IF EXISTS tz_tasks_id_seq;

CREATE TABLE tz_tasks (
    id SERIAL PRIMARY KEY,
    done SMALLINT DEFAULT 0,
    description TEXT NOT NULL,
    created DATE DEFAULT current_date
);