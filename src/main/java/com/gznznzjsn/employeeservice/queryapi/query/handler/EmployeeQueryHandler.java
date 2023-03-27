package com.gznznzjsn.employeeservice.queryapi.query.handler;

import com.gznznzjsn.employeeservice.core.model.Employee;
import com.gznznzjsn.employeeservice.core.model.exception.ResourceNotFoundException;
import com.gznznzjsn.employeeservice.core.persistence.repository.EmployeeRepository;
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
    public List<Employee> handle(GetAllEmployeesQuery query) {
        return repository.findAllByGlossary_Id(query.getGlossaryId()).collectList().block();
    }

    @QueryHandler
    public Employee handle(GetEmployeeByIdQuery query) {
        return repository
                .findByGlossary_IdAndId(query.getGlossaryId(),query.getEmployeeId())
                .switchIfEmpty(
                        Mono.error(
                                new ResourceNotFoundException("Employee with id=" + query.getEmployeeId() + " not found!") //todo message
                        )
                ).block();
    }

}
