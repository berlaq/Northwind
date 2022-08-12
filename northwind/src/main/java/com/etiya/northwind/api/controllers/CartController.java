package com.etiya.northwind.api.controllers;

import com.etiya.northwind.Business.Abstracts.CartService;
import com.etiya.northwind.Business.Responses.Cart.CartListResponse;
import com.etiya.northwind.Business.requests.Cart.CreateCartRequest;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private CartService cartService;

    @PostMapping("/create")
    Result addCart(@RequestBody @Valid CreateCartRequest createCartRequest){
        return this.cartService.addCart(createCartRequest);
    }

    @PutMapping("/update")
    Result updateCart(@RequestBody @Valid CartListResponse cartListResponse){
        return this.cartService.updateCart(cartListResponse);
    }

    @DeleteMapping("/{cartId}")
    Result deleteCart(@PathVariable  int cartId){
        return this.cartService.deleteCart(cartId);
    }

    @DeleteMapping("/{custId}")
    Result deleteCart(@PathVariable  String custId){
        return this.cartService.deleteCart(custId);
    }

}
