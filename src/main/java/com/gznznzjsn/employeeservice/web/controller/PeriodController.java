package com.gznznzjsn.employeeservice.web.controller;


import com.gznznzjsn.employeeservice.domain.Specialization;
import com.gznznzjsn.employeeservice.service.PeriodService;
import com.gznznzjsn.employeeservice.web.dto.PeriodDto;
import com.gznznzjsn.employeeservice.web.dto.mapper.PeriodMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee-api/v1/periods")
public class PeriodController {

    private final PeriodMapper periodMapper;
    private final PeriodService periodService;

    @PostMapping
    public Mono<PeriodDto> getAndEraseAppropriatePeriods(@RequestParam LocalDateTime arrivalTime, @RequestParam Specialization specialization, @RequestParam Integer totalDuration) {
        return periodService.eraseAppropriate(arrivalTime, specialization, totalDuration)
                .map(periodMapper::toDto);
    }

}
