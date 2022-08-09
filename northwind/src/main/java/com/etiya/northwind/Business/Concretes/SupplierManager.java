package com.etiya.northwind.Business.Concretes;

import com.etiya.northwind.Business.Abstracts.SupplierService;
import com.etiya.northwind.Business.Responses.Suppliers.SupplierListResponse;
import com.etiya.northwind.DataAccess.Abstracts.SupplierRepository;
import com.etiya.northwind.Entities.Concretes.Suppliers;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierManager implements SupplierService {
    private SupplierRepository supplierRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public SupplierManager(SupplierRepository supplierRepository, ModelMapperService modelMapperService) {
        this.supplierRepository = supplierRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public List<SupplierListResponse> getAll() {
        List<Suppliers> suppliers = supplierRepository.findAll();
        List<SupplierListResponse> supplierListResponseList = suppliers.stream()
                .map(supplier -> modelMapperService.forResponse().map(supplier,SupplierListResponse.class))
                .collect(Collectors.toList());
        return supplierListResponseList;
    }
}
