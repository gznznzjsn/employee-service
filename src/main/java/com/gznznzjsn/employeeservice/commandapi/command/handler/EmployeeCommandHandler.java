package com.gznznzjsn.employeeservice.commandapi.command.handler;

import com.gznznzjsn.employeeservice.commandapi.command.EmployeeCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.command.EmployeeDeleteCommand;
import org.axonframework.commandhandling.CommandHandler;

public interface EmployeeCommandHandler {

    @CommandHandler
    void handle(EmployeeCreateCommand command);

    @CommandHandler
    void handle(EmployeeDeleteCommand command);

}
