package com.my.customer.vo;


/**
 * 고객객체용 클래스이다
 * 고객정보(아이디, 비밀번호, 이름, 주소)
 * @author HongJiPyo
 *
 */
public class Customer extends Person {
	private String id;
	private String pwd;
	private static final long serialVersionUID = 1L;
//	private String name;
//	private String address;
	
	public Customer(){}
	
	/**
	 * 고객정보를 초기화한다
	 * @param id 아이디
	 * @param pwd 비밀번호
	 * @param name 이름
	 * @param address 주소
	 */
	public Customer(String id, String pwd, String name, String address){
		super(name, address);
		this.id = id;
		this.pwd = pwd;
	}
	
	/**
	 * 고객정보를 출력한다
	 * 출력예로 아이디:1 , 비밀번호:2, 이름:3, 주소:4 이다 
	 */
	public void printInfo() {
		System.out.print("아이디:" + id + ", 비밀번호:" + pwd);
		super.printInfo();
	}
	
	/**
	 * 현재고객의 아이디와 매개변수고객개체의 아이디가 같으면 true반환
	 * 그외의 경우는 false반환한다
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Customer) {
			Customer c = (Customer)obj;
			if(this.id.equals(c.id)) {
				return true;
			}
		}
		return false;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	@Override
	public String toString() {
		return (this.id + ":" + this.pwd + ":" + this.name + ":" + this.address);
	}
}
