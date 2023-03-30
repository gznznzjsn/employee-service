package com.gznznzjsn.employeeservice.queryapi.query.handler;

import com.gznznzjsn.employeeservice.core.model.Employee;
import com.gznznzjsn.employeeservice.queryapi.query.GetAllEmployeesQuery;
import com.gznznzjsn.employeeservice.queryapi.query.GetEmployeeByIdQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeQueryHandler {

    Flux<Employee> handle(GetAllEmployeesQuery query);

    Mono<Employee> handle(GetEmployeeByIdQuery query);

}
