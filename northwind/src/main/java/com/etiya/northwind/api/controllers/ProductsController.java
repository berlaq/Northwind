package com.etiya.northwind.api.controllers;

import com.etiya.northwind.Business.Abstracts.ProductService;
import com.etiya.northwind.Business.Responses.Orders.OrderListResponse;
import com.etiya.northwind.Business.Responses.Products.ProductListResponse;
import com.etiya.northwind.Business.requests.products.CreateProductRequest;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@Validated
public class ProductsController {
    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public DataResult<List<ProductListResponse>>  getAll(){
        return this.productService.getAll();
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody @Valid ProductListResponse productListResponse ){
        this.productService.updateProduct(productListResponse);
        return ResponseEntity.ok("Product is updated");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable @NotNull int productId ){
        this.productService.deleteProduct(productId);
        return ResponseEntity.ok("Product is deleted");
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductListResponse> getProduct(@PathVariable @Max(5) int productId){
        return ResponseEntity.ok(this.productService.getProductById(productId));
    }

    @PostMapping("/create")
    public Result createProduct(@RequestBody @Valid CreateProductRequest createProductRequest){
       return this.productService.addProduct(createProductRequest);
    }

    @GetMapping("/getAllByPage")
    public ResponseEntity<Page<ProductListResponse>> getAllProduct2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size
    ){
        return ResponseEntity.ok(this.productService.getAllByPage(page, size));
    }
    @GetMapping("/getAllByPageWithField")
    public ResponseEntity<Page<ProductListResponse>> getAllProduct2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(name = "Filtrele") String field
    ){
        return ResponseEntity.ok(this.productService.getAllByPageWithField(page, size,field));
    }
    @GetMapping("/getAllByPageWithOrder")
    public ResponseEntity<Page<ProductListResponse>> getAllProduct2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(name = "Filtrele") String field,
            @RequestParam(name = "Sırala") String order
    ){
        return ResponseEntity.ok(this.productService.getAllByPageWithOrder(page, size,field,order));
    }
}
