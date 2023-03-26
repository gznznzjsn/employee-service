package com.gznznzjsn.employeeservice.commandapi.aggregate;

import com.gznznzjsn.employeeservice.commandapi.command.EmployeeCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.command.EmployeeDeleteCommand;
import com.gznznzjsn.employeeservice.commandapi.command.GlossaryCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.event.EmployeeCreatedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.EmployeeDeletedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.GlossaryCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class Glossary {

    @AggregateIdentifier
    private UUID glossaryId;

    @AggregateMember
    private Map<UUID, EmployeeEntity> employees;

    @CommandHandler
    public Glossary(GlossaryCreateCommand command) {
        AggregateLifecycle.apply(new GlossaryCreatedEvent(
                UUID.randomUUID()
        ));
    }

    @EventSourcingHandler
    public void on(GlossaryCreatedEvent event) {
        this.glossaryId = event.getGlossaryId();
        this.employees = new HashMap<>();
    }

    @CommandHandler
    public void handle(EmployeeCreateCommand command) {
        AggregateLifecycle.apply(new EmployeeCreatedEvent(
                command.getGlossaryId(),
                UUID.randomUUID(),
                command.getName(),
                command.getSpecialization()
        ));
    }

    @EventSourcingHandler
    public void on(EmployeeCreatedEvent event) {
        employees.put(event.getEmployeeId(), new EmployeeEntity(
                event.getEmployeeId(),
                event.getName(),
                event.getSpecialization()
        ));
    }

    @CommandHandler
    public void handle(EmployeeDeleteCommand command) {
        AggregateLifecycle.apply(new EmployeeDeletedEvent(
                command.getGlossaryId(),
                command.getEmployeeId()
        ));
    }

    @EventSourcingHandler
    public void on(EmployeeDeletedEvent event) {
        employees.remove(event.getEmployeeId());
    }


}
