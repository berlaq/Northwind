package com.etiya.northwind.Business.Concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
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
	private ModelMapperService modelMapperService;

	@Autowired
	public OrderDetailsManager(OrderDetailsRepository orderDetailsRepository, ModelMapperService modelMapperService) {
		this.orderDetailsRepository = orderDetailsRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<OrderDetailsListResponse> getAll() {
		List<OrderDetails> orderDetails = orderDetailsRepository.findAll();
		List<OrderDetailsListResponse> orderDetailListResponses = orderDetails.stream()
				.map(orderDetail -> modelMapperService.forResponse().map(orderDetail,OrderDetailsListResponse.class))
				.collect(Collectors.toList());
		return orderDetailListResponses;
	}

}
