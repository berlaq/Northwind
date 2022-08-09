package com.etiya.northwind.DataAccess.Abstracts;

import com.etiya.northwind.Business.Responses.Customers.CustomerListResponse;
import com.etiya.northwind.Entities.Concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends JpaRepository<Customer,String>, PagingAndSortingRepository<Customer,String> {
}
