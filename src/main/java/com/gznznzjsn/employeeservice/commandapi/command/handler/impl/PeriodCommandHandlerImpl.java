package com.gznznzjsn.employeeservice.commandapi.command.handler.impl;

import com.gznznzjsn.employeeservice.commandapi.aggregate.GlossaryAggregate;
import com.gznznzjsn.employeeservice.commandapi.aggregate.PeriodEntity;
import com.gznznzjsn.employeeservice.commandapi.command.PeriodEraseAppropriateCommand;
import com.gznznzjsn.employeeservice.commandapi.command.handler.PeriodCommandHandler;
import com.gznznzjsn.employeeservice.commandapi.event.PeriodDeletedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.PeriodUpdatedEvent;
import com.gznznzjsn.employeeservice.core.model.Specialization;
import com.gznznzjsn.employeeservice.core.model.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;

@Component
@RequiredArgsConstructor
public class PeriodCommandHandlerImpl implements PeriodCommandHandler {

    private final Repository<GlossaryAggregate> repository;

    @Override
    public void handle(final PeriodEraseAppropriateCommand command) {
        repository.load(command.getGlossaryId().toString())
                .execute(glossaryAggregate -> {
                    Specialization specialization = command.getSpecialization();
                    LocalDateTime time = command.getArrivalTime();
                    Integer duration = command.getDuration();
                    PeriodEntity period = glossaryAggregate.getEmployeeMap()
                            .values().stream()
                            .filter(
                                    e -> e.getSpecialization()
                                            .equals(specialization)
                            )
                            .flatMap(e -> e.getPeriods().values().stream())
                            .filter(
                                    p -> p.getDate()
                                            .isAfter(time.toLocalDate())
                            )
                            .filter(
                                    p -> p.getEnd() - p.getStart()
                                         >= command.getDuration()
                            )
                            .min(
                                    Comparator.comparing(PeriodEntity::getDate)
                                            .thenComparing(
                                                    PeriodEntity::getStart
                                            )
                            )
                            .orElseThrow(
                                    () -> new ResourceNotFoundException(
                                            "No free time periods for such "
                                            + "parameters: arrival time = "
                                            + time
                                            + ", specialization = "
                                            + specialization.name()
                                            + ", total assignment duration = "
                                            + command.getDuration()
                                    )
                            );
                    if (period.getEnd() - period.getStart() == duration) {
                        AggregateLifecycle.apply(new PeriodDeletedEvent(
                                period.getPeriodId()
                        ));
                    } else {
                        AggregateLifecycle.apply(new PeriodUpdatedEvent(
                                period.getPeriodId(),
                                period.getDate(),
                                period.getStart() + duration,
                                period.getEnd()
                        ));
                    }
                });
    }


}
