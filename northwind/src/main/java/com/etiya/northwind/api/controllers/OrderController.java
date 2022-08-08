package com.etiya.northwind.api.controllers;

import com.etiya.northwind.Business.Responses.Orders.OrderListResponse;
import com.etiya.northwind.Business.Abstracts.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getAll")
    public List<OrderListResponse> getAll(){
        return this.orderService.getAll();
    }
}
