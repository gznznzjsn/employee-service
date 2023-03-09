package com.gznznzjsn.employeeservice.service.impl;

import com.gznznzjsn.employeeservice.domain.Employee;
import com.gznznzjsn.employeeservice.exception.ResourceNotFoundException;
import com.gznznzjsn.employeeservice.repository.EmployeeDao;
import com.gznznzjsn.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAll() {
        return employeeDao.findAll();
    }

    @Override
    @Transactional
    public Employee create(Employee employee) {
        Employee employeeToCreate = Employee.builder()
                .name(employee.getName())
                .specialization(employee.getSpecialization())
                .build();
        employeeDao.create(employeeToCreate);
        return employeeToCreate;
    }

    @Override
    @Transactional(readOnly = true)
    public Employee get(Long employeeId) {
        return employeeDao.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id=" + employeeId + " not found!"));

    }

    @Override
    @Transactional
    public void delete(Long employeeId) {
        employeeDao.delete(employeeId);
    }

}
