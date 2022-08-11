package com.etiya.northwind.Entities.Concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "city_country")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityCountry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "citycountry_id")
    private int cityCountryId;

    @OneToOne
    private City city;

    @ManyToOne
    private Country country;


}
