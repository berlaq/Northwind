package com.etiya.northwind.Business.Responses.Cart;


import com.etiya.northwind.Entities.Concretes.Customer;
import com.etiya.northwind.Entities.Concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartListResponse {

    private int cartId;

    private List<Product> product;

    private Customer customerId;

    private int quantity;

    private double unitPrice;
}
