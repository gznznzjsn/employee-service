package com.gznznzjsn.employeeservice.commandapi.event.handler;

import com.gznznzjsn.employeeservice.commandapi.event.EmployeeCreatedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.EmployeeDeletedEvent;
import com.gznznzjsn.employeeservice.core.model.Employee;
import com.gznznzjsn.employeeservice.core.model.Glossary;
import com.gznznzjsn.employeeservice.core.persistence.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EmployeeEventHandler {

    private final EmployeeRepository repository;

    @EventHandler
    public void on(EmployeeCreatedEvent event) {
        Mono.just(event)
                .flatMap(e -> repository.save(Employee.builder()
                        .id(event.getEmployeeId())
                        .glossary(Glossary.builder().id(event.getGlossaryId()).build())
                        .name(event.getName())
                        .specialization(event.getSpecialization())
                        .isNew(true)
                        .build()))
                .subscribe();
    }

    @EventHandler
    public void on(EmployeeDeletedEvent event) {
        Mono.just(event)
                .flatMap(e -> repository.deleteByGlossary_IdAndId(event.getGlossaryId(), event.getEmployeeId()))
                .subscribe();
    }

}
