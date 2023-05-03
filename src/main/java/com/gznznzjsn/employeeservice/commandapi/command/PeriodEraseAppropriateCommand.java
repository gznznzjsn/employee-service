package com.gznznzjsn.employeeservice.commandapi.command;

import com.gznznzjsn.employeeservice.core.model.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeriodEraseAppropriateCommand {

    @TargetAggregateIdentifier
    private UUID glossaryId;
    private LocalDateTime arrivalTime;
    private Specialization specialization;
    private Integer duration;

}
