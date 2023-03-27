package com.gznznzjsn.employeeservice.commandapi.command.service.impl;

import com.gznznzjsn.employeeservice.commandapi.command.EmployeeCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.command.EmployeeDeleteCommand;
import com.gznznzjsn.employeeservice.commandapi.command.service.EmployeeCommandService;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeCommandServiceImpl implements EmployeeCommandService {

    private final ReactorCommandGateway commandGateway;

    @Override
    public Mono<UUID> createEmployee(EmployeeCreateCommand command) {
        return commandGateway.send(command);
    }

    @Override
    public Mono<UUID> delete(EmployeeDeleteCommand command) {
        return commandGateway.send(command);
    }

}
