package com.etiya.northwind.Business.Concretes;

import com.etiya.northwind.Business.Responses.Orders.OrderListResponse;
import com.etiya.northwind.Entities.Concretes.Order;
import com.etiya.northwind.DataAccess.Abstracts.OrderRepository;
import com.etiya.northwind.Business.Abstracts.OrderService;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderManager implements OrderService {
    private OrderRepository orderRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public OrderManager(OrderRepository orderRepository, ModelMapperService modelMapperService) {
        this.orderRepository = orderRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<OrderListResponse> getAll() {
        List<Order> orders=this.orderRepository.findAll();
        List<OrderListResponse> orderDTOS=orders.stream()
                .map(order -> modelMapperService.forResponse().map(order, OrderListResponse.class))
                .collect(Collectors.toList());
        for(int i = 0; i< orderDTOS.size(); i++){
            orderDTOS.get(i)
                    .setFullName(orders.get(i).getEmployee().getFirstName()
                            + " " +
                            orders.get(i).getEmployee().getLastName());
        }
        return orderDTOS;
    }
}
