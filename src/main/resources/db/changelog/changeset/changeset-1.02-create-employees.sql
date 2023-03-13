--liquibase formatted sql

--changeset gznznzjsn:create-employees
create table employees
(
    employee_id    bigserial primary key,
    specialization varchar(40),
    "name"         varchar(40) not null

);
--rollback drop table employees;