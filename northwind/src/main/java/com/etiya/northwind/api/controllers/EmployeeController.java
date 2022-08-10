package com.etiya.northwind.api.controllers;


import com.etiya.northwind.Business.Responses.Customers.CustomerListResponse;
import com.etiya.northwind.Business.Responses.Employees.EmployeeListResponse;
import com.etiya.northwind.Business.Abstracts.EmployeeService;
import com.etiya.northwind.Business.requests.Employee.CreateEmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update")
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeListResponse employeeListResponse ){
        this.employeeService.updateEmployee(employeeListResponse);
        return ResponseEntity.ok("Employee is updated");
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId ){
        this.employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee is deleted");
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeListResponse> getEmployee(@PathVariable int employeeId){
        return ResponseEntity.ok(this.employeeService.getEmployeeById(employeeId));
    }

    @PostMapping("/create")
    public ResponseEntity<String > createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest){
        this.employeeService.addEmployee(createEmployeeRequest);
        return  ResponseEntity.ok("Employee is added");
    }

    @GetMapping("/getAllByPage")
    public ResponseEntity<Page<EmployeeListResponse>> getAllEmployee2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size
    ){
        return ResponseEntity.ok(this.employeeService.getAllByPage(page, size));
    }
    @GetMapping("/getAllByPageWithField")
    public ResponseEntity<Page<EmployeeListResponse>> getAllEmployee2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(name = "Filtrele") String field
    ){
        return ResponseEntity.ok(this.employeeService.getAllByPageWithField(page, size,field));
    }
    @GetMapping("/getAllByPageWithOrder")
    public ResponseEntity<Page<EmployeeListResponse>> getAllEmployee2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(name = "Filtrele") String field,
            @RequestParam(name = "Sırala") String order
    ){
        return ResponseEntity.ok(this.employeeService.getAllByPageWithOrder(page, size,field,order));
    }
}
