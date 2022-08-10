package com.etiya.northwind.api.controllers;

import com.etiya.northwind.Business.Abstracts.CategoryService;
import com.etiya.northwind.Business.Responses.Category.CategoryListResponse;
import com.etiya.northwind.Business.requests.Category.CreateCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<String> updateCategory(@RequestBody @Valid CategoryListResponse categoryListResponse ){
        this.categoryService.updateCategory(categoryListResponse);
        return ResponseEntity.ok("Category is updated");
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable @Valid int categoryId ){
        this.categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category is deleted");
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryListResponse> getCategory(@PathVariable @Valid int categoryId){
        return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
    }

    @PostMapping("/create")
    public ResponseEntity<String > createCategory(@RequestBody @Valid CreateCategoryRequest createCategoryRequest){
        this.categoryService.addCategory(createCategoryRequest);
        return  ResponseEntity.ok("Category is added");
    }
}
