package com.etiya.northwind.Business.Abstracts;

import com.etiya.northwind.Business.Responses.Employees.EmployeeListResponse;
import com.etiya.northwind.Business.requests.Employee.CreateEmployeeRequest;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    DataResult<List<EmployeeListResponse>>  getAll();
    Result updateEmployee(EmployeeListResponse employeeListResponse);
    Result deleteEmployee(int employeeId);
    DataResult<EmployeeListResponse>  getEmployeeById(int employeeId);
    Result addEmployee(CreateEmployeeRequest createEmployeeRequest);

    DataResult<Page<EmployeeListResponse>>  getAllByPage(int page, int size);
    DataResult<Page<EmployeeListResponse>>  getAllByPageWithField(int page,int size,String field);
    DataResult<Page<EmployeeListResponse>>  getAllByPageWithOrder(int page,int size,String field,String order);
}
