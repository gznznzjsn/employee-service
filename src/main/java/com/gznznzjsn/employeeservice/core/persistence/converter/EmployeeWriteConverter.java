package com.gznznzjsn.employeeservice.core.persistence.converter;

import com.gznznzjsn.employeeservice.core.model.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class EmployeeWriteConverter implements Converter<Employee, OutboundRow> {

    @Override
    public OutboundRow convert(Employee employee) {
        OutboundRow row = new OutboundRow();
        if (employee.getId() != null) {
            row.put("employee_id", Parameter.from(employee.getId()));
        }
        if (employee.getGlossary() != null && employee.getGlossary().getId() != null) {
            row.put("glossary_id", Parameter.from(employee.getGlossary().getId()));
        }
        if (employee.getName() != null) {
            row.put("name", Parameter.from(employee.getName()));
        }
        if (employee.getSpecialization() != null) {
            row.put("specialization", Parameter.from(employee.getSpecialization()));
        }
        return row;
    }

}