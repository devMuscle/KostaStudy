package com.my.customer.dao;

import java.util.ArrayList;
import java.util.List;

import com.my.customer.vo.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;

/**
 * 고객객체를 저장소에 추가, 조회, 수정, 삭제하는 클래스이다
 * 각 기능별 예외처리한다
 * @author KOSTA
 * @version 3.0
 */
public class CustomerDAOList implements CustomerDAOInterface{
	private List<Customer> customers; //고객저장소
	//private int count; //저장된 고객수
	public CustomerDAOList(){
		customers = new ArrayList(); //10개의 index가 만들어짐
	}
	public CustomerDAOList(int size) {
		customers = new ArrayList(size); //size개수의 index가 만들어짐
	}
	public void printInfo() {
		System.out.println("저장된 고객수" + customers.size());
	
		for(Customer c:customers) {
			c.printInfo();
		}
	}
	 
	//*  저장소에 고객을 추가한다
	public void add(Customer customer) throws AddException{
		//ID중복확인
		for(Customer c: customers) {
			if(c.equals(customer)) {
				//System.out.println("이미 존재하는 아이디입니다");
				//return;
				throw new AddException("이미 존재하는 아이디입니다");
			}
		}
			//중복되지 않은 경우
		customers.add(customer);	
	}
	
	public List<Customer> findAll(){    //Customer[]all = dao.findAll();로 사용한다
		return customers;
	}
	
	/**
	 * 아이디에 해당 고객을 저장소에서 찾아 반환한다
	 * @param id 아이디
	 * @return 고객객체
	 * @throws FindException 아이디에 해당 고객이 저장소에 없으면 "아이디에 해당하는 고객이 없습니다"메시지를 갖는 예외발생한다
	 */
	public Customer findById(String id) throws FindException{ //Customer c = dao.findById(id);로 사용한다
		Customer c1 = new Customer();
		c1.setId(id);
		for(Customer c: customers) {
			if(c.equals(c1)) {
				return c;
			}
		}
		throw new FindException("아이디에 해당하는 고객이 없습니다");
	}
	
	/**
	 * 단어를 포함한 이름을 갖는 고객들을 반환한다
	 * @param word 단어
	 * @return 고객들
	 * @throws FindException 단어를 포함한 이름을 갖는 고객이 한명도 없으면 "단어를 포함한 이름의 고객은 없습니다"메시지를 갖는 예외가 발생한다
	 */
	public List<Customer> findByName(String word) throws FindException{
		List<Customer> list = new ArrayList<>();
		for(Customer c: customers) {
			if(c.getName().contains(word)) {
				list.add(c);
			}
		}
		if(list.size() > 0) {
			return list;
		} else {
			throw new FindException("단어를 포함한 이름의 고객은 없습니다");
		}
	}
	/**
	 * 아이디에 해당하는 고객정보(비밀번호, 이름, 주소)를 수정한다<br>
	 * 단, 정보가 기존내용과 같을 경우는 변경하지 않는다
	 * @param c 변경할 고객객체
	 * @throws ModifyException c고객객체의 아이디가 저장소에 없으면 예외 발생한다<br>
	 *                         기존내용과 같을 경우는 "고객정보가 변경되지않았습니다"메시지를 갖는 예외가 발생한다
	 */
	public void modify(Customer c) throws ModifyException{
		try {
			Customer c1 = findById(c.getId()); //c1: 저장소의 고객	
			boolean flag = false;
			if(c1 != null) {
				if(!"".equals(c.getPwd())) {
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
				throw new ModifyException("고객정보가 변경되지않았습니다");
			}
		}catch(FindException e) {
			throw new ModifyException(e.getMessage());
		}
	}

	public void remove(String id) {
		Customer c = new Customer();
		c.setId(id);
		customers.remove(c);
	}
	
	public static void main(String[] args) {
		CustomerDAOList dao = new CustomerDAOList();
		try {
			dao.add(new Customer("id1", "p1", "n1", "a1"));
		} catch (AddException e) {
				e.printStackTrace();
			}		
		try {
			dao.add(new Customer("id2", "p2", "n2", "a2"));
		} catch (AddException e) {
				e.printStackTrace();
		}
		try {
			dao.add(new Customer("id3", "p3", "n3", "a3"));
		} catch (AddException e) {
				e.printStackTrace();
		}
		try {
			dao.add(new Customer("id1", "x1", "x1", "x1"));
		} catch (AddException e) {
				e.printStackTrace();
		}
		try {
			dao.add(new Customer("id4", "p4", "n14", "a4"));
		} catch (AddException e) {
			e.printStackTrace();
		}
		
		List<Customer> all = dao.findAll();
		for(Customer c: all) {
			c.printInfo();
		}
		
		System.out.println("--아이디로 검색 테스트--");
		try {
			Customer c = dao.findById("id3");
			c.printInfo();
		} catch (FindException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("---이름으로 검색 테스트---");
		try {
			List<Customer> list1 = dao.findByName("1");
			for(Customer c: list1) {
				c.printInfo();
			}
		} catch (FindException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("--수정 테스트--");
		Customer c = new Customer("id1", "", "nn1", "aa1");
		try {
			dao.modify(c);
		} catch (ModifyException e) {
			e.printStackTrace();
		}
		System.out.println("--수정후 조회 테스트--");
		try {
			dao.findById("id1").printInfo();
		} catch (FindException e) {
			e.printStackTrace();
		}
		
		System.out.println("--삭제 테스트--");
		dao.remove("id1");
		System.out.println("--삭제후 전체조회 테스트--");
		for(Customer c1: dao.findAll()) {
			c1.printInfo();
		}
		
	}

}