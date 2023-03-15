package com.gznznzjsn.employeeservice.web.dto;

import java.time.LocalDate;

public record PeriodDto(

        Long id,
        EmployeeDto employee,
        LocalDate date,
        Integer start,
        Integer end

) {
}
