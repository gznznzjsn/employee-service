--liquibase formatted sql

--changeset gznznzjsn:fulfill-employees
insert into employees (employee_id,name, specialization)
values ('9080a70f-a6ab-4848-abd7-e1ecb9d2c5d8','employee_A', 'CLEANER'),
       ('36d717dd-742d-408f-bac6-288ab85f5389','emloyee_B', 'REPAIRER'),
       ('d41d06f0-33e4-4d08-87f8-d6ff24a7e41b','emloyee_C', 'CLEANER');

--changeset gznznzjsn:fulfill-periods
insert into periods (employee_id, period_date, period_start, period_end)
values ('9080a70f-a6ab-4848-abd7-e1ecb9d2c5d8', '2023-01-11', 8, 20),
       ('36d717dd-742d-408f-bac6-288ab85f5389', '2023-01-11', 8, 20),
       ('36d717dd-742d-408f-bac6-288ab85f5389', '2023-01-12', 8, 20),
       ('36d717dd-742d-408f-bac6-288ab85f5389', '2023-01-13', 8, 20),
       ('d41d06f0-33e4-4d08-87f8-d6ff24a7e41b', '2023-01-11', 8, 20),
       ('36d717dd-742d-408f-bac6-288ab85f5389', '2023-01-20', 8, 20);