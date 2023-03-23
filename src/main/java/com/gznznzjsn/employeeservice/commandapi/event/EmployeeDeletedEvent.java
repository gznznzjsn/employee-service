package com.gznznzjsn.employeeservice.commandapi.event;

import lombok.Data;

import java.util.UUID;

@Data
public class EmployeeDeletedEvent {

    private final UUID id;

}
