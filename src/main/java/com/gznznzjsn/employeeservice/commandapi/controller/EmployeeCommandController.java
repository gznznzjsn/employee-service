package com.gznznzjsn.employeeservice.commandapi.controller;


import com.gznznzjsn.employeeservice.commandapi.command.DeleteEmployeeCommand;
import com.gznznzjsn.employeeservice.commandapi.service.EmployeeCommandService;
import com.gznznzjsn.employeeservice.web.dto.EmployeeDto;
import com.gznznzjsn.employeeservice.web.dto.group.OnCreateEmployee;
import com.gznznzjsn.employeeservice.web.dto.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee-api/v1/employees")
public class EmployeeCommandController {

    private final EmployeeMapper employeeMapper;
    private final EmployeeCommandService employeeCommandService;

    @PostMapping
    public Mono<UUID> create(@Validated(OnCreateEmployee.class) @RequestBody EmployeeDto employeeDto) {
        return Mono.just(employeeDto)
                .map(employeeMapper::toCreateCommand)
                .flatMap(employeeCommandService::createEmployee);
    }

    @DeleteMapping("/{employeeId}")
    public Mono<UUID> delete(@PathVariable UUID employeeId) {
        return employeeCommandService.delete(new DeleteEmployeeCommand(employeeId));
    }

}
