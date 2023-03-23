package com.gznznzjsn.employeeservice.persistence.converter;


import com.gznznzjsn.employeeservice.domain.Employee;
import com.gznznzjsn.employeeservice.domain.Period;
import com.gznznzjsn.employeeservice.domain.Specialization;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalDate;
import java.util.UUID;

@ReadingConverter
public class PeriodReadConverter implements Converter<Row, Period> {

    @Override
    public Period convert(Row source) {
        return Period.builder()
                .id(source.get("period_id", Long.class))
                .employee(Employee.builder()
                        .id(source.get("employee_id", UUID.class))
                        .name(source.get("employee_name", String.class))
                        .specialization(Specialization.valueOf(source.get("employee_specialization", String.class)))
                        .build()
                )
                .date(source.get("period_date", LocalDate.class))
                .start(source.get("period_start", Integer.class))
                .end(source.get("period_end", Integer.class))
                .build();
    }

}