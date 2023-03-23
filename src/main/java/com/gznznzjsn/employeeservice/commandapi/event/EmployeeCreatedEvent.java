package com.gznznzjsn.employeeservice.commandapi.event;

import com.gznznzjsn.employeeservice.domain.Specialization;
import lombok.Data;

import java.util.UUID;

@Data
public final class EmployeeCreatedEvent {

    private final UUID id;
    private final String name;
    private final Specialization specialization;

}
