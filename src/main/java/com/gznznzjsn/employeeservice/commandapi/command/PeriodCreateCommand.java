package com.gznznzjsn.employeeservice.commandapi.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeriodCreateCommand {

    @TargetAggregateIdentifier
    private UUID glossaryId;
    private UUID employeeId;
    private LocalDate date;
    private Integer start;
    private Integer end;

}
