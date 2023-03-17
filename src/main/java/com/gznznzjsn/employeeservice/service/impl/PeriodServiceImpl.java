package com.gznznzjsn.employeeservice.service.impl;

import com.gznznzjsn.employeeservice.domain.Period;
import com.gznznzjsn.employeeservice.domain.Specialization;
import com.gznznzjsn.employeeservice.domain.exception.ResourceNotFoundException;
import com.gznznzjsn.employeeservice.persistence.repository.PeriodRepository;
import com.gznznzjsn.employeeservice.service.PeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PeriodServiceImpl implements PeriodService {

    private final PeriodRepository periodRepository;

    @Override
    @Transactional
    public Mono<Period> eraseAppropriate(LocalDateTime arrivalTime, Specialization specialization, Integer totalDuration) {
        return getBy(arrivalTime, specialization, totalDuration)
                .flatMap(period -> {
                    if (period.getEnd() - period.getStart() == totalDuration) {
                        return Mono.zip(Mono.just(period), delete(period.getId()));
                    } else {
                        Period updatedPeriod = Period.builder()
                                .id(period.getId())
                                .start(period.getStart() + totalDuration)
                                .build();
                        return Mono.zip(Mono.just(period), update(updatedPeriod));
                    }
                })
                .map(t -> {
                    Period period = t.getT1();
                    period.setEnd(period.getStart() + totalDuration);
                    return period;
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Period> getBy(LocalDateTime arrivalTime, Specialization specialization, Integer totalDuration) {
        return periodRepository.findBy(arrivalTime, specialization, totalDuration)
                .switchIfEmpty(
                        Mono.error(
                                new ResourceNotFoundException("No free time periods for such parameters: arrival time = " + arrivalTime + ", specialization = " + specialization.name() + ", total assignment duration = " + totalDuration)
                        )
                );
    }

    @Override
    @Transactional
    public Mono<Void> delete(Long periodId) {
        return periodRepository.deleteById(periodId);
    }

    @Override
    @Transactional
    public Mono<Period> update(Period period) {
        return Mono.just(period)
                .flatMap(p -> get(period.getId()))
                .map(periodFromRepository -> {
                    if (period.getEmployee() != null) {
                        periodFromRepository.setEmployee(period.getEmployee());
                    }
                    if (period.getStart() != null) {
                        periodFromRepository.setStart(period.getStart());
                    }
                    if (period.getEnd() != null) {
                        periodFromRepository.setEnd(period.getEnd());
                    }
                    return periodFromRepository;
                })
                .flatMap(periodRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Period> get(Long periodId) {
        return periodRepository.findById(periodId)
                .switchIfEmpty(
                        Mono.error(
                                new ResourceNotFoundException("Periods with id = " + periodId + " not found!")
                        )
                );
    }

}
