package com.etiya.northwind.Business.Abstracts;

import com.etiya.northwind.Business.Responses.Products.ProductListResponse;

import java.util.List;

public interface ProductService {
    List<ProductListResponse> getAll();

}
