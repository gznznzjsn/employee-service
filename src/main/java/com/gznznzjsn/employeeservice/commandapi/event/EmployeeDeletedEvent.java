package com.gznznzjsn.employeeservice.commandapi.event;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
public class EmployeeDeletedEvent {

    @TargetAggregateIdentifier
    private final UUID id;
    private final Long employeeId;

}
