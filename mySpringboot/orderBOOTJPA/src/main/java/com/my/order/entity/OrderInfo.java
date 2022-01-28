package com.my.order.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.my.customer.entity.Customer;


@Entity(name="order_info")
@Table(name="jpaorder_info")
@SequenceGenerator(name = "jpaorder_seq_generator",
        sequenceName = "ORDER_SEQ",
        initialValue = 1,
        allocationSize = 1
		)
public class OrderInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	                 generator = "jpaorder_seq_generator"
	                )
	@Column(name="order_no")
	private	int ordeNo; //주문번호 값은 일련번호(SEQUENCE명 : jpaorder_seq)
	
	//private String order_id; //주문자ID
	@ManyToOne
	@JoinColumn(name="order_id")
	private Customer orderCustomer;

	@CreationTimestamp
	@Column(name="order_dt")
	private Date orderDt;
	

	@OneToMany(cascade = CascadeType.ALL
			,  fetch = FetchType.LAZY
		//	,  mappedBy = "orderInfo"
			)
	@JoinColumn(name = "order_no")
	private List<OrderLine> lines;


	public int getOrdeNo() {
		return ordeNo;
	}


	public void setOrdeNo(int ordeNo) {
		this.ordeNo = ordeNo;
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
	public int hashCode() {
		return Objects.hash(ordeNo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderInfo other = (OrderInfo) obj;
		return ordeNo == other.ordeNo;
	}


	
	
}
