package com.gznznzjsn.employeeservice.commandapi.event.handler;

import com.gznznzjsn.employeeservice.commandapi.event.PeriodCreatedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.PeriodDeletedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.PeriodUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;

public interface PeriodEventHandler {

    @EventHandler
    void on(PeriodCreatedEvent event);

    @EventHandler
    void on(PeriodDeletedEvent event);

    @EventHandler
    void on(PeriodUpdatedEvent event);

}
