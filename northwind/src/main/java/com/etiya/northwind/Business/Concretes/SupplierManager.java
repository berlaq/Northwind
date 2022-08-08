package com.etiya.northwind.Business.Concretes;

import com.etiya.northwind.Business.Abstracts.SupplierService;
import com.etiya.northwind.Business.Responses.Suppliers.SupplierListResponse;
import com.etiya.northwind.DataAccess.Abstracts.SupplierRepository;
import com.etiya.northwind.Entities.Concretes.Suppliers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierManager implements SupplierService {
    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierManager(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierListResponse> getAll() {
        List<Suppliers> suppliers = supplierRepository.findAll();
        List<SupplierListResponse> supplierListResponseList = new ArrayList<>();

        for (Suppliers supplier: suppliers) {
            SupplierListResponse supplierListResponse = new SupplierListResponse();
            supplierListResponse.setSupplierId(supplier.getSupplierId());
            supplierListResponse.setCompanyName(supplier.getCompanyName());

            supplierListResponseList.add(supplierListResponse);
        }
        return supplierListResponseList;
    }
}
