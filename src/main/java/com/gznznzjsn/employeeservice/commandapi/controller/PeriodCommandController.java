package com.gznznzjsn.employeeservice.commandapi.controller;


import com.gznznzjsn.employeeservice.commandapi.command.PeriodCreateCommand;
import com.gznznzjsn.employeeservice.commandapi.service.PeriodCommandService;
import com.gznznzjsn.employeeservice.core.web.dto.PeriodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee-api/v1/glossaries/{glossaryId}/employees/{employeeId}/periods")
public class PeriodCommandController {

    private final PeriodCommandService periodCommandService;

//    @PostMapping
//    public Mono<PeriodDto> erase(@RequestParam UUID periodId) {
//        return periodCommandService.erase(new ErasePeriodCommand(periodId))
//                .map(periodMapper::toDto);
//    }

    @PostMapping
    public Mono<UUID> create(@RequestBody PeriodDto periodDto, @PathVariable UUID glossaryId, @PathVariable UUID employeeId) {
        return Mono.just(periodDto)
                .map(p -> new PeriodCreateCommand(glossaryId, employeeId, p.date(), p.start(), p.end()))
                .flatMap(periodCommandService::create);
    }

//    @GetMapping
//    public Mono<PeriodDto> getAppropriate(@RequestParam LocalDateTime arrivalTime, @RequestParam Specialization specialization, @RequestParam Integer totalDuration) {
//        return periodCommandService.getAppropriate(new ErasePeriodCommand(arrivalTime, specialization, totalDuration))
//                .map(periodMapper::toDto);
//    }

}
