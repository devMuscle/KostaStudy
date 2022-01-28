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

@Entity(name="orderline")
@Table(name="jparder_line")
public class OrderLine implements Serializable{
	
//	private int order_no;
	
	@Id
	private int order_no;
	
	@Column
	private int order_quantity;
	
	@Id
	@ManyToOne
	@JoinColumn(name="order_prod_no")
	private Product order_p;

	

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public int getOrder_quantity() {
		return order_quantity;
	}

	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}

	public Product getOrder_p() {
		return order_p;
	}

	public void setOrder_p(Product order_p) {
		this.order_p = order_p;
	}

	@Override
	public int hashCode() {
		return Objects.hash(order_no, order_p);
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
		return Objects.equals(order_no, other.order_no) && Objects.equals(order_p, other.order_p);
	}
	
	
}
