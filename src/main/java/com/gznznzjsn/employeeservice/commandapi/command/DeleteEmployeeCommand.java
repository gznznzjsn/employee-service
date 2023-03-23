package com.gznznzjsn.employeeservice.commandapi.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
public class DeleteEmployeeCommand {

    @TargetAggregateIdentifier
    private final UUID id;
    private final Long employeeId;

}
