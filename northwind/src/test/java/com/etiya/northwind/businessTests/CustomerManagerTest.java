package com.etiya.northwind.businessTests;

import com.etiya.northwind.Business.Abstracts.CustomerService;
import com.etiya.northwind.Business.Concretes.CustomerManager;
import com.etiya.northwind.Business.Responses.Customers.CustomerListResponse;
import com.etiya.northwind.Business.requests.customers.CreateCustomerRequest;
import com.etiya.northwind.DataAccess.Abstracts.CustomerRepository;
import com.etiya.northwind.Entities.Concretes.Customer;
import com.etiya.northwind.core.mapping.ModelMapperManager;
import com.etiya.northwind.core.mapping.ModelMapperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

public class CustomerManagerTest {
    private CustomerService customerService;
    private ModelMapper modelMapper;
    private ModelMapperService modelMapperService;
    private CustomerRepository customerRepository;


    @BeforeEach
    public void setup(){
        modelMapper = new ModelMapper();
        modelMapperService = new ModelMapperManager(modelMapper);
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerService = new CustomerManager(customerRepository,modelMapperService);
    }

    @Test
    public void add_customer_test(){
        var createSupplierRequest = new CreateCustomerRequest();
        customerService.addCustomer(createSupplierRequest);

        Mockito.verify(customerRepository,Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void update_customer_test(){
        CustomerListResponse customerListResponse = new CustomerListResponse();
        Customer expected = modelMapperService.forRequest().map(customerListResponse, Customer.class);

        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(expected);

        customerService.updateCustomer(customerListResponse);

        Mockito.verify(customerRepository,Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void delete_customer_test(){
        customerService.deleteCustomer(Mockito.anyString());
        Mockito.verify(customerRepository,Mockito.times(1)).deleteById(Mockito.anyString());
    }

    @Test
    public void getall_customer_test(){
        customerService.getAll();
        Mockito.verify(customerRepository,Mockito.times(1)).findAll();
    }

}
