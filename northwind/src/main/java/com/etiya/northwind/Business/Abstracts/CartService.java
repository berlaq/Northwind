package com.etiya.northwind.Business.Abstracts;

import com.etiya.northwind.Business.Responses.Cart.CartListResponse;
import com.etiya.northwind.Business.requests.Cart.CreateCartRequest;
import com.etiya.northwind.Entities.Concretes.Cart;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;

public interface CartService {
   Result addCart(CreateCartRequest createCartRequest);
   Result updateCart(CartListResponse cartListResponse);
   Result deleteCart(int cartId);
   Result deleteCart(String customerId);
}
