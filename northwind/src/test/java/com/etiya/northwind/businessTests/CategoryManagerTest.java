package com.etiya.northwind.businessTests;

import com.etiya.northwind.Business.Abstracts.CategoryService;
import com.etiya.northwind.Business.Abstracts.SupplierService;
import com.etiya.northwind.Business.Concretes.CategoryManager;
import com.etiya.northwind.Business.Concretes.SupplierManager;
import com.etiya.northwind.Business.Responses.Category.CategoryListResponse;
import com.etiya.northwind.Business.requests.Category.CreateCategoryRequest;
import com.etiya.northwind.DataAccess.Abstracts.CategoryRepository;
import com.etiya.northwind.DataAccess.Abstracts.SupplierRepository;
import com.etiya.northwind.Entities.Concretes.Category;
import com.etiya.northwind.core.mapping.ModelMapperManager;
import com.etiya.northwind.core.mapping.ModelMapperService;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class CategoryManagerTest {
    private CategoryService categoryService;
    private ModelMapper modelMapper;
    private ModelMapperService modelMapperService;
    private CategoryRepository categoryRepository;


    @BeforeEach
    public void setup(){
        modelMapper = new ModelMapper();
        modelMapperService = new ModelMapperManager(modelMapper);
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryService = new CategoryManager(categoryRepository,modelMapperService);
    }

    @Test
    public void add_category_test(){
        var createCategoryRequest = createCategoryRequest();
        categoryService.addCategory(createCategoryRequest);

        Mockito.verify(categoryRepository,Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void update_category_test(){
        CategoryListResponse categoryListResponse = categoryListResponse();
        Mockito.when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Category()));

        categoryService.updateCategory(categoryListResponse);

        Mockito.verify(categoryRepository,Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void delete_category_test(){
        Mockito.when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Category()));
        categoryService.deleteCategory(Mockito.anyInt());
        Mockito.verify(categoryRepository,Mockito.times(1)).deleteById(Mockito.anyInt());
    }

    @Test
    public void getall_category_test(){
        categoryService.getAll();
        Mockito.verify(categoryRepository,Mockito.times(1)).findAll();
    }

    private CreateCategoryRequest createCategoryRequest(){
        CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest();
        createCategoryRequest.setCategoryName("TEST");
        return createCategoryRequest;
    }

    private CategoryListResponse categoryListResponse(){
        CategoryListResponse categoryListResponse = new CategoryListResponse();
        categoryListResponse.setCategoryId(1);
        return categoryListResponse;
    }
}
