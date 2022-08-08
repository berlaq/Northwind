package com.etiya.northwind.Entities.Concretes;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class OrderDetailsId implements Serializable{


	private Order order;
	private Product product;
	
	public OrderDetailsId(Order order, Product product) {
		super();
		this.order = order;
		this.product = product;
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
		return order == other.order && product == other.product;
	}

    @Override
	public int hashCode() {
		return Objects.hash(order, order);
	}
    
    
}
