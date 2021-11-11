DROP TABLE IF EXISTS tr_tasks_categories;
DROP TABLE IF EXISTS tz_tasks;
DROP TABLE IF EXISTS tz_users;
DROP TABLE IF EXISTS tz_roles;
DROP TABLE IF EXISTS tz_categories;

DROP SEQUENCE IF EXISTS tz_tasks_id_seq;
DROP SEQUENCE IF EXISTS tz_roles_id_seq;
DROP SEQUENCE IF EXISTS tz_users_id_seq;
DROP SEQUENCE IF EXISTS tz_categories_id_seq;

CREATE TABLE tz_roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE tz_categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE tz_users (
    id SERIAL PRIMARY KEY,
    id_role INTEGER DEFAULT -1 REFERENCES tz_roles (id) ON DELETE CASCADE,
    login VARCHAR(120) NOT NULL UNIQUE,
    name VARCHAR(250) NOT NULL,
    password VARCHAR(40) NOT NULL
);

CREATE TABLE tz_tasks (
    id SERIAL PRIMARY KEY,
    id_author INTEGER NOT NULL REFERENCES tz_users (id) ON DELETE CASCADE,
    done SMALLINT DEFAULT 0,
    description TEXT NOT NULL,
    created TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE tr_tasks_categories (
    id_task INTEGER NOT NULL REFERENCES tz_tasks (id) ON DELETE CASCADE,
    id_category INTEGER NOT NULL REFERENCES tz_categories (id) ON DELETE CASCADE,
    PRIMARY KEY (id_task, id_category)
);