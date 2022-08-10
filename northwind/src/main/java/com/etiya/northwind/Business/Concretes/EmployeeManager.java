package com.etiya.northwind.Business.Concretes;


import com.etiya.northwind.Business.Responses.Customers.CustomerListResponse;
import com.etiya.northwind.Business.Responses.Employees.EmployeeListResponse;
import com.etiya.northwind.Business.requests.Employee.CreateEmployeeRequest;
import com.etiya.northwind.Entities.Concretes.Customer;
import com.etiya.northwind.Entities.Concretes.Employee;
import com.etiya.northwind.DataAccess.Abstracts.EmployeeRepository;
import com.etiya.northwind.Business.Abstracts.EmployeeService;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
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
        List<EmployeeListResponse> employeeListResponses = employees.stream()
                .map(employee -> modelMapperService.forResponse().map(employee,EmployeeListResponse.class))
                .collect(Collectors.toList());
        return employeeListResponses;
    }

    @Override
    public void updateEmployee(EmployeeListResponse employeeListResponse) {
        employeeRepository.save(modelMapperService.forRequest().map(employeeListResponse, Employee.class));
    }

    @Override
    public void deleteEmployee(int employeeId) {
        this.employeeRepository.deleteById(employeeId);
    }

    @Override
    public EmployeeListResponse getEmployeeById(int employeeId) {
        EmployeeListResponse employeeListResponse = modelMapperService.forResponse()
                .map(this.employeeRepository.getReferenceById(employeeId), EmployeeListResponse.class);
        return employeeListResponse;
    }

    @Override
    public void addEmployee(CreateEmployeeRequest createEmployeeRequest) {
        this.employeeRepository.save(modelMapperService.forRequest().map(createEmployeeRequest, Employee.class));
    }

    @Override
    public Page<EmployeeListResponse> getAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        var tempEmployee = employeeRepository.findAll(pageable);
        Page<EmployeeListResponse> employeeListResponses = tempEmployee.map(new Function<Employee, EmployeeListResponse>() {
            @Override
            public EmployeeListResponse apply(Employee employee) {
                EmployeeListResponse employeeListResponse = modelMapperService.forResponse().map(employee,EmployeeListResponse.class);
                return employeeListResponse;
            }
        });
        return employeeListResponses;
    }

    @Override
    public Page<EmployeeListResponse> getAllByPageWithField(int page, int size, String field) {
        Pageable pageable = PageRequest.of(page,size,Sort.by(field));
        var tempEmployee = employeeRepository.findAll(pageable);
        Page<EmployeeListResponse> employeeListResponses = tempEmployee.map(new Function<Employee, EmployeeListResponse>() {
            @Override
            public EmployeeListResponse apply(Employee employee) {
                EmployeeListResponse employeeListResponse = modelMapperService.forResponse().map(employee,EmployeeListResponse.class);
                return employeeListResponse;
            }
        });
        return employeeListResponses;
    }

    @Override
    public Page<EmployeeListResponse> getAllByPageWithOrder(int page, int size, String field, String order) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(order),field));
        var tempEmployee = employeeRepository.findAll(pageable);
        Page<EmployeeListResponse> employeeListResponses = tempEmployee.map(new Function<Employee, EmployeeListResponse>() {
            @Override
            public EmployeeListResponse apply(Employee employee) {
                EmployeeListResponse employeeListResponse = modelMapperService.forResponse().map(employee,EmployeeListResponse.class);
                return employeeListResponse;
            }
        });
        return employeeListResponses;
    }


}
