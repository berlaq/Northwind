package com.etiya.northwind.DataAccess.Abstracts;

import com.etiya.northwind.Entities.Concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
