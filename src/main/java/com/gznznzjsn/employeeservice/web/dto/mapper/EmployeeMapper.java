package com.gznznzjsn.employeeservice.web.dto.mapper;


import com.gznznzjsn.employeeservice.command.model.CreateEmployeeCommand;
import com.gznznzjsn.employeeservice.domain.Employee;
import com.gznznzjsn.employeeservice.web.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEntity(EmployeeDto dto);

    @Mapping(target = "uuid", expression = "java(java.util.UUID.randomUUID().toString())")
    CreateEmployeeCommand toCreateCommand(EmployeeDto dto);

    EmployeeDto toDto(Employee entity);

}
