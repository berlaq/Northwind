package com.etiya.northwind.Business.Concretes;


import com.etiya.northwind.Business.Responses.Employees.EmployeeListResponse;
import com.etiya.northwind.Entities.Concretes.Employee;
import com.etiya.northwind.DataAccess.Abstracts.EmployeeRepository;
import com.etiya.northwind.Business.Abstracts.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeManager implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeManager(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeListResponse> getAll() {
        List<Employee> employees=this.employeeRepository.findAll();
        List<EmployeeListResponse> employeeDTOs=new ArrayList<>();
        for(Employee employee:employees){
            EmployeeListResponse employeeDTO=new EmployeeListResponse();
            employeeDTO.setEmployeeId(employee.getEmployeeId());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;

    }

}
