package com.etiya.northwind.Business.Responses.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponse {

    private int productId;
    private String productName;
    private double unitPrice;
    private int unitInStock;
    private int categoryId;

    private int supplierId;

}
