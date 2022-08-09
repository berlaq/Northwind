package com.etiya.northwind.DataAccess.Abstracts;

import com.etiya.northwind.Entities.Concretes.OrderDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.northwind.Entities.Concretes.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsId>{

}
