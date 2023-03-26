package com.gznznzjsn.employeeservice.core.web.dto.mapper;


import com.gznznzjsn.employeeservice.commandapi.command.PeriodCreateCommand;
import com.gznznzjsn.employeeservice.core.model.Period;
import com.gznznzjsn.employeeservice.core.web.dto.PeriodDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface PeriodMapper {

    PeriodDto toDto(Period entity);

}
