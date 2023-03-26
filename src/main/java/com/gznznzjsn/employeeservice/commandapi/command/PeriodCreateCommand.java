package com.gznznzjsn.employeeservice.commandapi.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class PeriodCreateCommand {

    @TargetAggregateIdentifier
    private final UUID glossaryId;
    private final UUID employeeId;
    private final LocalDate date;
    private final Integer start;
    private final Integer end;

}
