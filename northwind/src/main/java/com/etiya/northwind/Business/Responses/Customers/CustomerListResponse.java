package com.etiya.northwind.Business.Responses.Customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListResponse {

    private String customerId;
    private String companyName;
    private String contactName;
}
