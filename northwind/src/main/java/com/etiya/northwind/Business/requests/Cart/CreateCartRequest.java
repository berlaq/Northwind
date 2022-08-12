package com.etiya.northwind.Business.requests.Cart;

import com.etiya.northwind.Entities.Concretes.Customer;
import com.etiya.northwind.Entities.Concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCartRequest {

    @NotNull
    @NotBlank
    private int productId;

    @NotNull
    @NotBlank
    private String customerId;

    @NotNull
    private int quantity;

    @NotNull
    private double unitPrice;

}
