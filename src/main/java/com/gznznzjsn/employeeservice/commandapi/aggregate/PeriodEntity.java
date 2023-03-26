package com.gznznzjsn.employeeservice.commandapi.aggregate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.EntityId;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class PeriodEntity {

    @EntityId
    private UUID periodId;
    private LocalDate date;
    private Integer start;
    private Integer end;

}
