package com.etiya.northwind.Business.Abstracts;

import com.etiya.northwind.Business.Responses.Category.CategoryListResponse;
import com.etiya.northwind.Business.requests.Category.CreateCategoryRequest;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;


import java.util.List;

public interface CategoryService {

    DataResult<List<CategoryListResponse>>  getAll();
    Result updateCategory(CategoryListResponse categoryListResponse);
    Result deleteCategory(int categoryId);
    DataResult<CategoryListResponse>  getCategoryById(int categoryId);
    Result addCategory(CreateCategoryRequest createCategoryRequest);

}
