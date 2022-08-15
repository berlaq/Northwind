package com.etiya.northwind.businessTests;

import com.etiya.northwind.Business.Abstracts.ProductService;
import com.etiya.northwind.Business.Concretes.ProductManager;
import com.etiya.northwind.Business.Responses.Products.ProductListResponse;
import com.etiya.northwind.Business.requests.products.CreateProductRequest;
import com.etiya.northwind.DataAccess.Abstracts.ProductRepository;
import com.etiya.northwind.Entities.Concretes.Product;
import com.etiya.northwind.core.Exceptions.BusinessException;
import com.etiya.northwind.core.mapping.ModelMapperManager;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.Optional;


public class ProductManagerTest {

    private ProductService productService;
    private ModelMapper modelMapper;
    private ModelMapperService modelMapperService;
    private ProductRepository productRepository;


    @BeforeEach
    public void setup(){
        modelMapper = new ModelMapper();
        modelMapperService = new ModelMapperManager(modelMapper);
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductManager(productRepository,modelMapperService);
    }


    @Test
    public void getById_product_test(){
        Product expected = createProduct();
        Mockito.when(productRepository.getReferenceById(Mockito.anyInt())).thenReturn(expected);
        DataResult<ProductListResponse> productListResponseDataResult = productService.getProductById(2);

        Mockito.verify(productRepository,Mockito.times(1)).getReferenceById(Mockito.anyInt());
    }

    @Test
    public void add_product_test(){

        var createProductRequest = createProductRequest();
        productService.addProduct(createProductRequest);

        Mockito.verify(productRepository,Mockito.times(1)).save(modelMapperService
                .forRequest().map(createProductRequest,Product.class));
    }

    @Test
    public void update_product_test(){
        Product expected = modelMapperService.forRequest().map(productListResponse(),Product.class);

        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(expected));

        productService.updateProduct(productListResponse());

        Mockito.verify(productRepository,Mockito.times(1)).save(expected);
    }

    @Test
    public void delete_product_test(){
        Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(createProduct()));

        productService.deleteProduct(2);

        Mockito.verify(productRepository,Mockito.times(1)).deleteById(Mockito.anyInt());
    }

    @Test
    public void getall_product_test(){
        productService.getAll();

        Mockito.verify(productRepository,Mockito.times(1)).findAll();
    }



    private Product createProduct(){
        Product product = new Product();
        product.setProductId(5);
        product.setProductName("limon");
        return product;
    }

    private CreateProductRequest createProductRequest(){
        CreateProductRequest createProductRequest = new CreateProductRequest();
        return createProductRequest;
    }

    private ProductListResponse productListResponse(){
        ProductListResponse productListResponse = new ProductListResponse();
        productListResponse.setProductId(1);
        return productListResponse;
    }

}
