package com.gznznzjsn.employeeservice.persistence.converter;

import com.gznznzjsn.employeeservice.domain.Period;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class PeriodWriteConverter implements Converter<Period, OutboundRow> {

    @Override
    public OutboundRow convert(Period Period) {
        OutboundRow row = new OutboundRow();
        if (Period.getId() != null) {
            row.put("period_id", Parameter.from(Period.getId()));
        }
        if (Period.getEmployee() != null && Period.getEmployee().getId() != null) {
            row.put("employee_id", Parameter.from(Period.getEmployee().getId()));
        }
        if (Period.getDate() != null) {
            row.put("period_date", Parameter.from(Period.getDate()));
        }
        if (Period.getStart() != null) {
            row.put("period_start", Parameter.from(Period.getStart()));
        }
        if (Period.getEnd() != null) {
            row.put("period_end", Parameter.from(Period.getEnd()));
        }
        return row;
    }

}