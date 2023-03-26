package com.gznznzjsn.employeeservice.commandapi.event;

import com.gznznzjsn.employeeservice.core.model.Specialization;
import lombok.Data;

import java.util.UUID;

@Data
public final class EmployeeCreatedEvent {

    private final UUID glossaryId;
    private final UUID employeeId;
    private final String name;
    private final Specialization specialization;

}
