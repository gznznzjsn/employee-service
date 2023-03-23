package com.gznznzjsn.employeeservice.commandapi.aggregate;

import com.gznznzjsn.employeeservice.commandapi.command.CreateEmployeeCommand;
import com.gznznzjsn.employeeservice.commandapi.command.DeleteEmployeeCommand;
import com.gznznzjsn.employeeservice.commandapi.event.EmployeeCreatedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.EmployeeDeletedEvent;
import com.gznznzjsn.employeeservice.domain.Specialization;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class EmployeeAggregate {

    @AggregateIdentifier
    private UUID id;
    private String name;
    private Specialization specialization;

    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand command) {
        AggregateLifecycle.apply(new EmployeeCreatedEvent(
                command.getId(),
                command.getName(),
                command.getSpecialization()
        ));
    }

    @CommandHandler
    public void handle(DeleteEmployeeCommand command) {
        AggregateLifecycle.apply(new EmployeeDeletedEvent(
                command.getId()
        ));
    }

    @EventSourcingHandler
    public void on(EmployeeCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.specialization = event.getSpecialization();
    }

    @EventSourcingHandler
    public void on(EmployeeDeletedEvent event) {
        AggregateLifecycle.markDeleted();
    }

}
