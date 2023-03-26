package com.gznznzjsn.employeeservice.commandapi.handler;

import com.gznznzjsn.employeeservice.commandapi.event.GlossaryCreatedEvent;
import com.gznznzjsn.employeeservice.core.model.Glossary;
import com.gznznzjsn.employeeservice.core.persistence.repository.GlossaryRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GlossaryEventHandler {

    private final GlossaryRepository repository;

    @EventHandler
    public void on(GlossaryCreatedEvent event) {
        Mono.just(event)
                .flatMap(e -> repository.save(Glossary.builder()
                        .id(event.getGlossaryId())
                        .isNew(true)
                        .build()))
                .subscribe();
    }

}
