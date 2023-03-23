package com.gznznzjsn.employeeservice.cqrsdemo.projection;

import com.gznznzjsn.employeeservice.cqrsdemo.event.EmployeeCreatedEvent;
import com.gznznzjsn.employeeservice.domain.Employee;
import com.gznznzjsn.employeeservice.persistence.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmployeeProjection {

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

}
