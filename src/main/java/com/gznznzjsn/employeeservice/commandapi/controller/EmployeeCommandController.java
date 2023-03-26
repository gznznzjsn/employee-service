package com.gznznzjsn.employeeservice.commandapi.controller;


import com.gznznzjsn.employeeservice.commandapi.command.EmployeeCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.command.EmployeeDeleteCommand;
import com.gznznzjsn.employeeservice.commandapi.service.EmployeeCommandService;
import com.gznznzjsn.employeeservice.core.web.dto.EmployeeDto;
import com.gznznzjsn.employeeservice.core.web.dto.group.OnCreateEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee-api/v1/glossaries/{glossaryId}/employees")
public class EmployeeCommandController {

    private final EmployeeCommandService employeeCommandService;

    @PostMapping
    public Mono<UUID> create(@Validated(OnCreateEmployee.class) @RequestBody EmployeeDto employeeDto, @PathVariable UUID glossaryId) {
        return Mono.just(employeeDto)
                .map(e -> new EmployeeCreateCommand(glossaryId, e.name(), e.specialization()))
                .flatMap(employeeCommandService::createEmployee);
    }

    @DeleteMapping("/{employeeId}")
    public Mono<UUID> delete(@PathVariable UUID employeeId, @PathVariable UUID glossaryId) {
        return employeeCommandService.delete(new EmployeeDeleteCommand(glossaryId, employeeId));
    }

}
