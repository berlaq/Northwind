package com.etiya.northwind.api.controllers;


import com.etiya.northwind.Business.Responses.Customers.CustomerListResponse;
import com.etiya.northwind.Business.Abstracts.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getAll")
    public List<CustomerListResponse> getAll(){
        return this.customerService.getAll();
    }
}
