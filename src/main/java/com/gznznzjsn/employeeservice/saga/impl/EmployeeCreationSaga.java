package com.gznznzjsn.employeeservice.saga.impl;

import com.gznznzjsn.employeeservice.commandapi.command.EmployeeDeleteCommand;
import com.gznznzjsn.employeeservice.commandapi.event.EmployeeCreatedEvent;
import com.gznznzjsn.employeeservice.saga.EmployeeCreator;
import com.gznznzjsn.saga.command.EquipmentAssignCommand;
import com.gznznzjsn.saga.event.EquipmentAssignedEvent;
import jakarta.inject.Inject;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

@Saga
public class EmployeeCreationSaga implements EmployeeCreator {

    @Value("${axon.custom.inventory-id}")
    private UUID inventoryId;

    @Inject
    private transient ReactorCommandGateway commandGateway;

    @Override
    public void handle(EmployeeCreatedEvent event) {
        SagaLifecycle.associateWith("inventoryId", inventoryId.toString());
        commandGateway.send(new EquipmentAssignCommand(
                        inventoryId,
                        event.getSpecialization().toString(),
                        event.getEmployeeId()
                ))
                .onErrorResume(o1 -> commandGateway.send(
                        new EmployeeDeleteCommand(
                                event.getGlossaryId(),
                                event.getEmployeeId()
                        )
                ))
                .subscribe();
    }

    @Override
    public void handle(EquipmentAssignedEvent event) {
        SagaLifecycle.end();
    }

}