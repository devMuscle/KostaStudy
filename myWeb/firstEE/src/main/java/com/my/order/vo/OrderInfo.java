package com.my.order.vo;

import java.util.Date;
import java.util.List;

import com.my.customer.vo.Customer;

public class OrderInfo {
	private int orderNo;
	private Customer orderCustomer;
	private Date orderDt;
	private List<OrderLine> lines;
	public OrderInfo() {
	}
	public OrderInfo(int orderNo, Customer orderCustomer, Date orderDt, List<OrderLine> lines) {
		super();
		this.orderNo = orderNo;
		this.orderCustomer = orderCustomer;
		this.orderDt = orderDt;
		this.lines = lines;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public Customer getOrderCustomer() {
		return orderCustomer;
	}
	public void setOrderCustomer(Customer orderCustomer) {
		this.orderCustomer = orderCustomer;
	}
	public Date getOrderDt() {
		return orderDt;
	}
	public void setOrderDt(Date orderDt) {
		this.orderDt = orderDt;
	}
	public List<OrderLine> getLines() {
		return lines;
	}
	public void setLines(List<OrderLine> lines) {
		this.lines = lines;
	}
	@Override
	public String toString() {
		return "OrderInfo [orderNo=" + orderNo + ", orderCustomer=" + orderCustomer + ", orderDt=" + orderDt
				+ ", lines=" + lines + "]";
	}
}
