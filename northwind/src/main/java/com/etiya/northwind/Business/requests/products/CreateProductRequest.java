package com.etiya.northwind.Business.requests.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

    @NotNull
    private String productName;
    @NotNull
    @PositiveOrZero
    private double unitPrice;
    @NotNull
    @PositiveOrZero
    private int unitInStock;
    @NotNull
    private int categoryId;
    @NotNull
    private int supplierId;
}
