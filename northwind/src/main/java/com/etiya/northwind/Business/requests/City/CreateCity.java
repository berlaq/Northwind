package com.etiya.northwind.Business.requests.City;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCity {
    @NotNull
    private String cityName;

    @NotNull
    private String countryName;
}
