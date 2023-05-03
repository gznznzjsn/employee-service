package com.gznznzjsn.employeeservice.commandapi.command.handler.impl;

import com.gznznzjsn.employeeservice.commandapi.aggregate.GlossaryAggregate;
import com.gznznzjsn.employeeservice.commandapi.command.EmployeeCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.command.EmployeeDeleteCommand;
import com.gznznzjsn.employeeservice.commandapi.command.handler.EmployeeCommandHandler;
import com.gznznzjsn.employeeservice.commandapi.event.EmployeeCreatedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.EmployeeDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmployeeCommandHandlerImpl implements EmployeeCommandHandler {

    private final Repository<GlossaryAggregate> repository;

    @Override
    public void handle(final EmployeeDeleteCommand command) {
        repository.load(command.getGlossaryId().toString())
                .execute(glossaryAggregate -> AggregateLifecycle.apply(
                        new EmployeeDeletedEvent(
                                command.getGlossaryId(),
                                command.getEmployeeId()
                        )
                ));
    }

    @Override
    public void handle(final EmployeeCreateCommand command) {
        repository.load(command.getGlossaryId().toString())
                .execute(glossaryAggregate -> AggregateLifecycle.apply(
                        new EmployeeCreatedEvent(
                                command.getGlossaryId(),
                                UUID.randomUUID(),
                                command.getName(),
                                command.getSpecialization(),
                                command.getInventoryId()
                        )
                ));
    }

}
