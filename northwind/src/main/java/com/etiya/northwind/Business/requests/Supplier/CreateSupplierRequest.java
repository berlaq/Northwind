package com.etiya.northwind.Business.requests.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSupplierRequest {
    @NotNull
    private String companyName;
}
