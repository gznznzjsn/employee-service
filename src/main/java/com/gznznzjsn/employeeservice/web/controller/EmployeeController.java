package com.gznznzjsn.employeeservice.web.controller;


import com.gznznzjsn.employeeservice.service.EmployeeService;
import com.gznznzjsn.employeeservice.web.dto.EmployeeDto;
import com.gznznzjsn.employeeservice.web.dto.group.OnCreateEmployee;
import com.gznznzjsn.employeeservice.web.dto.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee-api/v1/employees")
public class EmployeeController {

    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;

    @GetMapping
    public Flux<EmployeeDto> getAll() {
        return employeeService.getAll().map(employeeMapper::toDto);
    }

    @PostMapping
    public Mono<EmployeeDto> create(@Validated(OnCreateEmployee.class) @RequestBody EmployeeDto employeeDto) {
       return Mono.just(employeeDto)
               .map(employeeMapper::toEntity)
               .flatMap(employeeService::create)
               .map(employeeMapper::toDto);
    }

    @GetMapping("/{employeeId}")
    public Mono<EmployeeDto> get(@PathVariable Long employeeId) {
        return employeeService
                .get(employeeId)
                .map(employeeMapper::toDto);
    }

    @DeleteMapping("/{employeeId}")
    public Mono<Void> delete(@PathVariable Long employeeId) {
        return employeeService.delete(employeeId);
    }

}
