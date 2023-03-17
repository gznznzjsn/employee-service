package com.gznznzjsn.employeeservice.persistence.repository;

import com.gznznzjsn.employeeservice.domain.Employee;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface EmployeeRepository extends R2dbcRepository<Employee, Long> {
}
