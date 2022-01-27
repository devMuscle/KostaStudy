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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.my.customer.entity.Customer;

@Entity(name = "order_info")
@Table(name = "jpaorder_info")

@SequenceGenerator(name = "jpaorder_seq_generator", sequenceName = "jpaorder_seq", initialValue = 1, allocationSize = 1)
public class OrderInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaorder_seq_generator")
	private int order_no; // 주문번호 값은 일련번호(SEQUENCE명 : jpaorder_seq)
	
	//private String order_id; //주문자 ID
	@ManyToOne
	@JoinColumn(name="order_id")
	private Customer order_c;
	
	@CreationTimestamp
	private Date order_dt;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order_info")
	private List<OrderLine> lines;
	
	public int getOrder_no() {
		return order_no;
	}
	
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public Customer getOrder_c() {
		return order_c;
	}

	public void setOrder_c(Customer order_c) {
		this.order_c = order_c;
	}

	public Date getOrder_dt() {
		return order_dt;
	}

	public void setOrder_dt(Date order_dt) {
		this.order_dt = order_dt;
	}

	public List<OrderLine> getLines() {
		return lines;
	}

	public void setLines(List<OrderLine> lines) {
		this.lines = lines;
	}

	@Override
	public int hashCode() {
		return Objects.hash(order_no);
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
		return order_no == other.order_no;
	}
	
	
}
