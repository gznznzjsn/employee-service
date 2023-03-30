package com.gznznzjsn.employeeservice.commandapi.event.handler;

import com.gznznzjsn.employeeservice.commandapi.event.EmployeeCreatedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.EmployeeDeletedEvent;
import org.axonframework.eventhandling.EventHandler;

public interface EmployeeEventHandler {
    @EventHandler
    void on(EmployeeCreatedEvent event);

    @EventHandler
    void on(EmployeeDeletedEvent event);
}
