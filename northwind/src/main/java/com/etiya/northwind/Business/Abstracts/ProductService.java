package com.etiya.northwind.Business.Abstracts;

import com.etiya.northwind.Business.Responses.Orders.OrderListResponse;
import com.etiya.northwind.Business.Responses.Products.ProductListResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<ProductListResponse> getAll();
    void updateProduct(ProductListResponse productListResponse);
    void deleteProduct(int productId);
    ProductListResponse getProductById(int productId);
    void addProduct(ProductListResponse productListResponse);

    Page<ProductListResponse> getAllByPage(int page, int size);
    Page<ProductListResponse>  getAllByPageWithField(int page,int size,String field);
    Page<ProductListResponse>  getAllByPageWithOrder(int page,int size,String field,String order);

}
