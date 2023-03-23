package com.gznznzjsn.employeeservice.service;

import com.gznznzjsn.employeeservice.commandapi.command.CreateEmployeeCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EmployeeCommandService {

    Mono<UUID> createEmployee(CreateEmployeeCommand command);

}
