package com.gznznzjsn.employeeservice.commandapi.service.impl;

import com.gznznzjsn.employeeservice.commandapi.command.EmployeeCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.command.EmployeeDeleteCommand;
import com.gznznzjsn.employeeservice.commandapi.service.EmployeeCommandService;
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
    public Mono<UUID> createEmployee(final EmployeeCreateCommand command) {
        return commandGateway.send(command);
    }

    @Override
    public Mono<UUID> delete(final EmployeeDeleteCommand command) {
        return commandGateway.send(command);
    }

}
