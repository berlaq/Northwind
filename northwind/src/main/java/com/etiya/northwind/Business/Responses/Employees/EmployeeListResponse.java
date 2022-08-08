package com.etiya.northwind.Business.Responses.Employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeListResponse {

    private int employeeId;
    private String lastName;
    private String firstName;
}
