package com.gznznzjsn.employeeservice.web.dto.mapper;


import com.gznznzjsn.employeeservice.commandapi.command.CreateEmployeeCommand;
import com.gznznzjsn.employeeservice.domain.Employee;
import com.gznznzjsn.employeeservice.web.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    CreateEmployeeCommand toCreateCommand(EmployeeDto dto);

    EmployeeDto toDto(Employee entity);

}
