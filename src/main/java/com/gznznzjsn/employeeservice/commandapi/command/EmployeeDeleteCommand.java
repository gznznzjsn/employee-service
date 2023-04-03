package com.gznznzjsn.employeeservice.commandapi.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDeleteCommand {

    @TargetAggregateIdentifier
    private UUID glossaryId;
    private UUID employeeId;

}
