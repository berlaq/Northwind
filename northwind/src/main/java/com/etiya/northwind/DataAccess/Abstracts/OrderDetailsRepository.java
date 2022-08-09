package com.etiya.northwind.DataAccess.Abstracts;

import com.etiya.northwind.Entities.Concretes.OrderDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.northwind.Entities.Concretes.OrderDetails;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>, PagingAndSortingRepository<OrderDetails,Integer> {

}
