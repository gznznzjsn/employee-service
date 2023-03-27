package com.gznznzjsn.employeeservice.saga;

import com.gznznzjsn.employeeservice.commandapi.event.EmployeeCreatedEvent;
import com.gznznzjsn.saga.command.EquipmentAssignCommand;
import com.gznznzjsn.saga.event.EquipmentAssignedEvent;
import jakarta.inject.Inject;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.util.UUID;

@Saga
public class EmployeeCreationSaga {

    @Inject
    private transient ReactorCommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "employeeId")
    public void handle(EmployeeCreatedEvent event) {
        System.out.println("Saga invoked");

        UUID equipmentId = UUID.fromString("4de57dc9-08be-4573-8da9-858bc52faa4f");//todo hardcoded
        SagaLifecycle.associateWith("inventoryId", equipmentId.toString());

        System.out.println("employee id" + event.getEmployeeId());
        commandGateway.send(new EquipmentAssignCommand(equipmentId,
                event.getSpecialization().toString(),
                event.getEmployeeId()
        )).subscribe();
        System.out.println("SENT!!!");
    }

    @SagaEventHandler(associationProperty = "employeeId")
    public void handle(EquipmentAssignedEvent event) {
        System.out.println("Saga finished");
        SagaLifecycle.end();
        System.out.println("Completely");
    }

}