package com.etiya.northwind.api.controllers;


import com.etiya.northwind.Business.Abstracts.SupplierService;
import com.etiya.northwind.Business.Responses.Products.ProductListResponse;
import com.etiya.northwind.Business.Responses.Suppliers.SupplierListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update")
    public ResponseEntity<String> updateSupplier(@RequestBody SupplierListResponse supplierListResponse ){
        this.supplierService.updateSupplier(supplierListResponse);
        return ResponseEntity.ok("Supplier is updated");
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<String> deleteSupplier(@PathVariable int supplierId ){
        this.supplierService.deleteSupplier(supplierId);
        return ResponseEntity.ok("Supplier is deleted");
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<SupplierListResponse> getSupplier(@PathVariable int supplierId){
        return ResponseEntity.ok(this.supplierService.getSupplierById(supplierId));
    }

    @PostMapping("/create")
    public ResponseEntity<String > createSupplier(@RequestBody SupplierListResponse supplierListResponse){
        this.supplierService.addSupplier(supplierListResponse);
        return  ResponseEntity.ok("Product is added");
    }

    @GetMapping("/getAllByPage")
    public ResponseEntity<Page<SupplierListResponse>> getAllSupplier2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size
    ){
        return ResponseEntity.ok(this.supplierService.getAllByPage(page, size));
    }
    @GetMapping("/getAllByPageWithField")
    public ResponseEntity<Page<SupplierListResponse>> getAllSupplier2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(name = "Filtrele") String field
    ){
        return ResponseEntity.ok(this.supplierService.getAllByPageWithField(page, size,field));
    }
    @GetMapping("/getAllByPageWithOrder")
    public ResponseEntity<Page<SupplierListResponse>> getAllSupplier2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(name = "Filtrele") String field,
            @RequestParam(name = "Sırala") String order
    ){
        return ResponseEntity.ok(this.supplierService.getAllByPageWithOrder(page, size,field,order));
    }

}
