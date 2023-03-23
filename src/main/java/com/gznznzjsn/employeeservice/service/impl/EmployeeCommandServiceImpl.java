package com.gznznzjsn.employeeservice.service.impl;

import com.gznznzjsn.employeeservice.commandapi.command.CreateEmployeeCommand;
import com.gznznzjsn.employeeservice.service.EmployeeCommandService;
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
    public Mono<UUID> createEmployee(CreateEmployeeCommand command) {
        return commandGateway.send(command);
    }

}
