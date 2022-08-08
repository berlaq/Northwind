package com.etiya.northwind.Business.Abstracts;

import com.etiya.northwind.Business.Responses.Suppliers.SupplierListResponse;

import java.util.List;

public interface SupplierService {
    List<SupplierListResponse> getAll();
}
