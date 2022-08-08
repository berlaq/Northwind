package com.etiya.northwind.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.northwind.Business.Abstracts.OrderDetailService;
import com.etiya.northwind.Business.Responses.OrderDetails.OrderDetailsListResponse;

@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailsController {
	private OrderDetailService orderDetailService;
	
	@Autowired
	public OrderDetailsController (OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}
	
	@GetMapping("/getAll")
	public List<OrderDetailsListResponse> getAll(){
		return orderDetailService.getAll();
	}
	
	
	

}
