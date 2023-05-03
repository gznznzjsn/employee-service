package com.gznznzjsn.employeeservice.queryapi.query.handler.impl;

import com.gznznzjsn.employeeservice.core.model.Employee;
import com.gznznzjsn.employeeservice.core.model.exception.ResourceNotFoundException;
import com.gznznzjsn.employeeservice.core.persistence.repository.EmployeeRepository;
import com.gznznzjsn.employeeservice.queryapi.query.GetAllEmployeesQuery;
import com.gznznzjsn.employeeservice.queryapi.query.GetEmployeeByIdQuery;
import com.gznznzjsn.employeeservice.queryapi.query.handler.EmployeeQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EmployeeQueryHandlerImpl implements EmployeeQueryHandler {

    private final EmployeeRepository repository;

    @Override
    public Flux<Employee> handle(final GetAllEmployeesQuery query) {
        return repository.findAllByGlossaryId(query.getGlossaryId());
    }

    @Override
    public Mono<Employee> handle(final GetEmployeeByIdQuery query) {
        return repository
                .findByGlossaryIdAndId(
                        query.getGlossaryId(),
                        query.getEmployeeId()
                )
                .switchIfEmpty(
                        Mono.error(new ResourceNotFoundException(
                                "Employee with id = "
                                + query.getEmployeeId()
                                + " in glossary with id = "
                                + query.getGlossaryId()
                                + " not found!"
                        ))
                );
    }

}
