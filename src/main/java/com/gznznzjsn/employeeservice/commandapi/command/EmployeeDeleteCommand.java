package com.gznznzjsn.employeeservice.commandapi.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
public class EmployeeDeleteCommand {

    @TargetAggregateIdentifier
    private final UUID glossaryId;
    private final UUID employeeId;

}
