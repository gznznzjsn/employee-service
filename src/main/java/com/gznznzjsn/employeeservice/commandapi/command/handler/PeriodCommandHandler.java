package com.gznznzjsn.employeeservice.commandapi.command.handler;

import com.gznznzjsn.employeeservice.commandapi.command.PeriodEraseAppropriateCommand;
import org.axonframework.commandhandling.CommandHandler;

public interface PeriodCommandHandler {

    @CommandHandler(routingKey = "glossaryId")
    void handle(PeriodEraseAppropriateCommand command);

}
