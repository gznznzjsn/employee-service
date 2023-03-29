package com.gznznzjsn.employeeservice.commandapi.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeriodDeletedEvent {

    private UUID periodId;

}
