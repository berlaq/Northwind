package com.etiya.northwind.Business.Abstracts;

import java.util.List;

import com.etiya.northwind.Business.Responses.OrderDetails.OrderDetailsListResponse;
import com.etiya.northwind.Business.Responses.Orders.OrderListResponse;
import com.etiya.northwind.Business.requests.OrderDetail.CreateOrderDetailRequest;
import org.springframework.data.domain.Page;

public interface OrderDetailService {
	List<OrderDetailsListResponse> getAll();

	void updateOrderDetail(OrderDetailsListResponse orderDetailsListResponse);
	void deleteOrderDetail(int orderId,int productId);
	OrderDetailsListResponse getOrderDetailById(int orderId,int productId);
	void addOrderDetail(CreateOrderDetailRequest createOrderDetailRequest);

	Page<OrderDetailsListResponse> getAllByPage(int page, int size);
	Page<OrderDetailsListResponse>  getAllByPageWithField(int page,int size,String field);
	Page<OrderDetailsListResponse>  getAllByPageWithOrder(int page,int size,String field,String order);

}
