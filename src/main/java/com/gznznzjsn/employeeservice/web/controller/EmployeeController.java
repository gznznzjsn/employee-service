package com.gznznzjsn.employeeservice.web.controller;


import com.gznznzjsn.employeeservice.service.EmployeeCommandService;
import com.gznznzjsn.employeeservice.web.dto.EmployeeDto;
import com.gznznzjsn.employeeservice.web.dto.group.OnCreateEmployee;
import com.gznznzjsn.employeeservice.web.dto.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee-api/v1/employees")
public class EmployeeController {

    private final EmployeeMapper employeeMapper;
    private final EmployeeCommandService employeeCommandService;

//    @GetMapping
//    public Flux<EmployeeDto> getAll() {
//        return employeeService.getAll().map(employeeMapper::toDto);
//    }

    @PostMapping
    public Mono<String> create(@Validated(OnCreateEmployee.class) @RequestBody EmployeeDto employeeDto) {
        return Mono.just(employeeDto)
                .map(employeeMapper::toCreateCommand)
                .flatMap(employeeCommandService::createEmployee);
    }

//    @GetMapping("/{employeeId}")
//    public Mono<EmployeeDto> get(@PathVariable Long employeeId) {
//        return employeeService
//                .get(employeeId)
//                .map(employeeMapper::toDto);
//    }
//
//    @DeleteMapping("/{employeeId}")
//    public Mono<Void> delete(@PathVariable Long employeeId) {
//        return employeeService.delete(employeeId);
//    }

}
