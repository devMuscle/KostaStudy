package com.my.customer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;

import com.my.customer.dao.CustomerDAOInterface;
import com.my.customer.vo.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;

public class CustomerManager {
	public static void main(String[] args) {
		//CustomerDAOList dao;
		//dao = new CustomerDAOList(); 
		
		CustomerDAOInterface dao;
		//dao = new CustomerDAOInterface(); //인터페이스 객체생성불가
		//dao = new CustomerDAOList(); 
		
		//dao.properties파일구조 키=클래스이름(customer=com.my.customer.dao.CustomerDAOOracle)
		//1.dao.properties파일읽기 
		Properties env = new Properties();
		try {
			env.load(new FileInputStream("dao.properties"));
			//2.customer키에 해당하는 값얻기 com.my.customer.dao.CustomerDAOOracle
			String className = env.getProperty("customer");
			
			//3.값을 클래스이름이라 생각하고 런타임다이나믹 클래스로드 Class.forName( );
			Class clazz = Class.forName(className);
			
			//4.로드된 클래스타입의 객체생성  newInstance()
			//5.dao에 객체 대입
			dao = (CustomerDAOInterface)clazz.newInstance();
		
			java.util.Scanner sc;
			sc = new java.util.Scanner(System.in);
			String readValue;
			while(true) {
				System.out.println("작업구분: 1-추가, 2-저장소, 3-전체조회, 4-아이디로 검색, 5-이름으로 검색, 6-수정, 7-삭제, 9-종료");
				System.out.print("작업을 선택하세요:");
				readValue = sc.nextLine();		
				switch(readValue) {
				case "1":
					System.out.println(">>고객 추가<<");
					System.out.print("아이디:");
					String id = sc.nextLine();
					System.out.print("비밀번호:");
					String pwd = sc.nextLine();
					System.out.print("이름:");
					String name = sc.nextLine();
					System.out.print("주소:");
					String address = sc.nextLine();
				
					Customer c1 = new Customer(id, pwd, name, address);
					try {
						dao.add(c1);
					}catch(AddException e) {
						System.out.println("추가실패:" + e.getMessage());
					}
					break;
				case "2":
					System.out.println(">>저장소 정보<<");
					dao.printInfo();
					break;
				case "3":
					System.out.println(">>고객 전체조회<<");
					try {
						List<Customer>all = dao.findAll();
						for(Customer c: all) {
							c.printInfo();
						}
					}catch(FindException e) {
						System.out.println("고객 전체조회:" + e.getMessage());
					}
					break;
				case "4":
					System.out.println(">>아이디로 검색<<");
					System.out.print("아이디:");
					id = sc.nextLine();
					try {
						Customer c = dao.findById(id);
						c.printInfo();
					}catch(FindException e) {
						System.out.println("고객조회 실패:" + e.getMessage());
					}
					break;
				case "5":
					System.out.println(">>이름으로 검색<<");
					System.out.print("단어:");
					String word = sc.nextLine();
					try {
						List<Customer> cArr = dao.findByName(word);
						for(Customer c2: cArr) {
							c2.printInfo();
						}
					}catch(FindException e) {
						System.out.println(e.getMessage());
					}
					
					break;
				case "6":
					System.out.println(">>고객 수정<<");
					System.out.print("아이디:");
					id = sc.nextLine();
					//조회
					try {
						Customer c6 = dao.findById(id);
					//if(c6 == null) {
					//	System.out.println(id+"고객은 존재하지 않습니다");
					//}else {
						c6.printInfo();
						System.out.println("변경안하려면 enter를 입력하고, 변경하려면 값을 입력하시오");
						System.out.print("비밀번호[" + c6.getPwd() +"]:");
						String c6Pwd = sc.nextLine();
						//System.out.println("입력된 비번값:" + c6Pwd);
						System.out.print("이름[" + c6.getName() +"]:");
						String c6Name = sc.nextLine();
						System.out.print("주소[" + c6.getAddress() +"]:");
						String c6Address = sc.nextLine();	
						Customer c6Modify = new Customer(id, c6Pwd, c6Name, c6Address);
						dao.modify(c6Modify);
					}catch(FindException e) {
						System.out.println("고객조회실패:" + e.getMessage());
					}catch(ModifyException e) {
						System.out.println("고객수정실패:" + e.getMessage());
					}
					break;
				case "7":
					System.out.println(">>고객 삭제<<");
					System.out.print("아이디:");
					id = sc.nextLine();
					dao.remove(id);
					break;
				case "9":
					System.exit(0);
					break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
