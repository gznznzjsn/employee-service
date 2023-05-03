package com.gznznzjsn.employeeservice.commandapi.aggregate;

import com.gznznzjsn.employeeservice.commandapi.command.PeriodCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.event.PeriodCreatedEvent;
import com.gznznzjsn.employeeservice.core.model.Specialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.modelling.command.EntityId;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class EmployeeEntity {

    @EntityId
    private UUID employeeId;
    private String name;
    private Specialization specialization;

    @AggregateMember
    private final Map<UUID, PeriodEntity> periods = new HashMap<>();

    @CommandHandler
    public void handle(final PeriodCreateCommand command) {
        AggregateLifecycle.apply(new PeriodCreatedEvent(
                this.employeeId,
                UUID.randomUUID(),
                command.getDate(),
                command.getStart(),
                command.getEnd()

        ));
    }

    @EventSourcingHandler
    public void on(final PeriodCreatedEvent event) {
        this.periods.put(event.getPeriodId(), new PeriodEntity(
                event.getPeriodId(),
                event.getDate(),
                event.getStart(),
                event.getEnd()
        ));
    }

}
