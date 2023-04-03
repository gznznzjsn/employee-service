package com.gznznzjsn.employeeservice.commandapi.event.handler.impl;

import com.gznznzjsn.employeeservice.commandapi.event.EmployeeCreatedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.EmployeeDeletedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.handler.EmployeeEventHandler;
import com.gznznzjsn.employeeservice.core.model.Employee;
import com.gznznzjsn.employeeservice.core.model.Glossary;
import com.gznznzjsn.employeeservice.core.persistence.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EmployeeEventHandlerImpl implements EmployeeEventHandler {

    private final EmployeeRepository repository;

    @Override
    public void on(EmployeeCreatedEvent event) {
        Mono.just(event)
                .flatMap(e -> repository.save(Employee.builder()
                        .id(event.getEmployeeId())
                        .glossary(Glossary.builder()
                                .id(event.getGlossaryId())
                                .build())
                        .name(event.getName())
                        .specialization(event.getSpecialization())
                        .isNew(true)
                        .build()))
                .subscribe();
    }

    @Override
    public void on(EmployeeDeletedEvent event) {
        Mono.just(event)
                .flatMap(e -> repository.deleteByGlossaryIdAndId(
                        event.getGlossaryId(),
                        event.getEmployeeId()
                ))
                .subscribe();
    }

}
