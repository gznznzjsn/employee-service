package com.gznznzjsn.employeeservice.core.web.dto.mapper;


import com.gznznzjsn.employeeservice.core.model.Period;
import com.gznznzjsn.employeeservice.core.web.dto.PeriodDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface PeriodMapper {

    PeriodDto toDto(Period entity);

}
