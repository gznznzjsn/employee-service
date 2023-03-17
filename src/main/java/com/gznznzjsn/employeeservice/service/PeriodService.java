package com.gznznzjsn.employeeservice.service;


import com.gznznzjsn.employeeservice.domain.Period;
import com.gznznzjsn.employeeservice.domain.Specialization;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface PeriodService {


    Mono<Period> eraseAppropriate(LocalDateTime arrivalTime, Specialization specialization, Integer totalDuration);

    Mono<Period> getBy(LocalDateTime arrivalTime, Specialization specialization, Integer totalDuration);

    Mono<Void> delete(Long periodId);

    Mono<Period> update(Period period);

    Mono<Period> get(Long periodId);

}
