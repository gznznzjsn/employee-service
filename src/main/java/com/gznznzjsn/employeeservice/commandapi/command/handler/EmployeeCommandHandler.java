package com.gznznzjsn.employeeservice.commandapi.command.handler;

import com.gznznzjsn.employeeservice.commandapi.command.EmployeeCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.command.EmployeeDeleteCommand;
import org.axonframework.commandhandling.CommandHandler;

public interface EmployeeCommandHandler {

    @CommandHandler(routingKey = "glossaryId")
    void handle(EmployeeCreateCommand command);

    @CommandHandler(routingKey = "glossaryId")
    void handle(EmployeeDeleteCommand command);

}
