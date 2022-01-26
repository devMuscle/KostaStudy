package com.my.customer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person implements Serializable{
	@Column
	protected String name;
	@Column
	protected String address;
	public Person() {}
	public Person(String name, String address) {
		this.name = name;
		this.address = address;
	}
	public void printInfo() {
		System.out.println(", 이름:" + name + ", 주소:" + address);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
