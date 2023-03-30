package com.gznznzjsn.employeeservice.queryapi.controller;


import com.gznznzjsn.employeeservice.queryapi.query.GetAllEmployeesQuery;
import com.gznznzjsn.employeeservice.queryapi.query.GetEmployeeByIdQuery;
import com.gznznzjsn.employeeservice.queryapi.service.EmployeeQueryService;
import com.gznznzjsn.employeeservice.core.web.dto.EmployeeDto;
import com.gznznzjsn.employeeservice.core.web.dto.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee-api/v1/glossaries/{glossaryId}/employees")
public class EmployeeQueryController {

    private final EmployeeMapper employeeMapper;
    private final EmployeeQueryService employeeQueryService;

    @GetMapping
    public Flux<EmployeeDto> getAll(@PathVariable UUID glossaryId) {
        return employeeQueryService.getAll(new GetAllEmployeesQuery(glossaryId))
                .map(employeeMapper::toDto);
    }

    @GetMapping("/{employeeId}")
    public Mono<EmployeeDto> get(
            @PathVariable UUID glossaryId,
            @PathVariable UUID employeeId
    ) {
        return employeeQueryService
                .get(new GetEmployeeByIdQuery(glossaryId, employeeId))
                .map(employeeMapper::toDto);
    }

}
