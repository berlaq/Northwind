package com.etiya.northwind.businessTests;

import com.etiya.northwind.Business.Abstracts.SupplierService;
import com.etiya.northwind.Business.Concretes.SupplierManager;
import com.etiya.northwind.Business.Responses.Suppliers.SupplierListResponse;
import com.etiya.northwind.Business.requests.Supplier.CreateSupplierRequest;
import com.etiya.northwind.DataAccess.Abstracts.SupplierRepository;
import com.etiya.northwind.Entities.Concretes.Suppliers;
import com.etiya.northwind.core.mapping.ModelMapperManager;
import com.etiya.northwind.core.mapping.ModelMapperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;


public class SupplierManagerTest {
    private SupplierService supplierService;
    private ModelMapper modelMapper;
    private ModelMapperService modelMapperService;
    private SupplierRepository supplierRepository;


    @BeforeEach
    public void setup(){
        modelMapper = new ModelMapper();
        modelMapperService = new ModelMapperManager(modelMapper);
        supplierRepository = Mockito.mock(SupplierRepository.class);
        supplierService = new SupplierManager(supplierRepository,modelMapperService);
    }

    @Test
    public void add_supplier_test(){
        var createSupplierRequest = createSupplierRequest();
        supplierService.addSupplier(createSupplierRequest);

        Mockito.verify(supplierRepository,Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void update_supplier_test(){
        SupplierListResponse supplierListResponse = supplierListResponse();
        Suppliers expected = modelMapperService.forRequest().map(supplierListResponse,Suppliers.class);

        Mockito.when(supplierRepository.save(Mockito.any())).thenReturn(expected);

        supplierService.updateSupplier(supplierListResponse);

        Mockito.verify(supplierRepository,Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void delete_supplier_test(){
        supplierService.deleteSupplier(Mockito.anyInt());
        Mockito.verify(supplierRepository,Mockito.times(1)).deleteById(Mockito.anyInt());
    }

    @Test
    public void getall_supplier_test(){
        supplierService.getAll();
        Mockito.verify(supplierRepository,Mockito.times(1)).findAll();
    }

    private CreateSupplierRequest createSupplierRequest(){
        CreateSupplierRequest createSupplierRequest = new CreateSupplierRequest();
        createSupplierRequest.setCompanyName("Test_Logistic");
        return createSupplierRequest;
    }

    private SupplierListResponse supplierListResponse(){
        SupplierListResponse supplierListResponse = new SupplierListResponse();
        return supplierListResponse;
    }
}
