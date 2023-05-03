package com.gznznzjsn.employeeservice.core.persistence.repository;

import com.gznznzjsn.employeeservice.core.model.Period;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PeriodRepository extends R2dbcRepository<Period, UUID> {

    @Query("""
            SELECT
                    period_id as "period_id",
                    period_date as "period_date",
                    period_start as "period_start",
                    period_end as "period_end",
                    employee_id as "employee_id",
                    glossary_id as "employee_glossary_id",
                    "name"        as "employee_name",
                    specialization       as "employee_specialization"
                    FROM periods
                    JOIN employees USING (employee_id)
                    WHERE period_id=$1;
            """)
    Mono<Period> findById(UUID periodId);

}
