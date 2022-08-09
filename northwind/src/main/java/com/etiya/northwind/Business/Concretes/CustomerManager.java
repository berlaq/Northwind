package com.etiya.northwind.Business.Concretes;

import com.etiya.northwind.Business.Responses.Customers.CustomerListResponse;
import com.etiya.northwind.DataAccess.Abstracts.CustomerPageableRepository;
import com.etiya.northwind.Entities.Concretes.Customer;
import com.etiya.northwind.DataAccess.Abstracts.CustomerRepository;
import com.etiya.northwind.Business.Abstracts.CustomerService;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import io.swagger.v3.oas.models.examples.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.Converter;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class CustomerManager implements CustomerService {

    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;
    private CustomerPageableRepository customerPageableRepository;

    @Autowired
    public CustomerManager(CustomerRepository customerRepository, ModelMapperService modelMapperService, CustomerPageableRepository customerPageableRepository) {
        this.customerRepository = customerRepository;
        this.modelMapperService = modelMapperService;
        this.customerPageableRepository = customerPageableRepository;
    }
    @Override
    public Page<CustomerListResponse>  getAllByPage(int page, int size){
        Pageable pageable = createPageable(page,size);
        var tempCustomer = customerPageableRepository.findAll(pageable);
        Page<CustomerListResponse> customerListResponses = tempCustomer.map(new Function<Customer, CustomerListResponse>() {
            @Override
            public CustomerListResponse apply(Customer customer) {
                CustomerListResponse customerListResponse = new CustomerListResponse();
                customerListResponse = modelMapperService.forResponse().map(customer,CustomerListResponse.class);
                return customerListResponse;
            }
        });
        return customerListResponses;
    }
    @Override
    public Page<CustomerListResponse>  getAllByPageWithField(int page,int size,String field){
        Pageable pageable = createPageable(page,size,field);
        var tempCustomer = customerPageableRepository.findAll(pageable);
        Page<CustomerListResponse> customerListResponses = tempCustomer.map(new Function<Customer, CustomerListResponse>() {
            @Override
            public CustomerListResponse apply(Customer customer) {
                CustomerListResponse customerListResponse = new CustomerListResponse();
                customerListResponse = modelMapperService.forResponse().map(customer,CustomerListResponse.class);
                return customerListResponse;
            }
        });
        return customerListResponses;
    }
    @Override
    public Page<CustomerListResponse>  getAllByPageWithOrder(int page,int size,String field,String order){
        Pageable pageable = createPageable(page,size,field,order);
        var tempCustomer = customerPageableRepository.findAll(pageable);
        Page<CustomerListResponse> customerListResponses = tempCustomer.map(new Function<Customer, CustomerListResponse>() {
            @Override
            public CustomerListResponse apply(Customer customer) {
                CustomerListResponse customerListResponse = new CustomerListResponse();
                customerListResponse = modelMapperService.forResponse().map(customer,CustomerListResponse.class);
                return customerListResponse;
            }
        });
        return customerListResponses;
    }


    private Pageable createPageable(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        return pageable;
    }
    private Pageable createPageable(int page,int size,String field){
        Pageable pageable = PageRequest.of(page,size, Sort.by(field));
        return pageable;
    }

    private Pageable createPageable(int page,int size,String field,String order){
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(order), field));
        return pageable;
    }

    @Override
    public List<CustomerListResponse> getAll() {
        List<Customer> customers=this.customerRepository.findAll();
        List<CustomerListResponse> customersDto= customers.stream()
                .map(customer -> modelMapperService.forResponse().map(customer,CustomerListResponse.class))
                .collect(Collectors.toList());
        return customersDto;
    }

    @Override
    public void updateCustomer(CustomerListResponse customerListResponse) {
        customerRepository.save(modelMapperService.forRequest().map(customerListResponse,Customer.class));
    }

    @Override
    public void deleteCustomer(String customerId) {
        this.customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerListResponse getCustomerById(String customerId) {
        CustomerListResponse customerListResponse = modelMapperService.forResponse()
                .map(this.customerRepository.getReferenceById(customerId), CustomerListResponse.class);
        return customerListResponse;
    }

    @Override
    public void addCustomer(CustomerListResponse customerListResponse) {
        this.customerRepository.save(modelMapperService.forRequest().map(customerListResponse, Customer.class));

    }
}
