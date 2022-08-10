package com.etiya.northwind.Business.requests.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDetailRequest {

    @NotNull
    private int orderId;

    @NotNull
    private int productId;

    @NotNull
    private double unitPrice;

    @NotNull
    private int quantity;
}
