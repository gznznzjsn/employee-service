package com.gznznzjsn.employeeservice.cqrsdemo.aggregate;

import com.gznznzjsn.employeeservice.command.model.CreateEmployeeCommand;
import com.gznznzjsn.employeeservice.cqrsdemo.event.EmployeeCreatedEvent;
import com.gznznzjsn.employeeservice.domain.Specialization;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
@Slf4j
public class EmployeeAggregate {

    @AggregateIdentifier
    private String uuid;
    private String name;
    private Specialization specialization;

    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand command) {
        log.info("CreateEmployeeCommand received. ");
        AggregateLifecycle.apply(new EmployeeCreatedEvent(
                command.getEmployeeId(),
                command.getName(),
                command.getSpecialization()
        ));
    }

    @EventSourcingHandler
    public void on(EmployeeCreatedEvent event) {
        log.info("An EmployeeCreatedEvent occurred.");
        this.uuid = event.getUuid();
        this.name = event.getName();
        this.specialization = event.getSpecialization();
    }

}
