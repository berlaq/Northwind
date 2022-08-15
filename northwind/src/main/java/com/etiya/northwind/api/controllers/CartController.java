package com.etiya.northwind.api.controllers;

import com.etiya.northwind.Business.Abstracts.CartService;
import com.etiya.northwind.Business.Responses.Cart.CartListResponse;
import com.etiya.northwind.Business.requests.Cart.CreateCartRequest;
import com.etiya.northwind.Business.requests.Cart.UpdateCartRequest;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create")
    Result addCart(@RequestBody @Valid CreateCartRequest createCartRequest){
        return this.cartService.addCart(createCartRequest);
    }

    @PutMapping("/update")
    Result updateCart(@RequestBody @Valid UpdateCartRequest updateCartRequest){
        return this.cartService.updateCart(updateCartRequest);
    }

    @DeleteMapping("/deleteByCartId/{cartId}")
    Result deleteCart(@PathVariable  int cartId){
        return this.cartService.deleteByCartId(cartId);
    }

    @DeleteMapping("/deleteByCustomerId")
    Result deleteCart(@RequestParam  String custId){
        return this.cartService.deleteByCustomerId(custId);
    }

}
