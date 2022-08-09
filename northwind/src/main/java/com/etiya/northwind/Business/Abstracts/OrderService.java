package com.etiya.northwind.Business.Abstracts;

import com.etiya.northwind.Business.Responses.Employees.EmployeeListResponse;
import com.etiya.northwind.Business.Responses.Orders.OrderListResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    List<OrderListResponse> getAll();
    void updateOrder(OrderListResponse orderListResponse);
    void deleteOrder(int orderId);
    OrderListResponse getOrderById(int orderId);
    void addOrder(OrderListResponse orderListResponse);

    Page<OrderListResponse> getAllByPage(int page, int size);
    Page<OrderListResponse>  getAllByPageWithField(int page,int size,String field);
    Page<OrderListResponse>  getAllByPageWithOrder(int page,int size,String field,String order);
}
