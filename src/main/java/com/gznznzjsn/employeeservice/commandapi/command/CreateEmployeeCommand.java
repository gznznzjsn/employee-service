package com.gznznzjsn.employeeservice.commandapi.command;

import com.gznznzjsn.employeeservice.domain.Specialization;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
public final class CreateEmployeeCommand {

        @TargetAggregateIdentifier
        private final UUID id;
        private final String name;
        private final Specialization specialization;

}
