package com.my.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity(name="Product")
@Table(name="product")
public class Product {
	@Id
	@Column(name="prod_no")
	private String prodNo;               
	
	@Column(name="prod_name")
	private String prodName;
	
	@Column(name="prod_price")
	@ColumnDefault(value="1")
	private int prodPrice;

	public Product() {
	}
	
	public Product(String prod_no, String prod_name, int prod_price){
		this.prodNo = prod_no;
		this.prodName = prod_name;
		this.prodPrice = prod_price;
	}

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prod_no) {
		this.prodNo = prod_no;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prod_name) {
		this.prodName = prod_name;
	}

	public int getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(int prod_price) {
		this.prodPrice = prod_price;
	}

	
}
