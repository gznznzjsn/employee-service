package com.gznznzjsn.employeeservice.queryapi.service;

import com.gznznzjsn.employeeservice.core.model.Employee;
import com.gznznzjsn.employeeservice.queryapi.query.GetAllEmployeesQuery;
import com.gznznzjsn.employeeservice.queryapi.query.GetEmployeeByIdQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeQueryService {

    Flux<Employee> getAll(GetAllEmployeesQuery getAllEmployeesQuery);

    Mono<Employee> get(GetEmployeeByIdQuery getEmployeeByIdQuery);

}
