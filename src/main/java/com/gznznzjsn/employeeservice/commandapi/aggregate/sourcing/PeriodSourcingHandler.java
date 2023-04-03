package com.gznznzjsn.employeeservice.commandapi.aggregate.sourcing;

import com.gznznzjsn.employeeservice.commandapi.event.PeriodDeletedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.PeriodUpdatedEvent;
import org.axonframework.eventsourcing.EventSourcingHandler;

public interface PeriodSourcingHandler {

    @EventSourcingHandler
    void on(PeriodDeletedEvent event);

    @EventSourcingHandler
    void on(PeriodUpdatedEvent event);

}
