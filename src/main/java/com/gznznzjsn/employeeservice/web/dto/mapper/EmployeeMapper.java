package com.gznznzjsn.employeeservice.web.dto.mapper;


import com.gznznzjsn.employeeservice.domain.Employee;
import com.gznznzjsn.employeeservice.web.dto.EmployeeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEntity(EmployeeDto dto);

    EmployeeDto toDto(Employee entity);

}
