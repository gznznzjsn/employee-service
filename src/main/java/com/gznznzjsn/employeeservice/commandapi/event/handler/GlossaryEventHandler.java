package com.gznznzjsn.employeeservice.commandapi.event.handler;

import com.gznznzjsn.employeeservice.commandapi.event.GlossaryCreatedEvent;
import org.axonframework.eventhandling.EventHandler;

public interface GlossaryEventHandler {

    @EventHandler
    void on(GlossaryCreatedEvent event);

}
