package com.gznznzjsn.employeeservice.commandapi.command;

import com.gznznzjsn.employeeservice.core.model.Specialization;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PeriodEraseAppropriateCommand {

    @TargetAggregateIdentifier
    private final UUID glossaryId;
    private final LocalDateTime arrivalTime;
    private final Specialization specialization;
    private final Integer totalDuration;

}
