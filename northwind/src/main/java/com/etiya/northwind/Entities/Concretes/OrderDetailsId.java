package com.etiya.northwind.Entities.Concretes;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class OrderDetailsId implements Serializable{

	private int orderId;
	private int productId;
	
	public OrderDetailsId(int orderId, int productId) {
		super();
		this.orderId = orderId;
		this.productId = productId;
	}

	public OrderDetailsId() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailsId other = (OrderDetailsId) obj;
		return orderId == other.orderId && productId == other.productId;
	}

    @Override
	public int hashCode() {
		return Objects.hash(orderId, productId);
	}
    
    
}
