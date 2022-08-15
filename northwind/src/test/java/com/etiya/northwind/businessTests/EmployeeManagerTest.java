package com.etiya.northwind.businessTests;

import com.etiya.northwind.Business.Abstracts.EmployeeService;
import com.etiya.northwind.Business.Concretes.EmployeeManager;
import com.etiya.northwind.Business.Responses.Employees.EmployeeListResponse;
import com.etiya.northwind.Business.requests.Employee.CreateEmployeeRequest;
import com.etiya.northwind.DataAccess.Abstracts.EmployeeRepository;
import com.etiya.northwind.Entities.Concretes.Employee;
import com.etiya.northwind.core.mapping.ModelMapperManager;
import com.etiya.northwind.core.mapping.ModelMapperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

public class EmployeeManagerTest {
    private EmployeeService employeeService;
    private ModelMapper modelMapper;
    private ModelMapperService modelMapperService;
    private EmployeeRepository employeeRepository;


    @BeforeEach
    public void setup(){
        modelMapper = new ModelMapper();
        modelMapperService = new ModelMapperManager(modelMapper);
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeManager(employeeRepository,modelMapperService);
    }

    @Test
    public void add_employee(){
        var createEmployeeRequest = createEmployeeRequest();
        employeeService.addEmployee(createEmployeeRequest);

        Mockito.verify(employeeRepository,Mockito.times(1)).save(modelMapperService.
                forRequest()
                .map(createEmployeeRequest, Employee.class));
    }

    @Test
    public void update_employee_test(){
        EmployeeListResponse employeeListResponse = employeeListResponse();
        Employee expected = modelMapperService.forRequest().map(employeeListResponse,Employee.class);

        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(expected);

        employeeService.updateEmployee(employeeListResponse);

        Mockito.verify(employeeRepository,Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void delete_employee_test(){
        employeeService.deleteEmployee(Mockito.anyInt());
        Mockito.verify(employeeRepository,Mockito.times(1)).deleteById(Mockito.anyInt());
    }

    @Test
    public void getall_employee_test(){
        employeeService.getAll();
        Mockito.verify(employeeRepository,Mockito.times(1)).findAll();
    }

    private CreateEmployeeRequest createEmployeeRequest(){
        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();

        return createEmployeeRequest;
    }

    private EmployeeListResponse employeeListResponse(){
        EmployeeListResponse employeeListResponse = new EmployeeListResponse();

        return employeeListResponse;
    }
}