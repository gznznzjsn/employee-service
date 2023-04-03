package com.gznznzjsn.employeeservice.core.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record PeriodDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        UUID id,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        EmployeeDto employee,
        @NotNull
        LocalDate date,
        @NotNull
        Integer start,
        @NotNull
        Integer end

) {
}
