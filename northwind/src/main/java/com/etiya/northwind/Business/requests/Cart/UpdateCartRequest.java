package com.etiya.northwind.Business.requests.Cart;

import com.etiya.northwind.Entities.Concretes.Customer;
import com.etiya.northwind.Entities.Concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartRequest {

    @NotBlank
    @NotNull
    private int cartId;

    @NotBlank
    @NotNull
    private int productId;

    @NotBlank
    @NotNull
    private int customerId;

    @NotNull
    @PositiveOrZero
    private int quantity;

    @Positive
    private double unitPrice;
}
