package com.etiya.northwind.Business.requests.Cart;

import com.etiya.northwind.Entities.Concretes.Customer;
import com.etiya.northwind.Entities.Concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCartRequest {


    private int productId;

    @NotNull
    @NotBlank
    private String customerId;

    @PositiveOrZero
    private int quantity;

    @PositiveOrZero
    private double unitPrice;

}
