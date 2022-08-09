package com.etiya.northwind.api.controllers;

import com.etiya.northwind.Business.Responses.Employees.EmployeeListResponse;
import com.etiya.northwind.Business.Responses.Orders.OrderListResponse;
import com.etiya.northwind.Business.Abstracts.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getAll")
    public List<OrderListResponse> getAll(){
        return this.orderService.getAll();
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateEmployee(@RequestBody OrderListResponse orderListResponse ){
        this.orderService.updateOrder(orderListResponse);
        return ResponseEntity.ok("Order is updated");
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable int orderId ){
        this.orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order is deleted");
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderListResponse> getOrder(@PathVariable int orderId){
        return ResponseEntity.ok(this.orderService.getOrderById(orderId));
    }

    @PostMapping("/create")
    public ResponseEntity<String > createOrder(@RequestBody OrderListResponse orderListResponse){
        this.orderService.addOrder(orderListResponse);
        return  ResponseEntity.ok("Order is added");
    }

    @GetMapping("/getAllByPage")
    public ResponseEntity<Page<OrderListResponse>> getAllOrder2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size
    ){
        return ResponseEntity.ok(this.orderService.getAllByPage(page, size));
    }
    @GetMapping("/getAllByPageWithField")
    public ResponseEntity<Page<OrderListResponse>> getAllOrder2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(name = "Filtrele") String field
    ){
        return ResponseEntity.ok(this.orderService.getAllByPageWithField(page, size,field));
    }
    @GetMapping("/getAllByPageWithOrder")
    public ResponseEntity<Page<OrderListResponse>> getAllOrder2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(name = "Filtrele") String field,
            @RequestParam(name = "Sırala") String order
    ){
        return ResponseEntity.ok(this.orderService.getAllByPageWithOrder(page, size,field,order));
    }
}
