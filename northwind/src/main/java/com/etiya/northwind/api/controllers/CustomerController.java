package com.etiya.northwind.api.controllers;


import com.etiya.northwind.Business.Responses.Customers.CustomerListResponse;
import com.etiya.northwind.Business.Abstracts.CustomerService;
import com.etiya.northwind.Business.Responses.Products.ProductListResponse;
import com.etiya.northwind.Entities.Concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/getAllByPage")
    public ResponseEntity<Page<CustomerListResponse>> getAllCustomer2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size
    ){
        return ResponseEntity.ok(this.customerService.getAllByPage(page, size));
    }
    @GetMapping("/getAllByPageWithField")
    public ResponseEntity<Page<CustomerListResponse>> getAllCustomer2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(name = "Filtrele") String field
    ){
        return ResponseEntity.ok(this.customerService.getAllByPageWithField(page, size,field));
    }
    @GetMapping("/getAllByPageWithOrder")
    public ResponseEntity<Page<CustomerListResponse>> getAllCustomer2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(name = "Filtrele") String field,
            @RequestParam(name = "Sırala") String order
    ){
        return ResponseEntity.ok(this.customerService.getAllByPageWithOrder(page, size,field,order));
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerListResponse>> getAllCustomer(){
        return ResponseEntity.ok(this.customerService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProd(@RequestBody CustomerListResponse customerListResponse ){
        this.customerService.updateCustomer(customerListResponse);
        return ResponseEntity.ok("Customer is updated");
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteProd(@PathVariable String customerId ){
        this.customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Customer is deleted");
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerListResponse> getProd(@PathVariable String customerId){
        return ResponseEntity.ok(this.customerService.getCustomerById(customerId));
    }

    @PostMapping("/create")
    public ResponseEntity<String > createProd(@RequestBody CustomerListResponse customerListResponse){
        this.customerService.addCustomer(customerListResponse);
        return  ResponseEntity.ok("Customer is added");
    }

}
