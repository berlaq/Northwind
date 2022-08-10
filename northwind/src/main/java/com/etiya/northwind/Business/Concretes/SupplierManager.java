package com.etiya.northwind.Business.Concretes;

import com.etiya.northwind.Business.Abstracts.SupplierService;
import com.etiya.northwind.Business.Responses.Suppliers.SupplierListResponse;
import com.etiya.northwind.Business.requests.Supplier.CreateSupplierRequest;
import com.etiya.northwind.DataAccess.Abstracts.SupplierRepository;
import com.etiya.northwind.Entities.Concretes.Suppliers;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
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

    @Override
    public void updateSupplier(SupplierListResponse supplierListResponse) {
        supplierRepository.save(modelMapperService.forRequest().map(supplierListResponse, Suppliers.class));
    }

    @Override
    public void deleteSupplier(int supplierId) {
        this.supplierRepository.deleteById(supplierId);
    }

    @Override
    public SupplierListResponse getSupplierById(int supplierId) {
        var temp = this.supplierRepository.getReferenceById(supplierId);
        SupplierListResponse supplierListResponse = modelMapperService.forResponse()
                .map(temp, SupplierListResponse.class);
        return supplierListResponse;
    }

    @Override
    public void addSupplier(CreateSupplierRequest createSupplierRequest) {
        this.supplierRepository.save(modelMapperService.forRequest().map(createSupplierRequest, Suppliers.class));
    }

    @Override
    public Page<SupplierListResponse> getAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        var tempProduct = supplierRepository.findAll(pageable);
        Page<SupplierListResponse> supplierListResponses = tempProduct.map(new Function<Suppliers, SupplierListResponse>() {
            @Override
            public SupplierListResponse apply(Suppliers suppliers) {
                SupplierListResponse  supplierListResponse = modelMapperService.forResponse().map(suppliers,SupplierListResponse.class);
                return supplierListResponse;
            }
        });
        return supplierListResponses;
    }

    @Override
    public Page<SupplierListResponse> getAllByPageWithField(int page, int size, String field) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(field));;
        var tempProduct = supplierRepository.findAll(pageable);
        Page<SupplierListResponse> supplierListResponses = tempProduct.map(new Function<Suppliers, SupplierListResponse>() {
            @Override
            public SupplierListResponse apply(Suppliers suppliers) {
                SupplierListResponse  supplierListResponse = modelMapperService.forResponse().map(suppliers,SupplierListResponse.class);
                return supplierListResponse;
            }
        });
        return supplierListResponses;
    }

    @Override
    public Page<SupplierListResponse> getAllByPageWithOrder(int page, int size, String field, String order) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(order),field));
        var tempProduct = supplierRepository.findAll(pageable);
        Page<SupplierListResponse> supplierListResponses = tempProduct.map(new Function<Suppliers, SupplierListResponse>() {
            @Override
            public SupplierListResponse apply(Suppliers suppliers) {
                SupplierListResponse  supplierListResponse = modelMapperService.forResponse().map(suppliers,SupplierListResponse.class);
                return supplierListResponse;
            }
        });
        return supplierListResponses;
    }
}
