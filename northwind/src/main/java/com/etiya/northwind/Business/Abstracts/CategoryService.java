package com.etiya.northwind.Business.Abstracts;

import com.etiya.northwind.Business.Responses.Category.CategoryListResponse;


import java.util.List;

public interface CategoryService {

    List<CategoryListResponse> getAll();
    void updateCategory(CategoryListResponse categoryListResponse);
    void deleteCategory(int categoryId);
    CategoryListResponse getCategoryById(int categoryId);
    void addCategory(CategoryListResponse categoryListResponse);

}
