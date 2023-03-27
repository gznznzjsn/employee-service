package com.gznznzjsn.employeeservice.commandapi.aggregate;

import com.gznznzjsn.employeeservice.commandapi.command.EmployeeCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.command.EmployeeDeleteCommand;
import com.gznznzjsn.employeeservice.commandapi.command.GlossaryCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.command.PeriodEraseAppropriateCommand;
import com.gznznzjsn.employeeservice.commandapi.event.*;
import com.gznznzjsn.employeeservice.core.model.Specialization;
import com.gznznzjsn.employeeservice.core.model.exception.ResourceNotFoundException;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;
import java.util.Comparator;
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

    @CommandHandler
    public void handle(PeriodEraseAppropriateCommand command) {
        Specialization specialization = command.getSpecialization();
        LocalDateTime arrivalTime = command.getArrivalTime();
        Integer totalDuration = command.getTotalDuration();
        PeriodEntity period = employees.values().stream()
                .filter(e -> e.getSpecialization().equals(specialization))
                .flatMap(e -> e.getPeriods().values().stream())
                .filter(p -> p.getDate().isAfter(arrivalTime.toLocalDate()))
                .filter(p -> p.getEnd() - p.getStart() >= command.getTotalDuration())
                .min(Comparator.comparing(PeriodEntity::getDate).thenComparing(PeriodEntity::getStart))
                .orElseThrow(() ->
                        new ResourceNotFoundException("No free time periods for such parameters: arrival time = " + arrivalTime + ", specialization = " + specialization.name() + ", total assignment duration = " + command.getTotalDuration())
                );
        if (period.getEnd() - period.getStart() == totalDuration) {
            AggregateLifecycle.apply(new PeriodDeletedEvent(
                    period.getPeriodId()
            ));
        } else {
            AggregateLifecycle.apply(new PeriodUpdatedEvent(
                    period.getPeriodId(),
                    period.getDate(),
                    period.getStart() + totalDuration,
                    period.getEnd()
            ));
        }
    }

    @EventSourcingHandler
    public void on(PeriodDeletedEvent event) {
        EmployeeEntity employee = employees.values().stream()
                .filter(e -> e.getPeriods().containsKey(event.getPeriodId()))
                .findAny()
                .orElseThrow(() ->
                        new ResourceNotFoundException("No periods with id = " + event.getPeriodId()));
        employee.getPeriods().remove(event.getPeriodId());
    }

    @EventSourcingHandler
    public void on(PeriodUpdatedEvent event) {
        EmployeeEntity employee = employees.values().stream()
                .filter(e -> e.getPeriods().containsKey(event.getPeriodId()))
                .findAny()
                .orElseThrow(() ->
                        new ResourceNotFoundException("No periods with id = " + event.getPeriodId()));
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
