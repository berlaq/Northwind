package com.etiya.northwind.Entities.Concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suppliers {
    @Id
    @Column(name="supplier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;

    @Column(name = "company_name")
    private String companyName;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "city_id",insertable = false,updatable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "country_id",insertable = false,updatable = false)
    private Country country;

}
