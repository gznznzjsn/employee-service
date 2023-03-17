package com.gznznzjsn.employeeservice.service.impl;

import com.gznznzjsn.employeeservice.domain.Employee;
import com.gznznzjsn.employeeservice.domain.exception.ResourceNotFoundException;
import com.gznznzjsn.employeeservice.persistence.repository.EmployeeRepository;
import com.gznznzjsn.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public Flux<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Mono<Employee> create(Employee employee) {
        return employeeRepository.save(
                        Employee.builder()
                                .name(employee.getName())
                                .specialization(employee.getSpecialization())
                                .build()
                );
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Employee> get(Long employeeId) {
        return employeeRepository
                .findById(employeeId)
                .switchIfEmpty(
                        Mono.error(
                                new ResourceNotFoundException("Employee with id=" + employeeId + " not found!")
                        )
                );

    }

    @Override
    @Transactional
    public Mono<Void> delete(Long employeeId) {
        return employeeRepository.deleteById(employeeId);
    }

}
