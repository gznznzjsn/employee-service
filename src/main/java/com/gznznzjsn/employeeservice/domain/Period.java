package com.gznznzjsn.employeeservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("periods")
public class Period {

    @Id
    @Column("period_id")
    private Long id;
    private Employee employee;
    private LocalDate date;
    private Integer start;
    private Integer end;

}
