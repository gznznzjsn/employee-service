package com.gznznzjsn.employeeservice.command.model;

import com.gznznzjsn.employeeservice.domain.Specialization;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public final class CreateEmployeeCommand {

        @TargetAggregateIdentifier
        private final String employeeId;
        private final String name;
        private final Specialization specialization;

}
