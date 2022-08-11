package com.etiya.northwind.Business.Concretes;

import com.etiya.northwind.Business.Abstracts.CategoryService;
import com.etiya.northwind.Business.Responses.Category.CategoryListResponse;
import com.etiya.northwind.Business.requests.Category.CreateCategoryRequest;
import com.etiya.northwind.DataAccess.Abstracts.CategoryRepository;
import com.etiya.northwind.Entities.Concretes.Category;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryManager implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public CategoryManager(CategoryRepository categoryRepository, ModelMapperService modelMapperService) {
        this.categoryRepository = categoryRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<CategoryListResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryListResponse> categoryListResponses = categories.stream()
                .map(category -> modelMapperService.forResponse().map(category, CategoryListResponse.class))
                .collect(Collectors.toList());
        return categoryListResponses;
    }

    @Override
    public void updateCategory(CategoryListResponse categoryListResponse) {
        categoryRepository.save(modelMapperService.forRequest().map(categoryListResponse, Category.class));
    }

    @Override
    public void deleteCategory(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryListResponse getCategoryById(int categoryId) {
        CategoryListResponse categoryListResponse = modelMapperService.forResponse()
                .map(categoryRepository.getReferenceById(categoryId),CategoryListResponse.class);
        return categoryListResponse;
    }

    @Override
    public void addCategory(CreateCategoryRequest createCategoryRequest) {
        categoryRepository.save(modelMapperService.forRequest().map(createCategoryRequest,Category.class));
    }

}
