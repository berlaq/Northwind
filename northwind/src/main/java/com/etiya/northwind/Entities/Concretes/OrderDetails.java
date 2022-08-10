package com.etiya.northwind.Entities.Concretes;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(OrderDetailsId.class)
public class OrderDetails implements Serializable {

	@Id
	@Column(name = "order_id")
	private int orderId;

	@Id
	@Column(name = "product_id")
	private int productId;
	
	@Column(name="unit_price")
	private double unitPrice;

	@ManyToOne
	@JoinColumn(name = "product_id",insertable = false,updatable = false)
	private Product product;

	@ManyToOne()
	@JoinColumn(name = "order_id",insertable = false,updatable = false)
	private Order order;

}
