package com.etiya.northwind.api.controllers;


import com.etiya.northwind.Business.Responses.Customers.CustomerListResponse;
import com.etiya.northwind.Business.Responses.Employees.EmployeeListResponse;
import com.etiya.northwind.Business.Abstracts.EmployeeService;
import com.etiya.northwind.Business.requests.Employee.CreateEmployeeRequest;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public DataResult<List<EmployeeListResponse>>  getAll(){
        return this.employeeService.getAll();
    }

    @PutMapping("/update")
    public Result updateEmployee(@RequestBody @Valid EmployeeListResponse employeeListResponse ){
       return this.employeeService.updateEmployee(employeeListResponse);
    }

    @DeleteMapping("/{employeeId}")
    public Result deleteEmployee(@PathVariable @Valid int employeeId ){
        return this.employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/{employeeId}")
    public DataResult<EmployeeListResponse> getEmployee(@PathVariable @Valid int employeeId){
        return this.employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("/create")
    public Result createEmployee(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest){
       return this.employeeService.addEmployee(createEmployeeRequest);
    }

    @GetMapping("/getAllByPage")
    public DataResult<Page<EmployeeListResponse>> getAllEmployee2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size
    ){
        return this.employeeService.getAllByPage(page, size);
    }
    @GetMapping("/getAllByPageWithField")
    public DataResult<Page<EmployeeListResponse>> getAllEmployee2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(name = "Filtrele") String field
    ){
        return this.employeeService.getAllByPageWithField(page, size,field);
    }
    @GetMapping("/getAllByPageWithOrder")
    public DataResult<Page<EmployeeListResponse>> getAllEmployee2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(name = "Filtrele") String field,
            @RequestParam(name = "Sırala") String order
    ){
        return this.employeeService.getAllByPageWithOrder(page, size,field,order);
    }
}
