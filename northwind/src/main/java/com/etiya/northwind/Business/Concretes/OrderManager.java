package com.etiya.northwind.Business.Concretes;

import com.etiya.northwind.Business.Responses.Orders.OrderListResponse;
import com.etiya.northwind.DataAccess.Abstracts.OrderDetailsRepository;
import com.etiya.northwind.Entities.Concretes.Order;
import com.etiya.northwind.DataAccess.Abstracts.OrderRepository;
import com.etiya.northwind.Business.Abstracts.OrderService;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderManager implements OrderService {
    private OrderRepository orderRepository;
    private ModelMapperService modelMapperService;
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderManager(OrderRepository orderRepository, ModelMapperService modelMapperService, OrderDetailsRepository orderDetailsRepository) {
        this.orderRepository = orderRepository;
        this.modelMapperService = modelMapperService;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public List<OrderListResponse> getAll() {
        List<Order> orders=this.orderRepository.findAll();
        List<OrderListResponse> orderListResponses=orders.stream()
                .map(order -> modelMapperService.forResponse().map(order, OrderListResponse.class))
                .collect(Collectors.toList());
        return orderListResponses;
    }


    @Override
    public void updateOrder(OrderListResponse orderListResponse) {
        var temp = modelMapperService.forResponse().map(orderListResponse, Order.class);
        temp.getOrderDetails().setProductId(temp.getProduct().getProductId());
        orderRepository.save(temp);
    }

    @Override
    public void deleteOrder(int orderId) {
        this.orderRepository.deleteById(orderId);
    }

    @Override
    public OrderListResponse getOrderById(int orderId) {
        OrderListResponse orderListResponse = modelMapperService.forResponse()
                .map(this.orderRepository.getReferenceById(orderId), OrderListResponse.class);
        return orderListResponse;
    }

    @Override
    public void addOrder(OrderListResponse orderListResponse) {
        var temp = modelMapperService.forResponse().map(orderListResponse, Order.class);
//        temp.getOrderDetails().setProductId(temp.getProduct().getProductId());
//        temp.getOrderDetails().setOrderId(temp.getOrderId());
        this.orderRepository.saveAndFlush(temp);
    }

    @Override
    public Page<OrderListResponse> getAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        var tempOrder = orderRepository.findAll(pageable);
        Page<OrderListResponse> orderListResponses = tempOrder.map(new Function<Order, OrderListResponse>() {
            @Override
            public OrderListResponse apply(Order order) {
                OrderListResponse  orderListResponse = modelMapperService.forResponse().map(order,OrderListResponse.class);
                return orderListResponse;
            }
        });
        return orderListResponses;
    }

    @Override
    public Page<OrderListResponse> getAllByPageWithField(int page, int size, String field) {
        Pageable pageable = PageRequest.of(page,size,Sort.by(field));;
        var tempOrder = orderRepository.findAll(pageable);
        Page<OrderListResponse> orderListResponses = tempOrder.map(new Function<Order, OrderListResponse>() {
            @Override
            public OrderListResponse apply(Order order) {
                OrderListResponse  orderListResponse = modelMapperService.forResponse().map(order,OrderListResponse.class);
                return orderListResponse;
            }
        });
        return orderListResponses;
    }

    @Override
    public Page<OrderListResponse> getAllByPageWithOrder(int page, int size, String field, String order) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(order),field));
        var tempOrder = orderRepository.findAll(pageable);
        Page<OrderListResponse> orderListResponses = tempOrder.map(new Function<Order, OrderListResponse>() {
            @Override
            public OrderListResponse apply(Order order) {
                OrderListResponse  orderListResponse = modelMapperService.forResponse().map(order,OrderListResponse.class);
                return orderListResponse;
            }
        });
        return orderListResponses;
    }

}
