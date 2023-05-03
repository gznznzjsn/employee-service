package com.gznznzjsn.employeeservice.commandapi.aggregate;

import com.gznznzjsn.employeeservice.commandapi.aggregate.sourcing.EmployeeSourcingHandler;
import com.gznznzjsn.employeeservice.commandapi.aggregate.sourcing.PeriodSourcingHandler;
import com.gznznzjsn.employeeservice.commandapi.command.GlossaryCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.event.EmployeeCreatedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.EmployeeDeletedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.GlossaryCreatedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.PeriodDeletedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.PeriodUpdatedEvent;
import com.gznznzjsn.employeeservice.core.model.exception.ResourceNotFoundException;
import lombok.Getter;
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

@Getter
@Aggregate
@NoArgsConstructor
public class GlossaryAggregate
        implements EmployeeSourcingHandler, PeriodSourcingHandler {

    @AggregateIdentifier
    private UUID glossaryId;

    @AggregateMember
    private Map<UUID, EmployeeEntity> employeeMap;

    @CommandHandler
    public GlossaryAggregate(final GlossaryCreateCommand command) {
        AggregateLifecycle.apply(new GlossaryCreatedEvent(
                UUID.randomUUID()
        ));
    }

    @EventSourcingHandler
    public void on(final GlossaryCreatedEvent event) {
        this.glossaryId = event.getGlossaryId();
        this.employeeMap = new HashMap<>();
    }


    @Override
    public void on(final EmployeeCreatedEvent event) {
        employeeMap.put(event.getEmployeeId(), new EmployeeEntity(
                event.getEmployeeId(),
                event.getName(),
                event.getSpecialization()
        ));
    }


    @Override
    public void on(final EmployeeDeletedEvent event) {
        employeeMap.remove(event.getEmployeeId());
    }

    @Override
    public void on(final PeriodDeletedEvent event) {
        EmployeeEntity employee = employeeMap.values().stream()
                .filter(e -> e.getPeriods().containsKey(event.getPeriodId()))
                .findAny()
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No periods with id = " + event.getPeriodId())
                );
        employee.getPeriods().remove(event.getPeriodId());
    }

    @Override
    public void on(final PeriodUpdatedEvent event) {
        EmployeeEntity employee = employeeMap.values().stream()
                .filter(e -> e.getPeriods().containsKey(event.getPeriodId()))
                .findAny()
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No periods with id = " + event.getPeriodId())
                );
        employee.getPeriods().replace(
                event.getPeriodId(),
                new PeriodEntity(
                        event.getPeriodId(),
                        event.getDate(),
                        event.getStart(), event.getEnd()
                )
        );
    }

}
