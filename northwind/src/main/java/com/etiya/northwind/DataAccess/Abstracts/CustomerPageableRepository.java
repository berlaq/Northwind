package com.etiya.northwind.DataAccess.Abstracts;

import com.etiya.northwind.Entities.Concretes.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerPageableRepository extends PagingAndSortingRepository<Customer,String> {
}
