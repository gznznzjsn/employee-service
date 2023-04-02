package com.gznznzjsn.employeeservice.queryapi.service.impl;

import com.gznznzjsn.employeeservice.core.model.Employee;
import com.gznznzjsn.employeeservice.queryapi.query.GetAllEmployeesQuery;
import com.gznznzjsn.employeeservice.queryapi.query.GetEmployeeByIdQuery;
import com.gznznzjsn.employeeservice.queryapi.query.handler.EmployeeQueryHandler;
import com.gznznzjsn.employeeservice.queryapi.service.EmployeeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeQueryServiceImpl implements EmployeeQueryService {

    private final EmployeeQueryHandler queryHandler;

    @Override
    public Flux<Employee> getAll(GetAllEmployeesQuery getAllEmployeesQuery) {
        return queryHandler.handle(getAllEmployeesQuery);
    }

    @Override
    public Mono<Employee> get(GetEmployeeByIdQuery getEmployeeByIdQuery) {
        return queryHandler.handle(getEmployeeByIdQuery);
    }

}
