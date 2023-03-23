package com.gznznzjsn.employeeservice.service;

import com.gznznzjsn.employeeservice.command.model.CreateEmployeeCommand;
import reactor.core.publisher.Mono;

public interface EmployeeCommandService {

    Mono<String> createEmployee(CreateEmployeeCommand command);

}
