package com.gznznzjsn.employeeservice.commandapi.command;

import com.gznznzjsn.employeeservice.core.model.Specialization;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
public final class EmployeeCreateCommand {

        @TargetAggregateIdentifier
        private final UUID glossaryId;
        private final String name;
        private final Specialization specialization;

}
