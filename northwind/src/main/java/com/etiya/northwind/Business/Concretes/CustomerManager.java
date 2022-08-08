package com.etiya.northwind.Business.Concretes;

import com.etiya.northwind.Business.Responses.Customers.CustomerListResponse;
import com.etiya.northwind.Entities.Concretes.Customer;
import com.etiya.northwind.DataAccess.Abstracts.CustomerRepository;
import com.etiya.northwind.Business.Abstracts.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerManager implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerManager(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerListResponse> getAll() {
        List<Customer> customers=this.customerRepository.findAll();
        List<CustomerListResponse> customersDto=new ArrayList<CustomerListResponse>();

        for(Customer customer:customers){
            CustomerListResponse customerDTO=new CustomerListResponse();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setCompanyName(customer.getCompanyName());
            customerDTO.setContactName(customer.getContactName());
            customersDto.add(customerDTO);
        }

        return customersDto;
    }
}
