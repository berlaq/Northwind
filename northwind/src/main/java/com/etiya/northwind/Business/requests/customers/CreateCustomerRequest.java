package com.etiya.northwind.Business.requests.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {

    @NotNull
    @NotBlank
    @Size(min = 5,max = 5)
    private String customerId;

    @NotNull
    private String companyName;

    @NotNull
    private String contactName;
}
