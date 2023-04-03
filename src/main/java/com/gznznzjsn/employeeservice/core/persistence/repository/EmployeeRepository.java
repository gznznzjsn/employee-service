package com.gznznzjsn.employeeservice.core.persistence.repository;

import com.gznznzjsn.employeeservice.core.model.Employee;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EmployeeRepository extends R2dbcRepository<Employee, UUID> {

    Mono<Void> deleteByGlossaryIdAndId(UUID glossaryID, UUID employeeId);

    @Query("""
            SELECT
            employees.employee_id as employee_id,
             employees.glossary_id as glossary_id,
             employees.name as "name",
             employees.specialization as specialization
             FROM employees
             WHERE employees.glossary_id = $1
            """)
    Flux<Employee> findAllByGlossaryId(UUID glossaryId);

    @Query("""
            SELECT
            employees.employee_id as employee_id,
             employees.glossary_id as glossary_id,
             employees.name as "name",
             employees.specialization as specialization
             FROM employees
             WHERE employees.glossary_id = $1
             AND employees.employee_id = $2
            """)
    Mono<Employee> findByGlossaryIdAndId(UUID glossaryID, UUID employeeId);


}
