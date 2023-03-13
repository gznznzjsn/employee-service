--liquibase formatted sql

--changeset gznznzjsn:fulfill-employees
insert into employees (name, specialization)
values ('employee_A', 'CLEANER'),
       ('emloyee_B', 'REPAIRER'),
       ('emloyee_C', 'CLEANER');