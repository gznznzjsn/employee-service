package com.gznznzjsn.employeeservice.queryapi.handler;

import com.gznznzjsn.employeeservice.domain.Employee;
import com.gznznzjsn.employeeservice.domain.exception.ResourceNotFoundException;
import com.gznznzjsn.employeeservice.persistence.repository.EmployeeRepository;
import com.gznznzjsn.employeeservice.queryapi.query.GetAllEmployeesQuery;
import com.gznznzjsn.employeeservice.queryapi.query.GetEmployeeByIdQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeQueryHandler {

    private final EmployeeRepository repository;

    @QueryHandler
    public List<Employee> handle(GetAllEmployeesQuery getAllEmployeesQuery) {
        return repository.findAll().collectList().block();
    }

    @QueryHandler
    public Employee handle(GetEmployeeByIdQuery getEmployeeByIdQuery) {
        return repository
                .findById(getEmployeeByIdQuery.getEmployeeId())
                .switchIfEmpty(
                        Mono.error(
                                new ResourceNotFoundException("Employee with id=" + getEmployeeByIdQuery.getEmployeeId() + " not found!")
                        )
                ).block();
    }

}
