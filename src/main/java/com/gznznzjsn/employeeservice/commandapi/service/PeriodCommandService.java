package com.gznznzjsn.employeeservice.commandapi.service;

import com.gznznzjsn.employeeservice.commandapi.command.PeriodCreateCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PeriodCommandService {

    Mono<UUID> create(PeriodCreateCommand command);

}
