package com.etiya.northwind.DataAccess.Abstracts;

import com.etiya.northwind.Entities.Concretes.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends JpaRepository<Order,Integer>, PagingAndSortingRepository<Order,Integer> {
}
