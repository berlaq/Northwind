package com.etiya.northwind.api.controllers;

import com.etiya.northwind.Business.Abstracts.CategoryService;
import com.etiya.northwind.Business.Responses.Category.CategoryListResponse;
import com.etiya.northwind.Business.requests.Category.CreateCategoryRequest;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
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
    public DataResult<List<CategoryListResponse>> getAllCategory(){
        return this.categoryService.getAll();
    }

    @PutMapping("/update")
    public Result updateCategory(@RequestBody @Valid CategoryListResponse categoryListResponse ){
        return this.categoryService.updateCategory(categoryListResponse);
    }

    @DeleteMapping("/{categoryId}")
    public Result deleteCategory(@PathVariable @Valid int categoryId ){
       return this.categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/{categoryId}")
    public DataResult<CategoryListResponse> getCategory(@PathVariable @Valid int categoryId){
        return this.categoryService.getCategoryById(categoryId);
    }

    @PostMapping("/create")
    public Result createCategory(@RequestBody @Valid CreateCategoryRequest createCategoryRequest){
      return this.categoryService.addCategory(createCategoryRequest);
    }
}
