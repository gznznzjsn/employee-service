package com.gznznzjsn.employeeservice.commandapi.controller;


import com.gznznzjsn.employeeservice.commandapi.command.GlossaryCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.service.GlossaryCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee-api/v1/glossaries")
public class GlossaryCommandController {

    private final GlossaryCommandService glossaryService;

    @PostMapping
    public Mono<UUID> create() {
        return glossaryService.create(new GlossaryCreateCommand());
    }

}
