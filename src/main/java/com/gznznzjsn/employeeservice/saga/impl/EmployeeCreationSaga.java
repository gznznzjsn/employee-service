package com.gznznzjsn.employeeservice.saga.impl;

import com.gznznzjsn.employeeservice.commandapi.command.EmployeeDeleteCommand;
import com.gznznzjsn.employeeservice.commandapi.event.EmployeeCreatedEvent;
import com.gznznzjsn.employeeservice.saga.EmployeeCreator;
import com.gznznzjsn.common.command.EquipmentAssignCommand;
import com.gznznzjsn.common.event.EquipmentAssignedEvent;
import jakarta.inject.Inject;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.spring.stereotype.Saga;

@Saga
public class EmployeeCreationSaga implements EmployeeCreator {

    @Inject
    private transient CommandGateway commandGateway;

    @Override
    public void handle(final EmployeeCreatedEvent event) {
        SagaLifecycle.associateWith(
                "inventoryId",
                event.getInventoryId().toString()
        );
        try {
            commandGateway.sendAndWait(new EquipmentAssignCommand(
                    event.getInventoryId(),
                    event.getSpecialization().toString(),
                    event.getEmployeeId()
            ));
        } catch (Exception e) {
            commandGateway.sendAndWait(new EmployeeDeleteCommand(
                    event.getGlossaryId(),
                    event.getEmployeeId()
            ));
        }
    }

    @Override
    public void handle(final EquipmentAssignedEvent event) {
        SagaLifecycle.end();
    }

}
