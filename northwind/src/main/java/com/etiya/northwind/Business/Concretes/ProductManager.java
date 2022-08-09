package com.etiya.northwind.Business.Concretes;

import com.etiya.northwind.Business.Responses.Products.ProductListResponse;
import com.etiya.northwind.DataAccess.Abstracts.ProductRepository;
import com.etiya.northwind.Entities.Concretes.Product;
import com.etiya.northwind.Business.Abstracts.ProductService;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductManager implements ProductService {

    private ProductRepository productRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public ProductManager(ProductRepository productRepository, ModelMapperService modelMapperService) {
        this.productRepository = productRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<ProductListResponse> getAll() {
       List<Product> products= this.productRepository.findAll();
       List<ProductListResponse> productsDTO=
               products.stream()
                       .map(product -> this.modelMapperService.forResponse().map(product,ProductListResponse.class))
                       .collect(Collectors.toList());

       return  productsDTO;
    }
}
