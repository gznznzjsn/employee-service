package com.gznznzjsn.employeeservice.core.persistence.converter;


import com.gznznzjsn.employeeservice.core.model.Employee;
import com.gznznzjsn.employeeservice.core.model.Glossary;
import com.gznznzjsn.employeeservice.core.model.Specialization;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.util.UUID;

@ReadingConverter
public class EmployeeReadConverter implements Converter<Row, Employee> {

    @Override
    public Employee convert(Row source) {
        return Employee.builder()
                .id(source.get("employee_id", UUID.class))
                .glossary(Glossary.builder()
                        .id(source.get("glossary_id", UUID.class))
                        .build()
                )
                .name(source.get("name", String.class))
                .specialization(Specialization.valueOf(source.get("specialization", String.class)))
                .build();
    }

}