package com.etiya.northwind.Business.Abstracts;

import com.etiya.northwind.Business.Responses.Orders.OrderListResponse;

import java.util.List;

public interface OrderService {
    List<OrderListResponse> getAll();
}
