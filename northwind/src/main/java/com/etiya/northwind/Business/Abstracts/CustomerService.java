package com.etiya.northwind.Business.Abstracts;

import com.etiya.northwind.Business.Responses.Customers.CustomerListResponse;
import com.etiya.northwind.Business.requests.customers.CreateCustomerRequest;
import com.etiya.northwind.Entities.Concretes.Customer;
import org.springframework.data.domain.Page;

import java.util.List;


public interface CustomerService {
    List<CustomerListResponse> getAll();
    void updateCustomer(CustomerListResponse customerListResponse);
    void deleteCustomer(String customerId);
    CustomerListResponse getCustomerById(String customerId);

    void addCustomer(CreateCustomerRequest createCustomerRequest);

    Page<CustomerListResponse> getAllByPage(int page, int size);
    Page<CustomerListResponse>  getAllByPageWithField(int page,int size,String field);
    Page<CustomerListResponse>  getAllByPageWithOrder(int page,int size,String field,String order);
}
