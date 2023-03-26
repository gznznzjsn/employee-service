package com.gznznzjsn.employeeservice.commandapi.event;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class PeriodCreatedEvent {

    private final UUID employeeId;
    private final UUID periodId;
    private final LocalDate date;
    private final Integer start;
    private final Integer end;

}
