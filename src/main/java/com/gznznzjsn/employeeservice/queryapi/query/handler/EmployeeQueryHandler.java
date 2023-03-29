package com.gznznzjsn.employeeservice.queryapi.query.handler;

import com.gznznzjsn.employeeservice.core.model.Employee;
import com.gznznzjsn.employeeservice.core.model.exception.ResourceNotFoundException;
import com.gznznzjsn.employeeservice.core.persistence.repository.EmployeeRepository;
import com.gznznzjsn.employeeservice.queryapi.query.GetAllEmployeesQuery;
import com.gznznzjsn.employeeservice.queryapi.query.GetEmployeeByIdQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EmployeeQueryHandler {

    private final EmployeeRepository repository;

    public Flux<Employee> handle(GetAllEmployeesQuery query) {
        return repository.findAllByGlossaryId(query.getGlossaryId());
    }

    public Mono<Employee> handle(GetEmployeeByIdQuery query) {
        return repository
                .findByGlossaryIdAndId(query.getGlossaryId(), query.getEmployeeId())
                .switchIfEmpty(
                        Mono.error(new ResourceNotFoundException(
                                "Employee with id = " + query.getEmployeeId()
                                + " in glossary with id = " + query.getGlossaryId()
                                + " not found!"
                        ))
                );
    }

}
