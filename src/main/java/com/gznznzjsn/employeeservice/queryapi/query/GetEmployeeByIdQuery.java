package com.gznznzjsn.employeeservice.queryapi.query;

import lombok.Data;

import java.util.UUID;

@Data
public class GetEmployeeByIdQuery {

    private final UUID id;

}
