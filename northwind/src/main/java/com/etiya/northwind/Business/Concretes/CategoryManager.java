package com.etiya.northwind.Business.Concretes;

import com.etiya.northwind.Business.Abstracts.CategoryService;
import com.etiya.northwind.Business.Responses.Category.CategoryListResponse;
import com.etiya.northwind.Business.requests.Category.CreateCategoryRequest;
import com.etiya.northwind.DataAccess.Abstracts.CategoryRepository;
import com.etiya.northwind.Entities.Concretes.Category;
import com.etiya.northwind.core.Exceptions.BusinessException;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.core.utilities.results.SuccessDataResult;
import com.etiya.northwind.core.utilities.results.SuccessResult;
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
    public DataResult<List<CategoryListResponse>> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryListResponse> categoryListResponses = categories.stream()
                .map(category -> modelMapperService.forResponse().map(category, CategoryListResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(categoryListResponses);
    }

    @Override
    public Result updateCategory(CategoryListResponse categoryListResponse) {
        checkIdExists(categoryListResponse.getCategoryId());
        checkNameExist(categoryListResponse.getCategoryName());
        categoryRepository.save(modelMapperService.forRequest().map(categoryListResponse, Category.class));
        return new SuccessResult("güncellendi");
    }

    @Override
    public Result deleteCategory(int categoryId) {
        checkIdExists(categoryId);
        categoryRepository.deleteById(categoryId);
        return new SuccessResult("Silindi");
    }

    @Override
    public DataResult<CategoryListResponse> getCategoryById(int categoryId) {
        CategoryListResponse categoryListResponse = modelMapperService.forResponse()
                .map(categoryRepository.getReferenceById(categoryId),CategoryListResponse.class);
        return new SuccessDataResult<>(categoryListResponse,"getirildi");
    }

    @Override
    public Result addCategory(CreateCategoryRequest createCategoryRequest) {
        checkNameExist(createCategoryRequest.getCategoryName());
        categoryRepository.save(modelMapperService.forRequest().map(createCategoryRequest,Category.class));
        return new SuccessResult("Eklendi");
    }
    private void checkIdExists(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category==null){
            throw new BusinessException("BÖLE BİŞE YOK");
        }
    }
    private void checkNameExist(String name){
        Category category = categoryRepository.findCategoryByCategoryName(name);
        if (category!=null){
            throw new BusinessException("BÖYLE BİR KATEGORİ ZATEN VAR!!!!");
        }
    }
}
