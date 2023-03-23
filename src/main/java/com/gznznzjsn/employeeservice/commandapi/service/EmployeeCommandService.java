package com.gznznzjsn.employeeservice.commandapi.service;

import com.gznznzjsn.employeeservice.commandapi.command.CreateEmployeeCommand;
import com.gznznzjsn.employeeservice.commandapi.command.DeleteEmployeeCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EmployeeCommandService {

    Mono<UUID> createEmployee(CreateEmployeeCommand command);

    Mono<UUID> delete(DeleteEmployeeCommand command);

}
