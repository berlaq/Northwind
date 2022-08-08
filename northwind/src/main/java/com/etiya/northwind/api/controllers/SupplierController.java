package com.etiya.northwind.api.controllers;


import com.etiya.northwind.Business.Abstracts.SupplierService;
import com.etiya.northwind.Business.Responses.Suppliers.SupplierListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/getAll")
    public List<SupplierListResponse> getAll(){
        return this.supplierService.getAll();
    }

}
