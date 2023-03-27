package com.gznznzjsn.employeeservice.commandapi.service;

import com.gznznzjsn.employeeservice.commandapi.command.GlossaryCreateCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface GlossaryCommandService {

    Mono<UUID> createGlossary(GlossaryCreateCommand command);

}
