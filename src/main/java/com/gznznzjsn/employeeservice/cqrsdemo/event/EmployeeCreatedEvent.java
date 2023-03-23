package com.gznznzjsn.employeeservice.cqrsdemo.event;

import com.gznznzjsn.employeeservice.domain.Specialization;
import lombok.Data;

@Data
public final class EmployeeCreatedEvent {

    private final String uuid;
    private final String name;
    private final Specialization specialization;

}
