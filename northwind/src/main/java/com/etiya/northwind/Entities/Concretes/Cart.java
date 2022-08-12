package com.etiya.northwind.Entities.Concretes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.Name;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="carts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_price")
    private double unitPrice;
}
