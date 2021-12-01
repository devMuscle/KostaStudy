package com.my.customer.dao;

import com.my.customer.vo.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;

/**
 * 고객객체를 저장소에 추가, 조회, 수정, 삭제하는 클래스이다
 * 각 기능별 예외처리한다
 * @author KOSTA
 * @version 2.0
 */
public class CustomerDAO2{
	private Customer[] customers; //고객저장소
	private int count; //저장된 고객수
	public CustomerDAO2(){
		customers = new Customer[5];
	}
	public CustomerDAO2(int size) {
		customers = new Customer[size];
	}
	public void printInfo() {
		System.out.println("고객저장소의 크기" + customers.length);
		System.out.println("저장된 고객수" + count);
		
		for(int i=0; i<count; i++) {
			Customer c = customers[i];
			System.out.print("[" + i + "]=");
			c.printInfo();
		}
	}
	/**
	 * 저장소에 고객을 추가한다
	 * @param customer 추가할 고객객체
	 * @throws AddException
	 * 	ID가 중복된 경우에는 "이미 존재하는 아이디입니다"상세메시지를 갖는 예외가 발생한다.<br>
	 * 	저장소가 꽉찬경ㅇ에는 "저장소가 꽉찼습니다"상세메시지를 갖는 예외가 발생한다.
	 */
	public void add(Customer customer) throws AddException{
		//ID중복확인
		try {
			for(int i=0; i<count; i++) {
				if(customers[i].equals(customer)) {
					//System.out.println("이미 존재하는 아이디입니다");
					//return;
					throw new AddException("이미 존재하는 아이디입니다");
				}
			}
			//중복되지 않은 경우
			customers[count] = customer;
			count++; //Good Code		
			//System.out.println("고객추가 성공");	
			//customers[count++] = customer; //Bad Code
		}catch(ArrayIndexOutOfBoundsException e) {
			throw new AddException("저장소가 꽉찼습니다. 현재고객수:" + count);
		}
		
	}
	
	public Customer[] findAll(){    //Customer[]all = dao.findAll();로 사용한다
		//return customers;
		Customer[]all = new Customer[count];
//		for(int i=0; i<count; i++) {
//			all[i] = customers[i];
//		}
		System.arraycopy(customers, 0, all, 0, count);
		return all;
	}
	
	/**
	 * 아이디에 해당 고객을 저장소에서 찾아 반환한다
	 * @param id 아이디
	 * @return 고객객체
	 * @throws FindException 아이디에 해당 고객이 저장소에 없으면 "아이디에 해당하는 고객이 업습니다"메시지를 갖는 예외발생한다.
	 */
	public Customer findById(String id) throws FindException{ //Customer c = dao.findById(id);로 사용한다
		for(int i=0; i<count; i++) {
//			Customer c = customers[i];
//			String cId = c.id; //저장된 고객ID
//			if(cId.equals(id)) {
//				return c;
//			}
//			if(customers[i].getId().equals(id)) {
//				return customers[i];
//			}
			Customer c1 = new Customer();
			c1.setId(id);
			if(customers[i].equals(c1)) {
				return customers[i];
			}
		}
		
		//System.out.println(id+"고객은 존재하지 않습니다");
		//return null;
		throw new FindException("아이디에 해당하는 고객이 없습니다");
	}
	/**
	 * 단어를 포함한 이름을 갖는 고객들을 반환한다
	 * @param word 단어
	 * @return 고객들
	 * @throws FindException 단어를 포함한 이름을 갖는 고객이 한명도 없으면 "단어를 포함한 이름의 고객은 없습니다"메시지를 갖는 예외가 발생한다
	 */
	public Customer[] findByName(String word) throws FindException{
		Customer []cArr1 = new Customer[count];
		int cArrIndex = 0;
		for(int i=0; i<count; i++) {
			Customer c = customers[i];
			String cName = c.getName();
			if(cName.contains(word)) {
				cArr1[cArrIndex] = c; 
				cArrIndex++;
			}
		}
		Customer []cArr = null;
		if(cArrIndex > 0) { 
			cArr = new Customer[cArrIndex];
			System.arraycopy(cArr1, 0, cArr, 0, cArrIndex);
			return cArr;
		}else {
			throw new FindException("단어를 포함한 이름의 고객은 없습니다");
		}
	}

	/**
	 * 아이디에 해당하는 고객정보(비밀번호, 이름, 주소)를 수정한다<br>
	 * 단, 기존내용과 같을 경우는 변경하지 않는다
	 * @param c 변경할 고객객체
	 * @throws ModifyException c고객객체의 아이디가 저장소에 없으면 예외발생한다<br>
	 * 						   기존내용과 같을 경우는 "고객정보가 변경되지않아습니다"메시지를 갖는 예외가 발생한다
	 */
	public void modify(Customer c) throws ModifyException{
		try {
			Customer c1 = findById(c.getId()); //c1: 저장소의 고객
			boolean flag = false;
			if(c1 != null) {
				if(!"".equals(c.getPwd())) {
					//c1.pwd = c.pwd;
					c1.setPwd(c.getPwd());
					flag = true;
				}
				if(!"".equals(c.getName())) {
					c1.setName(c.getName());
					flag = true;
				}
				if(!"".equals(c.getAddress())) {
					c1.setAddress(c.getAddress());
					flag = true;
				}
			}
			if(!flag) {
				throw new ModifyException("고객정보가 변경되지 않았습니다");
			}
		}catch(FindException e) {
			throw new ModifyException(e.getMessage());
		}
//		if(flag) { //if(flag==true){
//			System.out.println("고객정보가 변경되었습니다");
//		}else {
//			System.out.println("고객정보가 변경되지않았습니다");
//		}
	}
//	boolean modify(Customer c){
//		Customer c1 = findById(c.id); //c1: 저장소의 고객
//		if(c1 != null) {
//			boolean flag = false;
//			if(!"".equals(c.pwd)) {
//				c1.pwd = c.pwd;
//				flag = true;
//			}
//			if(!"".equals(c.name)) {
//				c1.name = c.name;
//				flag = true;
//			}
//			if(!"".equals(c.address)) {
//				c1.address = c.address;
//				flag = true;
//			}
//			return flag;
//		}
//		return false;
//	}
	public void remove(String id) {
		int index;
		for(index=0; index<count; index++) {
			Customer c = customers[index];
			if(c.getId().equals(id)) {
				break;
			}
		}
		if(index < count) {
			count--;
			for(int i=index; i<count;i++) {
				customers[i] = customers[i+1];
				
			}
			customers[count] = null;
		}
	}
}