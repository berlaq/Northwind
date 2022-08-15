package com.etiya.northwind.businessTests;

import com.etiya.northwind.Business.Abstracts.OrderDetailService;
import com.etiya.northwind.Business.Concretes.OrderDetailsManager;
import com.etiya.northwind.Business.Responses.OrderDetails.OrderDetailsListResponse;
import com.etiya.northwind.Business.requests.OrderDetail.CreateOrderDetailRequest;
import com.etiya.northwind.DataAccess.Abstracts.OrderDetailsRepository;
import com.etiya.northwind.Entities.Concretes.OrderDetails;
import com.etiya.northwind.core.mapping.ModelMapperManager;
import com.etiya.northwind.core.mapping.ModelMapperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

public class OrderDetailManagerTest {
    private OrderDetailService orderDetailService;
    private ModelMapper modelMapper;
    private ModelMapperService modelMapperService;
    private OrderDetailsRepository orderDetailsRepository;


    @BeforeEach
    public void setup(){
        modelMapper = new ModelMapper();
        modelMapperService = new ModelMapperManager(modelMapper);
        orderDetailsRepository = Mockito.mock(OrderDetailsRepository.class);
        orderDetailService = new OrderDetailsManager(orderDetailsRepository,modelMapperService);
    }

    @Test
    public void add_orderDetail_test(){
        var createOrderDetailRequest = new CreateOrderDetailRequest();
        orderDetailService.addOrderDetail(createOrderDetailRequest);

        Mockito.verify(orderDetailsRepository,Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void update_orderDetail_test(){
        OrderDetailsListResponse orderDetailsListResponse = new OrderDetailsListResponse();
        OrderDetails expected = modelMapperService.forRequest().map(orderDetailsListResponse,OrderDetails.class);

        Mockito.when(orderDetailsRepository.save(Mockito.any())).thenReturn(expected);

        orderDetailService.updateOrderDetail(orderDetailsListResponse);

        Mockito.verify(orderDetailsRepository,Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void delete_orderDetail_test(){
        orderDetailService.deleteOrderDetail(1,1);
        Mockito.verify(orderDetailsRepository,Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    public void getall_orderDetail_test(){
        orderDetailService.getAll();
        Mockito.verify(orderDetailsRepository,Mockito.times(1)).findAll();
    }

}
