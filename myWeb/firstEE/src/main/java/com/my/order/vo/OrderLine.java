package com.my.order.vo;

import com.my.product.vo.Product;

public class OrderLine {
	private int orderNo;
	private Product orderProduct;
	private int orderQuantity;
	public OrderLine() {
	}
	public OrderLine(int orderNo, Product orderProduct, int orderQuantity) {
		super();
		this.orderNo = orderNo;
		this.orderProduct = orderProduct;
		this.orderQuantity = orderQuantity;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
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
	@Override
	public String toString() {
		return "OrderLine [orderNo=" + orderNo + ", orderProduct=" + orderProduct + ", orderQuantity=" + orderQuantity
				+ "]";
	}
}
