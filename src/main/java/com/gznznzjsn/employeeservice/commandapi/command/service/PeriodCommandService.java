package com.gznznzjsn.employeeservice.commandapi.command.service;

import com.gznznzjsn.employeeservice.commandapi.command.PeriodCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.command.PeriodEraseAppropriateCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PeriodCommandService {

    Mono<UUID> create(PeriodCreateCommand command);

    Mono<UUID> eraseAppropriate(PeriodEraseAppropriateCommand command);

}
