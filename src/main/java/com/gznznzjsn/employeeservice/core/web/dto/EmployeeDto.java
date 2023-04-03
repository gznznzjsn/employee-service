package com.gznznzjsn.employeeservice.core.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gznznzjsn.employeeservice.core.model.Specialization;
import com.gznznzjsn.employeeservice.core.web.dto.group.OnCreateEmployee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;


public record EmployeeDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        UUID id,

        @NotBlank(message = "Employee's name can't be blank!", groups = {OnCreateEmployee.class})
        @Length(max = 40, message = "Too long name!", groups = {OnCreateEmployee.class})
        String name,

        @NotNull(message = "Specialization must be set!", groups = {OnCreateEmployee.class})
        Specialization specialization

) {
}
