package com.etiya.northwind.Business.Concretes;


import com.etiya.northwind.Business.Responses.Customers.CustomerListResponse;
import com.etiya.northwind.Business.Responses.Employees.EmployeeListResponse;
import com.etiya.northwind.Business.requests.Employee.CreateEmployeeRequest;
import com.etiya.northwind.Entities.Concretes.Customer;
import com.etiya.northwind.Entities.Concretes.Employee;
import com.etiya.northwind.DataAccess.Abstracts.EmployeeRepository;
import com.etiya.northwind.Business.Abstracts.EmployeeService;
import com.etiya.northwind.core.Exceptions.BusinessException;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.*;
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
    public DataResult<List<EmployeeListResponse>> getAll() {
        List<Employee> employees=this.employeeRepository.findAll();
        List<EmployeeListResponse> employeeListResponses = employees.stream()
                .map(employee -> modelMapperService.forResponse().map(employee,EmployeeListResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(employeeListResponses,"getirildi") ;
    }

    @Override
    public Result updateEmployee(EmployeeListResponse employeeListResponse) {
        reportsToCheck(employeeListResponse.getReportsTo());
        employeeRepository.save(modelMapperService.forRequest().map(employeeListResponse, Employee.class));
       return new SuccessResult() ;
    }

    @Override
    public Result deleteEmployee(int employeeId) {
        this.employeeRepository.deleteById(employeeId);
        return new SuccessResult("silindi");
    }

    @Override
    public DataResult<EmployeeListResponse>  getEmployeeById(int employeeId) {
        EmployeeListResponse employeeListResponse = modelMapperService.forResponse()
                .map(this.employeeRepository.getReferenceById(employeeId), EmployeeListResponse.class);
        return new SuccessDataResult<>(employeeListResponse,"getirildi");
    }

    @Override
    public Result addEmployee(CreateEmployeeRequest createEmployeeRequest) {
        reportsToCheck(createEmployeeRequest.getReportsTo());
        this.employeeRepository.save(modelMapperService.forRequest().map(createEmployeeRequest, Employee.class));
        return new SuccessResult("Eklendi");
    }

    @Override
    public DataResult<Page<EmployeeListResponse>> getAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        var tempEmployee = employeeRepository.findAll(pageable);
        Page<EmployeeListResponse> employeeListResponses = tempEmployee.map(new Function<Employee, EmployeeListResponse>() {
            @Override
            public EmployeeListResponse apply(Employee employee) {
                EmployeeListResponse employeeListResponse = modelMapperService.forResponse().map(employee,EmployeeListResponse.class);
                return employeeListResponse;
            }
        });
        return new SuccessDataResult<>(employeeListResponses);
    }

    @Override
    public DataResult<Page<EmployeeListResponse>> getAllByPageWithField(int page, int size, String field) {
        Pageable pageable = PageRequest.of(page,size,Sort.by(field));
        var tempEmployee = employeeRepository.findAll(pageable);
        Page<EmployeeListResponse> employeeListResponses = tempEmployee.map(new Function<Employee, EmployeeListResponse>() {
            @Override
            public EmployeeListResponse apply(Employee employee) {
                EmployeeListResponse employeeListResponse = modelMapperService.forResponse().map(employee,EmployeeListResponse.class);
                return employeeListResponse;
            }
        });
        return new SuccessDataResult<>(employeeListResponses);
    }

    @Override
    public DataResult<Page<EmployeeListResponse>> getAllByPageWithOrder(int page, int size, String field, String order) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(order),field));
        var tempEmployee = employeeRepository.findAll(pageable);
        Page<EmployeeListResponse> employeeListResponses = tempEmployee.map(new Function<Employee, EmployeeListResponse>() {
            @Override
            public EmployeeListResponse apply(Employee employee) {
                EmployeeListResponse employeeListResponse = modelMapperService.forResponse().map(employee,EmployeeListResponse.class);
                return employeeListResponse;
            }
        });
        return new SuccessDataResult<>(employeeListResponses);
    }

    private void reportsToCheck(int reportsTo){
        List<Employee> reportsToList = employeeRepository.findEmployeeByReportsTo(reportsTo);
        if(reportsToList.size()>=10){
            throw new BusinessException("Bu adam dolu");
        }

    }

}
