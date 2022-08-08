package com.etiya.northwind.Business.Abstracts;

import java.util.List;

import com.etiya.northwind.Business.Responses.OrderDetails.OrderDetailsListResponse;

public interface OrderDetailService {
	List<OrderDetailsListResponse> getAll();

}
