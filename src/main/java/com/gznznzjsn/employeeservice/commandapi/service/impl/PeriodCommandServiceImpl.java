package com.gznznzjsn.employeeservice.commandapi.service.impl;

import com.gznznzjsn.employeeservice.commandapi.command.PeriodCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.command.PeriodEraseAppropriateCommand;
import com.gznznzjsn.employeeservice.commandapi.service.PeriodCommandService;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PeriodCommandServiceImpl implements PeriodCommandService {

    private final ReactorCommandGateway gateway;

    @Override
    public Mono<UUID> create(final PeriodCreateCommand command) {
        return gateway.send(command);
    }

    @Override
    public Mono<UUID> eraseAppropriate(
            final PeriodEraseAppropriateCommand command
    ) {
        return gateway.send(command);
    }

}
