--liquibase formatted sql

--changeset gznznzjsn:create-glossaries
create table glossaries
(
    glossary_id uuid primary key
);
--rollback drop table glossaries;