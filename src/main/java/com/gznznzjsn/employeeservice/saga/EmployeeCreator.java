package com.gznznzjsn.employeeservice.saga;

import com.gznznzjsn.employeeservice.commandapi.event.EmployeeCreatedEvent;
import com.gznznzjsn.common.event.EquipmentAssignedEvent;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;

public interface EmployeeCreator {

    @StartSaga
    @SagaEventHandler(associationProperty = "employeeId")
    void handle(EmployeeCreatedEvent event);

    @SagaEventHandler(associationProperty = "employeeId")
    void handle(EquipmentAssignedEvent event);

}
