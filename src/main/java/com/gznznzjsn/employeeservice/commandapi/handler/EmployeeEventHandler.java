package com.gznznzjsn.employeeservice.commandapi.handler;

import com.gznznzjsn.employeeservice.commandapi.event.EmployeeCreatedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.EmployeeDeletedEvent;
import com.gznznzjsn.employeeservice.domain.Employee;
import com.gznznzjsn.employeeservice.persistence.repository.EmployeeRepository;
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
                        .name(event.getName())
                        .specialization(event.getSpecialization())
                        .build()))
                .subscribe();
    }

    @EventHandler
    public void on(EmployeeDeletedEvent event) {
        Mono.just(event)
                .flatMap(e -> repository.deleteById(event.getEmployeeId()))
                .subscribe();
    }

}
