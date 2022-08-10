package com.etiya.northwind.Business.Responses.OrderDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailidResponse {

    private int orderId;
    private int productId;

}
