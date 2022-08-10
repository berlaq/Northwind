package com.etiya.northwind.Business.requests.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

    private String productName;
    private double unitPrice;
    private int unitInStock;
    private int categoryId;
    private int supplierId;
}
