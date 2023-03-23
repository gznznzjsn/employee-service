--liquibase formatted sql

--changeset gznznzjsn:create-employees
create table employees
(
    employee_id    uuid primary key,
    specialization varchar(40),
    "name"         varchar(40) not null

);
--rollback drop table employees;