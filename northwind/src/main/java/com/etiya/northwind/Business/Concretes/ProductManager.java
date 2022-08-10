package com.etiya.northwind.Business.Concretes;

import com.etiya.northwind.Business.Responses.Products.ProductListResponse;
import com.etiya.northwind.Business.requests.products.CreateProductRequest;
import com.etiya.northwind.DataAccess.Abstracts.ProductRepository;
import com.etiya.northwind.Entities.Concretes.Product;
import com.etiya.northwind.Business.Abstracts.ProductService;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
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
       List<ProductListResponse> productsDTO= products
               .stream()
                       .map(product -> this.modelMapperService.forResponse().map(product,ProductListResponse.class))
                       .collect(Collectors.toList());
       return  productsDTO;
    }

    @Override
    public void updateProduct(ProductListResponse productListResponse) {
        productRepository.save(modelMapperService.forRequest().map(productListResponse, Product.class));
    }

    @Override
    public void deleteProduct(int productId) {
        this.productRepository.deleteById(productId);
    }

    @Override
    public ProductListResponse getProductById(int productId) {
        var temp = this.productRepository.getReferenceById(productId);
        ProductListResponse productListResponse = modelMapperService.forResponse()
                .map(temp, ProductListResponse.class);

        return productListResponse;
    }

    @Override
    public void addProduct(CreateProductRequest createProductRequest) {
        this.productRepository.save(modelMapperService.forRequest().map(createProductRequest, Product.class));
    }

    @Override
    public Page<ProductListResponse> getAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        var tempProduct = productRepository.findAll(pageable);
        Page<ProductListResponse> productListResponses = tempProduct.map(new Function<Product, ProductListResponse>() {
            @Override
            public ProductListResponse apply(Product product) {
                ProductListResponse  productListResponse = modelMapperService.forResponse().map(product,ProductListResponse.class);
                return productListResponse;
            }
        });
        return productListResponses;
    }

    @Override
    public Page<ProductListResponse> getAllByPageWithField(int page, int size, String field) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(field));
        var tempProduct = productRepository.findAll(pageable);
        Page<ProductListResponse> productListResponses = tempProduct.map(new Function<Product, ProductListResponse>() {
            @Override
            public ProductListResponse apply(Product product) {
                ProductListResponse  productListResponse = modelMapperService.forResponse().map(product,ProductListResponse.class);
                return productListResponse;
            }
        });
        return productListResponses;
    }

    @Override
    public Page<ProductListResponse> getAllByPageWithOrder(int page, int size, String field, String order) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(order),field));
        var tempProduct = productRepository.findAll(pageable);
        Page<ProductListResponse> productListResponses = tempProduct.map(new Function<Product, ProductListResponse>() {
            @Override
            public ProductListResponse apply(Product product) {
                ProductListResponse  productListResponse = modelMapperService.forResponse().map(product,ProductListResponse.class);
                return productListResponse;
            }
        });
        return productListResponses;
    }
}
