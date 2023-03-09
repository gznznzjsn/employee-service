package com.gznznzjsn.employeeservice.service;


import com.gznznzjsn.employeeservice.domain.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    Employee create(Employee employee);

    Employee get(Long employeeId);

    void delete(Long employeeId);

}

