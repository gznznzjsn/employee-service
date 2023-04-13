package com.gznznzjsn.employeeservice.commandapi.event;

import com.gznznzjsn.employeeservice.core.model.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class EmployeeCreatedEvent {

    private UUID glossaryId;
    private UUID employeeId;
    private String name;
    private Specialization specialization;
    private UUID inventoryId;

}
