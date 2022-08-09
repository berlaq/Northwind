package com.etiya.northwind.DataAccess.Abstracts;

import com.etiya.northwind.Entities.Concretes.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SupplierRepository extends JpaRepository<Suppliers, Integer>, PagingAndSortingRepository<Suppliers,Integer> {
}
