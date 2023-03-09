package com.gznznzjsn.employeeservice.web.controller;


import com.gznznzjsn.employeeservice.domain.Employee;
import com.gznznzjsn.employeeservice.service.EmployeeService;
import com.gznznzjsn.employeeservice.web.dto.EmployeeDto;
import com.gznznzjsn.employeeservice.web.dto.group.OnCreateEmployee;
import com.gznznzjsn.employeeservice.web.dto.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee-api/v1/employees")
public class EmployeeController {

    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;

//    @PreAuthorize("hasAuthority('EMPLOYEE_MANAGER')")
    @GetMapping
    public List<EmployeeDto> getAll() {
        return employeeService.getAll().stream()
                .map(employeeMapper::toDto)
                .toList();
    }

//    @PreAuthorize("hasAuthority('EMPLOYEE_MANAGER')")
    @PostMapping
    public EmployeeDto create(@Validated(OnCreateEmployee.class) @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee returnedEmployee = employeeService.create(employee);
        return employeeMapper.toDto(returnedEmployee);
    }

//    @PreAuthorize("hasAuthority('EMPLOYEE_MANAGER')")
    @GetMapping("/{employeeId}")
    public EmployeeDto get(@PathVariable Long employeeId) {
        Employee returnedEmployee = employeeService.get(employeeId);
        return employeeMapper.toDto(returnedEmployee);
    }

//    @PreAuthorize("hasAuthority('EMPLOYEE_MANAGER')")
    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable Long employeeId) {
        employeeService.delete(employeeId);
    }

}
