package com.gznznzjsn.employeeservice.repository;

import com.gznznzjsn.employeeservice.domain.Employee;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends R2dbcRepository<Employee, Long> {

//    Mono<Employee> save(Employee employee);



}
