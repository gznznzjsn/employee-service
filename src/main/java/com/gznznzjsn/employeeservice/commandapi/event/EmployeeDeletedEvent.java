package com.gznznzjsn.employeeservice.commandapi.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDeletedEvent {

    private UUID glossaryId;
    private UUID employeeId;

}
