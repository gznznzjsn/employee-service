package com.gznznzjsn.employeeservice.commandapi.command;

import com.gznznzjsn.employeeservice.core.model.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class EmployeeCreateCommand {

        @TargetAggregateIdentifier
        private UUID glossaryId;
        private String name;
        private Specialization specialization;

}
