package com.etiya.northwind.Business.Abstracts;

import com.etiya.northwind.Business.Responses.Employees.EmployeeListResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeListResponse> getAll();

}
