package com.my.product.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table
public class Product {
	@Id
	@Column 
	private String prod_no;               
	
	@Column
	private String prod_name;
	
	@Column
	@ColumnDefault(value="1")
	private int prod_price;

	public Product() {
	}
	
	public Product(String prod_no, String prod_name, int prod_price){
		this.prod_no = prod_no;
		this.prod_name = prod_name;
		this.prod_price = prod_price;
	}

	public String getProdNo() {
		return prod_no;
	}

	public void setProdNo(String prod_no) {
		this.prod_no = prod_no;
	}

	public String getProdName() {
		return prod_name;
	}

	public void setProdName(String prod_name) {
		this.prod_name = prod_name;
	}

	public int getProdPrice() {
		return prod_price;
	}

	public void setProdPrice(int prod_price) {
		this.prod_price = prod_price;
	}

	
}
