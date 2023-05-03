package com.gznznzjsn.employeeservice.commandapi.service.impl;

import com.gznznzjsn.employeeservice.commandapi.command.GlossaryCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.service.GlossaryCommandService;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GlossaryCommandServiceImpl implements GlossaryCommandService {

    private final ReactorCommandGateway commandGateway;

    @Override
    public Mono<UUID> create(final GlossaryCreateCommand command) {
        return commandGateway.send(command);
    }

}
