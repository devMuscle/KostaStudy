package com.my.order.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.my.product.entity.Product;

@Entity(name = "orderline")
@Table(name = "jpaorder_line")	
public class OrderLine implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn(name="order_no")
	private OrderInfo orderInfo;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "order_prod_no")
	private Product orderProduct;
	
	@Column(name="order_quantity")
	private int orderQuantity;

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public Product getOrderProduct() {
		return orderProduct;
	}

	public void setOrderProduct(Product orderProduct) {
		this.orderProduct = orderProduct;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderInfo, orderProduct);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderLine other = (OrderLine) obj;
		return Objects.equals(orderInfo, other.orderInfo) && Objects.equals(orderProduct, other.orderProduct);
	}

	
	
}
