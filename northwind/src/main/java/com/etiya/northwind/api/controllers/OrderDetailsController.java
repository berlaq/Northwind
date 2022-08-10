package com.etiya.northwind.api.controllers;

import java.util.List;

import com.etiya.northwind.Business.Responses.Suppliers.SupplierListResponse;
import com.etiya.northwind.Business.requests.OrderDetail.CreateOrderDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.etiya.northwind.Business.Abstracts.OrderDetailService;
import com.etiya.northwind.Business.Responses.OrderDetails.OrderDetailsListResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailsController {
	private OrderDetailService orderDetailService;
	
	@Autowired
	public OrderDetailsController (OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}
	@PutMapping("/update")
	public ResponseEntity<String> updateOrderDetail(@RequestBody @Valid OrderDetailsListResponse orderDetailsListResponse ){
		this.orderDetailService.updateOrderDetail(orderDetailsListResponse);
		return ResponseEntity.ok("OrderDETAİL is updated");
	}
	
	@GetMapping("/getAll")
	public List<OrderDetailsListResponse> getAll(){
		return orderDetailService.getAll();
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteOrderDetail(@RequestParam  int orderId , @RequestParam int productId){
		this.orderDetailService.deleteOrderDetail(orderId,productId);
		return ResponseEntity.ok("OrderDETAİL is deleted");
	}

	@GetMapping("/getOrderDetailById")
	public ResponseEntity<OrderDetailsListResponse> getOrderDetail(@RequestParam  int orderId,@RequestParam int productId){
		return ResponseEntity.ok(this.orderDetailService.getOrderDetailById(orderId,productId));
	}

	@PostMapping("/create")
	public ResponseEntity<String > createOrderDetail(@RequestBody @Valid CreateOrderDetailRequest createOrderDetailRequest){
		this.orderDetailService.addOrderDetail(createOrderDetailRequest);
		return  ResponseEntity.ok("OrderDETAİL is added");
	}

	@GetMapping("/getAllByPage")
	public ResponseEntity<Page<OrderDetailsListResponse>> getAllOrderDetail2(
			@RequestParam(defaultValue = "0", name = "page") int page,
			@RequestParam(defaultValue = "5", name = "size") int size
	){
		return ResponseEntity.ok(this.orderDetailService.getAllByPage(page, size));
	}
	@GetMapping("/getAllByPageWithField")
	public ResponseEntity<Page<OrderDetailsListResponse>> getAllOrderDetail2(
			@RequestParam(defaultValue = "0", name = "page") int page,
			@RequestParam(defaultValue = "5", name = "size") int size,
			@RequestParam(name = "Filtrele") String field
	){
		return ResponseEntity.ok(this.orderDetailService.getAllByPageWithField(page, size,field));
	}
	@GetMapping("/getAllByPageWithOrder")
	public ResponseEntity<Page<OrderDetailsListResponse>> getAllOrderDetail2(
			@RequestParam(defaultValue = "0", name = "page") int page,
			@RequestParam(defaultValue = "5", name = "size") int size,
			@RequestParam(name = "Filtrele") String field,
			@RequestParam(name = "Sırala") String order
	){
		return ResponseEntity.ok(this.orderDetailService.getAllByPageWithOrder(page, size,field,order));
	}



}
