package com.gznznzjsn.employeeservice.queryapi.query.service.impl;

import com.gznznzjsn.employeeservice.core.model.Employee;
import com.gznznzjsn.employeeservice.queryapi.query.GetAllEmployeesQuery;
import com.gznznzjsn.employeeservice.queryapi.query.GetEmployeeByIdQuery;
import com.gznznzjsn.employeeservice.queryapi.query.service.EmployeeQueryService;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeQueryServiceImpl implements EmployeeQueryService {

    private final ReactorQueryGateway queryGateway;

    @Override
    public Flux<Employee> getAll(GetAllEmployeesQuery getAllEmployeesQuery) {
        return queryGateway.query(getAllEmployeesQuery, ResponseTypes.multipleInstancesOf(Employee.class)).flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Employee> get(GetEmployeeByIdQuery getEmployeeByIdQuery) {
        return queryGateway.query(getEmployeeByIdQuery, Employee.class);
    }

}
