INSERT INTO tz_roles (id, name) VALUES
(-2, 'Администратор'), (-1, 'Пользователь');

INSERT INTO tz_users (id_role, login, name, password) VALUES
(-2, 'sysdba', 'Власов Александр Сергеевич', 'AB4154A7C451F56E9B7FF1537758DDD0C619F8BE');

INSERT INTO tz_tasks (id_author, done, description, created) VALUES
(1, 1, 'Обновление структуры базы данных', '2021-10-17');