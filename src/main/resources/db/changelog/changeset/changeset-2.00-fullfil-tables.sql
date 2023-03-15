--liquibase formatted sql

--changeset gznznzjsn:fulfill-employees
insert into employees (name, specialization)
values ('employee_A', 'CLEANER'),
       ('emloyee_B', 'REPAIRER'),
       ('emloyee_C', 'CLEANER');

--changeset gznznzjsn:fulfill-periods
insert into periods (employee_id, period_date, period_start, period_end)
values (1, '2023-01-11', 8, 20),
       (2, '2023-01-11', 8, 20),
       (2, '2023-01-12', 8, 20),
       (2, '2023-01-13', 8, 20),
       (3, '2023-01-11', 8, 20),
       (2, '2023-01-20', 8, 20);