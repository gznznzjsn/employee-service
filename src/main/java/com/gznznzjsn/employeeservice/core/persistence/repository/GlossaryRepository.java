package com.gznznzjsn.employeeservice.core.persistence.repository;

import com.gznznzjsn.employeeservice.core.model.Glossary;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface GlossaryRepository extends R2dbcRepository<Glossary, UUID> {
}
