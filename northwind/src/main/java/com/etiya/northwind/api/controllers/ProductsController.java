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
    public Result updateProduct(@RequestBody @Valid ProductListResponse productListResponse ){
       return this.productService.updateProduct(productListResponse);
    }

    @DeleteMapping("/{productId}")
    public Result deleteProduct(@PathVariable @NotNull int productId ){
       return this.productService.deleteProduct(productId);
    }

    @GetMapping("/{productId}")
    public DataResult<ProductListResponse> getProduct(@PathVariable @Max(5) int productId){
        return this.productService.getProductById(productId);
    }

    @PostMapping("/create")
    public Result createProduct(@RequestBody @Valid CreateProductRequest createProductRequest){
       return this.productService.addProduct(createProductRequest);
    }

    @GetMapping("/getAllByPage")
    public DataResult<Page<ProductListResponse>> getAllProduct2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size
    ){
        return this.productService.getAllByPage(page, size);
    }
    @GetMapping("/getAllByPageWithField")
    public DataResult<Page<ProductListResponse>> getAllProduct2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(name = "Filtrele") String field
    ){
        return this.productService.getAllByPageWithField(page, size,field);
    }
    @GetMapping("/getAllByPageWithOrder")
    public DataResult<Page<ProductListResponse>> getAllProduct2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(name = "Filtrele") String field,
            @RequestParam(name = "Sırala") String order
    ){
        return this.productService.getAllByPageWithOrder(page, size,field,order);
    }
}
