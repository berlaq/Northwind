package com.etiya.northwind.Business.Abstracts;

import com.etiya.northwind.Business.Responses.Suppliers.SupplierListResponse;
import com.etiya.northwind.Business.requests.Supplier.CreateSupplierRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SupplierService {
    List<SupplierListResponse> getAll();
    void updateSupplier(SupplierListResponse supplierListResponse);
    void deleteSupplier(int supplierId);
    SupplierListResponse getSupplierById(int supplierId);
    void addSupplier(CreateSupplierRequest createSupplierRequest);

    Page<SupplierListResponse> getAllByPage(int page, int size);
    Page<SupplierListResponse>  getAllByPageWithField(int page,int size,String field);
    Page<SupplierListResponse>  getAllByPageWithOrder(int page,int size,String field,String order);
}
