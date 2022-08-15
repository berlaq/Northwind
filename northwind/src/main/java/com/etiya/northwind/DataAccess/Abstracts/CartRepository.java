package com.etiya.northwind.DataAccess.Abstracts;

import com.etiya.northwind.Business.Responses.Cart.CartListResponse;
import com.etiya.northwind.Entities.Concretes.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Transactional
    void deleteAllByCustomer_CustomerId(String customerId);
}
