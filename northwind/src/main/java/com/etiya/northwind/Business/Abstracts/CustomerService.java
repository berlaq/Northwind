package com.etiya.northwind.Business.Abstracts;

import com.etiya.northwind.Business.Responses.Customers.CustomerListResponse;

import java.util.List;


public interface CustomerService {
    List<CustomerListResponse> getAll();
}
