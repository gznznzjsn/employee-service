package com.gznznzjsn.employeeservice.commandapi.event;

import lombok.Data;

import java.util.UUID;

@Data
public class PeriodDeletedEvent {

    private final UUID periodId;

}
