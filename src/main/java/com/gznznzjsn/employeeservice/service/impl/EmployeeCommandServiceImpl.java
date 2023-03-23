package com.gznznzjsn.employeeservice.service.impl;

import com.gznznzjsn.employeeservice.command.model.CreateEmployeeCommand;
import com.gznznzjsn.employeeservice.service.EmployeeCommandService;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeCommandServiceImpl implements EmployeeCommandService {

    private final ReactorCommandGateway commandGateway;

    @Override
    public Mono<String> createEmployee(CreateEmployeeCommand command) {
        return commandGateway.send(command);
    }

}
