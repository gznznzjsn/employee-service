package com.gznznzjsn.employeeservice.commandapi.controller;


import com.gznznzjsn.employeeservice.commandapi.command.PeriodCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.service.PeriodCommandService;
import com.gznznzjsn.employeeservice.core.web.dto.PeriodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(
        "/employee-api/v1/glossaries/{glossaryId}"
        + "/employees/{employeeId}/periods"
)
public class PeriodCommandController {

    private final PeriodCommandService periodCommandService;

    @PostMapping
    public Mono<UUID> create(
            final @RequestBody PeriodDto periodDto,
            final @PathVariable UUID glossaryId,
            final @PathVariable UUID employeeId) {
        return Mono.just(periodDto)
                .map(p -> new PeriodCreateCommand(
                        glossaryId, employeeId, p.date(), p.start(), p.end()
                ))
                .flatMap(periodCommandService::create);
    }

}
