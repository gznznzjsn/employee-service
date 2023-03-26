package com.gznznzjsn.employeeservice.commandapi.service;

import com.gznznzjsn.employeeservice.commandapi.command.EmployeeCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.command.EmployeeDeleteCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EmployeeCommandService {

    Mono<UUID> createEmployee(EmployeeCreateCommand command);

    Mono<UUID> delete(EmployeeDeleteCommand command);

}
