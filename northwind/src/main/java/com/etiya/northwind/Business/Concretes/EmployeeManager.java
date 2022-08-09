package com.etiya.northwind.Business.Concretes;


import com.etiya.northwind.Business.Responses.Employees.EmployeeListResponse;
import com.etiya.northwind.Entities.Concretes.Employee;
import com.etiya.northwind.DataAccess.Abstracts.EmployeeRepository;
import com.etiya.northwind.Business.Abstracts.EmployeeService;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeManager implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public EmployeeManager(EmployeeRepository employeeRepository, ModelMapperService modelMapperService) {
        this.employeeRepository = employeeRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<EmployeeListResponse> getAll() {
        List<Employee> employees=this.employeeRepository.findAll();
        List<EmployeeListResponse> employeeDTOs = employees.stream()
                .map(employee -> modelMapperService.forResponse().map(employee,EmployeeListResponse.class))
                .collect(Collectors.toList());
        return employeeDTOs;

    }

}
