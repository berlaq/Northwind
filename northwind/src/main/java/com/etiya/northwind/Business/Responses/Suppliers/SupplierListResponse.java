package com.etiya.northwind.Business.Responses.Suppliers;

import com.etiya.northwind.Entities.Concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierListResponse {

    private int supplierId;
    private String companyName;

}
