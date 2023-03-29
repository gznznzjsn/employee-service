package com.gznznzjsn.employeeservice.commandapi.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeriodCreatedEvent {

    private UUID employeeId;
    private UUID periodId;
    private LocalDate date;
    private Integer start;
    private Integer end;

}
