package com.gznznzjsn.employeeservice.commandapi.event.handler.impl;

import com.gznznzjsn.employeeservice.commandapi.event.GlossaryCreatedEvent;
import com.gznznzjsn.employeeservice.commandapi.event.handler.GlossaryEventHandler;
import com.gznznzjsn.employeeservice.core.model.Glossary;
import com.gznznzjsn.employeeservice.core.persistence.repository.GlossaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GlossaryEventHandlerImpl implements GlossaryEventHandler {

    private final GlossaryRepository repository;

    @Override
    public void on(GlossaryCreatedEvent event) {
        Mono.just(event)
                .flatMap(e -> repository.save(Glossary.builder()
                        .id(event.getGlossaryId())
                        .isNew(true)
                        .build()))
                .subscribe();
    }

}
