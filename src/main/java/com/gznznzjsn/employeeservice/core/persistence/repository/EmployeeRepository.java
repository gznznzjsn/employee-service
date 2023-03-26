package com.gznznzjsn.employeeservice.core.persistence.repository;

import com.gznznzjsn.employeeservice.core.model.Employee;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EmployeeRepository extends R2dbcRepository<Employee, UUID> {

    Mono<Void> deleteByGlossary_IdAndId(UUID glossaryID, UUID employeeId);

    @Query("""
            SELECT
            employees.employee_id,
             employees.glossary_id,
             employees.name,
             employees.specialization
             FROM employees
             WHERE employees.glossary_id = $1
            """)
    Flux<Employee> findAllByGlossary_Id(UUID glossaryId);

    @Query("""
            SELECT
            employees.employee_id,
             employees.glossary_id,
             employees.name,
             employees.specialization
             FROM employees
             WHERE employees.glossary_id = $1
             AND employees.employee_id = $2
            """)
    Mono<Employee> findByGlossary_IdAndId(UUID glossaryID, UUID employeeId);


}
