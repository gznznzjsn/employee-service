package com.gznznzjsn.employeeservice.commandapi.handler;

import com.gznznzjsn.employeeservice.commandapi.event.PeriodCreatedEvent;
import com.gznznzjsn.employeeservice.core.model.Employee;
import com.gznznzjsn.employeeservice.core.model.Period;
import com.gznznzjsn.employeeservice.core.persistence.repository.PeriodRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PeriodEventHandler {

    private final PeriodRepository repository;

    @EventHandler
    public void on(PeriodCreatedEvent event) {
        Mono.just(event)
                .flatMap(e -> repository.save(
                                Period.builder()
                                        .id(event.getPeriodId())
                                        .employee(Employee.builder()
                                                .id(event.getEmployeeId())
                                                .build())
                                        .date(event.getDate())
                                        .start(event.getStart())
                                        .end(event.getEnd())
                                        .isNew(true)
                                        .build()
                        )
                ).subscribe();
    }

    //    private final PeriodRepository periodRepository;
//
//    @Override
//    @Transactional
//    public Mono<Period> eraseAppropriate(LocalDateTime arrivalTime, Specialization specialization, Integer totalDuration) {
//        return getBy(arrivalTime, specialization, totalDuration)
//                .flatMap(period -> {
//                    if (period.getEnd() - period.getStart() == totalDuration) {
//                        return Mono.zip(Mono.just(period), delete(period.getId()));
//                    } else {
//                        Period updatedPeriod = Period.builder()
//                                .id(period.getId())
//                                .start(period.getStart() + totalDuration)
//                                .build();
//                        return Mono.zip(Mono.just(period), update(updatedPeriod));
//                    }
//                })
//                .map(t -> {
//                    Period period = t.getT1();
//                    period.setEnd(period.getStart() + totalDuration);
//                    return period;
//                });
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Mono<Period> getBy(LocalDateTime arrivalTime, Specialization specialization, Integer totalDuration) {
//        return periodRepository.findBy(arrivalTime, specialization, totalDuration)
//                .switchIfEmpty(
//                        Mono.error(
//                                new ResourceNotFoundException("No free time periods for such parameters: arrival time = " + arrivalTime + ", specialization = " + specialization.name() + ", total assignment duration = " + totalDuration)
//                        )
//                );
//    }
//
//    @Override
//    @Transactional
//    public Mono<Void> delete(Long periodId) {
//        return periodRepository.deleteById(periodId);
//    }
//
//    @Override
//    @Transactional
//    public Mono<Period> update(Period period) {
//        return Mono.just(period)
//                .flatMap(p -> get(period.getId()))
//                .map(periodFromRepository -> {
//                    if (period.getEmployee() != null) {
//                        periodFromRepository.setEmployee(period.getEmployee());
//                    }
//                    if (period.getStart() != null) {
//                        periodFromRepository.setStart(period.getStart());
//                    }
//                    if (period.getEnd() != null) {
//                        periodFromRepository.setEnd(period.getEnd());
//                    }
//                    return periodFromRepository;
//                })
//                .flatMap(periodRepository::save);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Mono<Period> get(Long periodId) {
//        return periodRepository.findById(periodId)
//                .switchIfEmpty(
//                        Mono.error(
//                                new ResourceNotFoundException("Periods with id = " + periodId + " not found!")
//                        )
//                );
//    }

}
