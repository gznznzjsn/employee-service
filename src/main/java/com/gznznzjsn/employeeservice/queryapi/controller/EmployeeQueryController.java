package com.gznznzjsn.employeeservice.queryapi.controller;


import com.gznznzjsn.employeeservice.queryapi.query.GetAllEmployeesQuery;
import com.gznznzjsn.employeeservice.queryapi.query.GetEmployeeByIdQuery;
import com.gznznzjsn.employeeservice.queryapi.service.EmployeeQueryService;
import com.gznznzjsn.employeeservice.web.dto.EmployeeDto;
import com.gznznzjsn.employeeservice.web.dto.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee-api/v1/employees")
public class EmployeeQueryController {

    private final EmployeeMapper employeeMapper;
    private final EmployeeQueryService employeeQueryService;

    @GetMapping
    public Flux<EmployeeDto> getAll() {
        return employeeQueryService.getAll(new GetAllEmployeesQuery())
                .map(employeeMapper::toDto);
    }

    @GetMapping("/{employeeId}")
    public Mono<EmployeeDto> get(@PathVariable Long employeeId) {
        return employeeQueryService
                .get(new GetEmployeeByIdQuery(employeeId))
                .map(employeeMapper::toDto);
    }

}
