package com.my.customer.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "customer")
@Table(name = "jpacustomer")
public class Customer {
	@Id
	@Column(name = "id", length = 10)
	private String id;
	
	@Column(nullable = false)
	private String pwd;
	
	@Column(name="c_name")
	private String name;
	
	public Customer() {
		super();
	}	
	public Customer(String id, String pwd, String name) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
