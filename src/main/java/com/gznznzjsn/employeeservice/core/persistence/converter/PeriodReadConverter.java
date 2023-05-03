package com.gznznzjsn.employeeservice.core.persistence.converter;


import com.gznznzjsn.employeeservice.core.model.Employee;
import com.gznznzjsn.employeeservice.core.model.Glossary;
import com.gznznzjsn.employeeservice.core.model.Period;
import com.gznznzjsn.employeeservice.core.model.Specialization;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalDate;
import java.util.UUID;

@ReadingConverter
public class PeriodReadConverter implements Converter<Row, Period> {

    @Override
    public Period convert(final Row source) {
        return Period.builder()
                .id(source.get("period_id", UUID.class))
                .employee(Employee.builder()
                        .id(source.get("employee_id", UUID.class))
                        .glossary(
                                Glossary.builder()
                                        .id(source.get(
                                                "employee_glossary_id",
                                                UUID.class)
                                        )
                                        .build()
                        )
                        .name(source.get("employee_name", String.class))
                        .specialization(Specialization.valueOf(
                                source.get(
                                        "employee_specialization",
                                        String.class
                                )
                        ))
                        .build()
                )
                .date(source.get("period_date", LocalDate.class))
                .start(source.get("period_start", Integer.class))
                .end(source.get("period_end", Integer.class))
                .build();
    }

}
