package com.etiya.northwind.api.controllers;

import com.etiya.northwind.Business.Abstracts.CategoryService;
import com.etiya.northwind.Business.Responses.Category.CategoryListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryListResponse>> getAllCategory(){
        return ResponseEntity.ok(this.categoryService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCategory(@RequestBody CategoryListResponse categoryListResponse ){
        this.categoryService.updateCategory(categoryListResponse);
        return ResponseEntity.ok("Category is updated");
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable int categoryId ){
        this.categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category is deleted");
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryListResponse> getCategory(@PathVariable int categoryId){
        return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
    }

    @PostMapping("/create")
    public ResponseEntity<String > createCategory(@RequestBody CategoryListResponse categoryListResponse){
        this.categoryService.addCategory(categoryListResponse);
        return  ResponseEntity.ok("Category is added");
    }
}
