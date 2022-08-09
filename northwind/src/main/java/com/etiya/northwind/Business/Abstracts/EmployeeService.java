package com.etiya.northwind.Business.Abstracts;

import com.etiya.northwind.Business.Responses.Employees.EmployeeListResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<EmployeeListResponse> getAll();
    void updateEmployee(EmployeeListResponse employeeListResponse);
    void deleteEmployee(int employeeId);
    EmployeeListResponse getEmployeeById(int employeeId);
    void addEmployee(EmployeeListResponse employeeListResponse);

    Page<EmployeeListResponse> getAllByPage(int page, int size);
    Page<EmployeeListResponse>  getAllByPageWithField(int page,int size,String field);
    Page<EmployeeListResponse>  getAllByPageWithOrder(int page,int size,String field,String order);
}
