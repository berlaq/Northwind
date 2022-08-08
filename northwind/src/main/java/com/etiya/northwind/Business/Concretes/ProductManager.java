package com.etiya.northwind.Business.Concretes;

import com.etiya.northwind.Business.Responses.Products.ProductListResponse;
import com.etiya.northwind.DataAccess.Abstracts.ProductRepository;
import com.etiya.northwind.Entities.Concretes.Product;
import com.etiya.northwind.Business.Abstracts.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductManager implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductListResponse> getAll() {
       List<Product> products= this.productRepository.findAll();
       List<ProductListResponse> productsDTO= new ArrayList<ProductListResponse>();
       for(Product product:products){
           ProductListResponse responseProduct=new ProductListResponse();
           responseProduct.setProductId(product.getProductId());
           responseProduct.setProductName(product.getProductName());
           responseProduct.setUnitPrice(product.getUnitPrice());
           responseProduct.setUnitInStock(product.getUnitInStock());
           responseProduct.setCategoryId(product.getCategory().getCategoryId());
           responseProduct.setCategoryName(product.getCategory().getCategoryName());
           productsDTO.add(responseProduct);
       }
       return  productsDTO;
    }
}
