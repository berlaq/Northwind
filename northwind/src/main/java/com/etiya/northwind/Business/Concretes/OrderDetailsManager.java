package com.etiya.northwind.Business.Concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.northwind.Business.Abstracts.OrderDetailService;
import com.etiya.northwind.Business.Abstracts.OrderService;
import com.etiya.northwind.Business.Responses.OrderDetails.OrderDetailsListResponse;
import com.etiya.northwind.Business.Responses.Orders.OrderListResponse;
import com.etiya.northwind.DataAccess.Abstracts.OrderDetailsRepository;
import com.etiya.northwind.Entities.Concretes.OrderDetails;

@Service
public class OrderDetailsManager implements OrderDetailService{

	private OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	private OrderDetailsManager (OrderDetailsRepository orderDetailsRepository) {
		this.orderDetailsRepository = orderDetailsRepository;
	}
	
	@Override
	public List<OrderDetailsListResponse> getAll() {
		List<OrderDetails> orderDetails = orderDetailsRepository.findAll();
		List<OrderDetailsListResponse> orderDetailListResponses = new ArrayList<>();
		for(OrderDetails orderDetail : orderDetails) {
			OrderDetailsListResponse orderDetailsLR = new OrderDetailsListResponse();
			orderDetailsLR.setOrderId(orderDetail.getOrder().getOrderId());
			orderDetailsLR.setProductId(orderDetail.getProduct().getProductId());
			orderDetailsLR.setUnitPrice(orderDetail.getUnitPrice());
			
			orderDetailListResponses.add(orderDetailsLR);
		}
		return orderDetailListResponses;
	}

}
