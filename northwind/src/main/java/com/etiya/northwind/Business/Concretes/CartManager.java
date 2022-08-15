package com.etiya.northwind.Business.Concretes;

import com.etiya.northwind.Business.Abstracts.CartService;
import com.etiya.northwind.Business.requests.Cart.CreateCartRequest;
import com.etiya.northwind.Business.requests.Cart.UpdateCartRequest;
import com.etiya.northwind.DataAccess.Abstracts.CartRepository;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.Entities.Concretes.Cart;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.core.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartManager implements CartService {

    private CartRepository cartRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public CartManager(CartRepository cartRepository,ModelMapperService modelMapperService) {
        this.cartRepository = cartRepository;
        this.modelMapperService=modelMapperService;
    }

    @Override
    public Result addCart(CreateCartRequest createCartRequest) {
        this.cartRepository.save(modelMapperService.forRequest().map(createCartRequest, Cart.class));
        return new SuccessResult("Eklendi");
    }

    @Override
    public Result updateCart(UpdateCartRequest updateCartRequest) {
        this.cartRepository.save(modelMapperService.forRequest().map(updateCartRequest,Cart.class));
        return new SuccessResult("Güncellendi");
    }

    @Override
    public Result deleteByCartId(int cartId) {
        this.cartRepository.deleteById(cartId);
        return new SuccessResult("Silindi");
    }

    public Result deleteByCustomerId(String custId){
        this.cartRepository.deleteAllByCustomer_CustomerId(custId);
        return new SuccessResult("Silindi");
    }

}
