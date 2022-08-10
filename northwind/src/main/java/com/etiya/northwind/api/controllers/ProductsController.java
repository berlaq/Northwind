package com.etiya.northwind.api.controllers;

import com.etiya.northwind.Business.Abstracts.ProductService;
import com.etiya.northwind.Business.Responses.Orders.OrderListResponse;
import com.etiya.northwind.Business.Responses.Products.ProductListResponse;
import com.etiya.northwind.Business.requests.products.CreateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductsController {
    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public List<ProductListResponse> getAll(){
        return this.productService.getAll();
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody ProductListResponse productListResponse ){
        this.productService.updateProduct(productListResponse);
        return ResponseEntity.ok("Product is updated");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId ){
        this.productService.deleteProduct(productId);
        return ResponseEntity.ok("Product is deleted");
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductListResponse> getProduct(@PathVariable int productId){
        return ResponseEntity.ok(this.productService.getProductById(productId));
    }

    @PostMapping("/create")
    public ResponseEntity<String > createProduct(@RequestBody CreateProductRequest createProductRequest){
        this.productService.addProduct(createProductRequest);
        return  ResponseEntity.ok("Product is added");
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
