package com.gznznzjsn.employeeservice.web.dto.mapper;


import com.gznznzjsn.employeeservice.domain.Period;
import com.gznznzjsn.employeeservice.web.dto.PeriodDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface PeriodMapper {

    PeriodDto toDto(Period entity);

}
