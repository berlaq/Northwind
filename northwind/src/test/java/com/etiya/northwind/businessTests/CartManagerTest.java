package com.etiya.northwind.businessTests;

import com.etiya.northwind.Business.Abstracts.CartService;
import com.etiya.northwind.Business.Concretes.CartManager;
import com.etiya.northwind.Business.requests.Cart.CreateCartRequest;
import com.etiya.northwind.Business.requests.Cart.UpdateCartRequest;
import com.etiya.northwind.DataAccess.Abstracts.CartRepository;
import com.etiya.northwind.Entities.Concretes.Cart;
import com.etiya.northwind.core.mapping.ModelMapperManager;
import com.etiya.northwind.core.mapping.ModelMapperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

public class CartManagerTest {
    private CartService cartService;
    private ModelMapper modelMapper;
    private ModelMapperService modelMapperService;
    private CartRepository cartRepository;


    @BeforeEach
    public void setup(){
        modelMapper = new ModelMapper();
        modelMapperService = new ModelMapperManager(modelMapper);
        cartRepository = Mockito.mock(CartRepository.class);
        cartService = new CartManager(cartRepository,modelMapperService);
    }

    @Test
    public void add_cart_test(){
        var createCartRequest = new CreateCartRequest();
        cartService.addCart(createCartRequest);

        Mockito.verify(cartRepository,Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void update_cart_test(){
        UpdateCartRequest updateCartRequest = new UpdateCartRequest();
        Cart expected = modelMapperService.forRequest().map(updateCartRequest,Cart.class);

        Mockito.when(cartRepository.save(Mockito.any())).thenReturn(expected);

        cartService.updateCart(updateCartRequest);

        Mockito.verify(cartRepository,Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void delete_by_cart_id_test(){
        cartService.deleteByCartId(Mockito.anyInt());
        Mockito.verify(cartRepository,Mockito.times(1)).deleteById(Mockito.anyInt());
    }

    @Test
    public void delete_by_customer_id_test(){
        cartService.deleteByCustomerId(Mockito.anyString());
        Mockito.verify(cartRepository,Mockito.times(1)).deleteAllByCustomer_CustomerId(Mockito.anyString());
    }


}
