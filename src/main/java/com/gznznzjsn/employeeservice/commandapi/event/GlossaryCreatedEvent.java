package com.gznznzjsn.employeeservice.commandapi.event;

import lombok.Data;

import java.util.UUID;

@Data
public final class GlossaryCreatedEvent {

    private final UUID glossaryId;

}
