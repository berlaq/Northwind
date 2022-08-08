package com.etiya.northwind.Business.Concretes;

import com.etiya.northwind.Business.Responses.Orders.OrderListResponse;
import com.etiya.northwind.Entities.Concretes.Order;
import com.etiya.northwind.DataAccess.Abstracts.OrderRepository;
import com.etiya.northwind.Business.Abstracts.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderManager implements OrderService {
    private OrderRepository orderRepository;

    public OrderManager(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderListResponse> getAll() {
        List<Order> orders=this.orderRepository.findAll();
        List<OrderListResponse> orderDTOS=new ArrayList<>();

        for(Order order:orders){
            OrderListResponse orderDTO=new OrderListResponse();
            orderDTO.setOrderId(order.getOrderId());
            orderDTO.setCustomerId(order.getCustomers().getCustomerId());
            orderDTO.setEmployeeId(order.getEmployee().getEmployeeId());
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }
}
