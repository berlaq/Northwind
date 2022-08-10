package com.etiya.northwind.Business.requests.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    @NotBlank
    @NotNull
    @Size(min = 5,max = 5)
    private String customerId;

    @NotNull
    private int employeeId;
}
