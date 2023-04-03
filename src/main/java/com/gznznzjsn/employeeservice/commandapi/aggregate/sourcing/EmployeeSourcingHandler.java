package com.gznznzjsn.employeeservice.commandapi.aggregate.sourcing;

import com.gznznzjsn.employeeservice.commandapi.event.EmployeeCreatedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.EmployeeDeletedEvent;
import org.axonframework.eventsourcing.EventSourcingHandler;

public interface EmployeeSourcingHandler {

    @EventSourcingHandler
    void on(EmployeeCreatedEvent event);

    @EventSourcingHandler
    void on(EmployeeDeletedEvent event);

}
