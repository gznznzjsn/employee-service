package com.gznznzjsn.employeeservice.commandapi.event.handler;

import com.gznznzjsn.employeeservice.commandapi.event.PeriodCreatedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.PeriodDeletedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.PeriodUpdatedEvent;
import com.gznznzjsn.employeeservice.core.model.Employee;
import com.gznznzjsn.employeeservice.core.model.Period;
import com.gznznzjsn.employeeservice.core.model.exception.ResourceNotFoundException;
import com.gznznzjsn.employeeservice.core.persistence.repository.PeriodRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PeriodEventHandler {

    private final PeriodRepository repository;

    @EventHandler
    public void on(PeriodCreatedEvent event) {
        Mono.just(event)
                .flatMap(e -> repository.save(
                                Period.builder()
                                        .id(event.getPeriodId())
                                        .employee(Employee.builder()
                                                .id(event.getEmployeeId())
                                                .build())
                                        .date(event.getDate())
                                        .start(event.getStart())
                                        .end(event.getEnd())
                                        .isNew(true)
                                        .build()
                        )
                ).subscribe();
    }

    @EventHandler
    public void on(PeriodDeletedEvent event) {
        repository.deleteById(event.getPeriodId()).subscribe();
    }

    @EventHandler
    public void on(PeriodUpdatedEvent event) {
        get(event.getPeriodId())
                .map(periodFromRepository -> {
                    if (event.getDate() != null) {
                        periodFromRepository.setDate(event.getDate());
                    }
                    if (event.getStart() != null) {
                        periodFromRepository.setStart(event.getStart());
                    }
                    if (event.getEnd() != null) {
                        periodFromRepository.setEnd(event.getEnd());
                    }
                    return periodFromRepository;
                })
                .flatMap(repository::save)
                .subscribe();
    }

    public Mono<Period> get(UUID periodId) {
        return repository.findById(periodId)
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException(
                                "Periods with id = " + periodId + " not found!"
                        ))
                );
    }

}
