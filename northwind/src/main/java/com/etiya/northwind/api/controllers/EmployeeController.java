package com.etiya.northwind.api.controllers;


import com.etiya.northwind.Business.Responses.Employees.EmployeeListResponse;
import com.etiya.northwind.Business.Abstracts.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAll")
    public List<EmployeeListResponse> getAll(){
        return this.employeeService.getAll();
    }
}
