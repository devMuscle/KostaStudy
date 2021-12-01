package com.my.customer.dao;

import com.my.customer.vo.Customer;

/**
 * 고객객체를 저장소에 추가, 조회, 수정, 삭제하는 클래스이다
 * @author HongJiPyo
 * @version 1.0
 */
public class CustomerDAO1{
	private Customer[] customers; //고객저장소
	private int count; //저장된 고객수
	public CustomerDAO1(){
		customers = new Customer[5];
	}
	public CustomerDAO1(int size) {
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
	 * 단, ID가 중복된 경우에는 "이미 존재하는 아이디입니다"출력하고 저장안한다
	 * @param customer 추가할 고객객체
	 */
	public void add(Customer customer) {
		if(count >= customers.length) {
			System.out.println("저장소가 꽉 찼습니다");
		}else {
			//ID중복확인
			String id = customer.getId(); //추가할 고객의 아이디
//			int i =0;
//			for(; i<count; i++) {
//				Customer c = customers[i]; //이미저장된 고객
//				if(c.getId().equals(id) ) { //중복
//					System.out.println("이미 존재하는 아이디입니다");
//				}
//			}
//			if(i==count) { //중복되지 않은 경우
//				customers[count] = customer;
//				count++; //Good Code
//				System.out.println("고객추가 성공");
//			}
			for(int i=0; i<count; i++) {
//				Customer c = customers[i]; //이미저장된 고객
//				if(c.getId().equals(id)) { //중복
//					System.out.println("이미 존재하는 아이디입니다");
//					return;
//				}
				if(customers[i].equals(customer)) {
					System.out.println("이미 존재하는 이이디입니다");
					return;
				}
			}
			
			//중복되지 않은 경우
			customers[count] = customer;
			count++; //Good Code
			System.out.println("고객추가 성공");
		}
//		customers[count++] = customer; //Bad Code
	}
	
	public Customer[] findAll(){ //Customer[]all = dao.findAll(); 로 사용한다
		Customer[] all = new Customer[count];
//		for(int i=0; i<count; i++) {
//			all[i] = customers[i];
//		}
		System.arraycopy(customers, 0, all, 0, count);
		return all;
	}
	
	public Customer findById(String id) { //Customer c = dao.findById(id); 로 사용한다
		for(int i=0; i<count; i++) {
//			Customer c = customers[i];
//			String cId = c.id; //저장된 고객ID
//			if(cId.equals(id)) {
//				return c;
			if(customers[i].getId().equals(id)) {
				return customers[i];
			}
		}
		return null;
	}
	public Customer[] findByName(String word) { //Customer[] cArr = dao.findByName(word);로 사용한다
//			int wordCnt = 0; //단어를 포함한 고객수
//			for(int i=0; i<count; i++) {
//				Customer c = customers[i];
//				String cName = c.name; //저장된 고객이름
//				if(cName.contains(word)) { //if(cName.contains(word)==true){
//					wordCnt++;
//				}
//			}
//			
//			Customer []cArr = new Customer[wordCnt];
//			int cArrIndex = 0;
//			for(int i=0; i<count; i++) {
//				Customer c = customers[i];
//				String cName = c.name; //저장된 고객이름
//				if(cName.contains(word)) { //if(cName.contains(word)==true){
//					cArr[cArrIndex] = c;
//					cArrIndex++;
//				}
//			}
			
			Customer []cArr1 = new Customer[count];
			int cArrIndex = 0;
			for(int i=0; i<count; i++) {
				Customer c = customers[i];
				String cName = c.getName();
				if(cName.contains(word)) {
					//cArr1[i] = c; //이부분이 오타. 아래코드로 바꿈
					cArr1[cArrIndex] = c; 
					cArrIndex++;
				}
			}
			//Customer []cArr =  new Customer[cArrIndex];//null값을 반환하거나 참조값을 반환해야하니까 먼저 null로 초기화
			Customer []cArr = null;
			if(cArrIndex > 0) { 
				cArr = new Customer[cArrIndex];
				System.arraycopy(cArr1, 0, cArr, 0, cArrIndex);
			}
			return cArr;
			
		}
	
		public void modify(Customer c) {
			Customer c1 = findById(c.getId()); //
			boolean flag = false;
			if(c1 != null) {
				if(!"".equals(c.getPwd())) {
					c1.setPwd(c.getPwd()) ;
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
			
			if(flag) { //if(flag==true){
				System.out.println("고객정보가 변경되었습니다");
			}else {
				System.out.println("고객정보가 변경되지않았습니다");
			}
		}
		
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


