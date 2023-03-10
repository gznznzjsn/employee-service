package com.gznznzjsn.employeeservice.service;


import com.gznznzjsn.employeeservice.domain.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

    Flux<Employee> getAll();

    Mono<Employee> create(Employee employee);

    Mono<Employee> get(Long employeeId);

    Mono<Void> delete(Long employeeId);

}

