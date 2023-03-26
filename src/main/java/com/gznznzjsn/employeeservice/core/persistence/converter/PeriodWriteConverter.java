package com.gznznzjsn.employeeservice.core.persistence.converter;

import com.gznznzjsn.employeeservice.core.model.Period;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class PeriodWriteConverter implements Converter<Period, OutboundRow> {

    @Override
    public OutboundRow convert(Period period) {
        OutboundRow row = new OutboundRow();
        if (period.getId() != null) {
            row.put("period_id", Parameter.from(period.getId()));
        }
        if (period.getEmployee() != null && period.getEmployee().getId() != null) {
            row.put("employee_id", Parameter.from(period.getEmployee().getId()));
        }
        if (period.getDate() != null) {
            row.put("period_date", Parameter.from(period.getDate()));
        }
        if (period.getStart() != null) {
            row.put("period_start", Parameter.from(period.getStart()));
        }
        if (period.getEnd() != null) {
            row.put("period_end", Parameter.from(period.getEnd()));
        }
        return row;
    }

}