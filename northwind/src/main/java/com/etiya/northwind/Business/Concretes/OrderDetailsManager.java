package com.etiya.northwind.Business.Concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.etiya.northwind.Business.requests.OrderDetail.CreateOrderDetailRequest;
import com.etiya.northwind.Entities.Concretes.Order;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	@Override
	public void updateOrderDetail(OrderDetailsListResponse orderDetailsListResponse) {

		orderDetailsRepository.save(modelMapperService.forRequest().map(orderDetailsListResponse,OrderDetails.class));

	}

	@Override
	public void deleteOrderDetail(int orderId) {
		this.orderDetailsRepository.deleteById(orderId);

	}

	@Override
	public OrderDetailsListResponse getOrderDetailById(int orderId) {
		OrderDetailsListResponse orderDetailsListResponse = modelMapperService.forResponse()
				.map(this.orderDetailsRepository.getReferenceById(orderId), OrderDetailsListResponse.class);
		return orderDetailsListResponse;
	}

	@Override
	public void addOrderDetail(CreateOrderDetailRequest createOrderDetailRequest) {
		this.orderDetailsRepository.save(modelMapperService.forRequest().map(createOrderDetailRequest, OrderDetails.class));
	}

	@Override
	public Page<OrderDetailsListResponse> getAllByPage(int page, int size) {
		Pageable pageable = PageRequest.of(page,size);
		var tempOrder = orderDetailsRepository.findAll(pageable);
		Page<OrderDetailsListResponse> orderDetailsListResponses = tempOrder.map(new Function<OrderDetails, OrderDetailsListResponse>() {
			@Override
			public OrderDetailsListResponse apply(OrderDetails orderDetails) {
				OrderDetailsListResponse  orderDetailsListResponse = modelMapperService.forResponse().map(orderDetails,OrderDetailsListResponse.class);
				return orderDetailsListResponse;
			}
		});
		return orderDetailsListResponses;
	}

	@Override
	public Page<OrderDetailsListResponse> getAllByPageWithField(int page, int size, String field) {
		Pageable pageable = PageRequest.of(page,size,Sort.by(field));
		var tempOrder = orderDetailsRepository.findAll(pageable);
		Page<OrderDetailsListResponse> orderDetailsListResponses = tempOrder.map(new Function<OrderDetails, OrderDetailsListResponse>() {
			@Override
			public OrderDetailsListResponse apply(OrderDetails orderDetails) {
				OrderDetailsListResponse  orderDetailsListResponse = modelMapperService.forResponse().map(orderDetails,OrderDetailsListResponse.class);
				return orderDetailsListResponse;
			}
		});
		return orderDetailsListResponses;
	}

	@Override
	public Page<OrderDetailsListResponse> getAllByPageWithOrder(int page, int size, String field, String order) {
		Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(order),field));
		var tempOrder = orderDetailsRepository.findAll(pageable);
		Page<OrderDetailsListResponse> orderDetailsListResponses = tempOrder.map(new Function<OrderDetails, OrderDetailsListResponse>() {
			@Override
			public OrderDetailsListResponse apply(OrderDetails orderDetails) {
				OrderDetailsListResponse  orderDetailsListResponse = modelMapperService.forResponse().map(orderDetails,OrderDetailsListResponse.class);
				return orderDetailsListResponse;
			}
		});
		return orderDetailsListResponses;
	}
}
