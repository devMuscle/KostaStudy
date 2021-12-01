package com.my.customer.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.my.customer.vo.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;

public class CustomerDAOFile implements CustomerDAOInterface {
	private String fileName = "customers.txt";
	/**
	 * 저장소에 고객들을 일괄 추가하기
	 * @param list 고객들
	 */
	private void add(List<Customer> list) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName);
			for(Customer c1: list) {
				String info = c1.getId() + ":" + c1.getPwd() + ":" + c1.getName() + ":" + c1.getAddress();
				fw.write(info);
				fw.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fw != null) {
				try {
					fw.close();
				}catch(Exception e) {
					
				}
			}
		}
	}
	@Override
	public void add(Customer customer) throws AddException {
		//1. 아이디중복처리
		//   findById()의 인자로 customer고객객체의 id값을 전달
		//      FindException예외가 발생한 경우-아이디가 없는 경우
		//       2. 문자단위로 파일목적지 연결 ex)new FileWriter(fileName, true);
		//       3. customer고객객체를 한줄의 정보(아이디값:비번값:이름값:주소값)문자열로 만듦
		//       4. 한줄문자열을 파일목적지에 추가
		//       5. 파일목적지 닫기
		//      FindException예외가 발생안한 경우-아이디가 있는 경우
		//       AddException예외발생
		
		try {
			findById(customer.getId());
			throw new AddException("이미 존재하는 아이디입니다");
		} catch (FindException e) {
//			e.printStackTrace();
			FileWriter fw = null;
			try {
				fw = new FileWriter(fileName, true); 
				String info = customer.getId() + ":" + customer.getPwd() + ":" + customer.getName()+ ":" + customer.getAddress();
				fw.write(info);fw.write("\n"); 
				
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if(fw != null) {
					try {
						fw.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}			 
		}
	}

	@Override
	public List<Customer> findAll() throws FindException {
		//1. 바이트단위로 파일저장소 연결
		
		//2. 파일내용을 반복하며 한줄씩 읽기
		//3. 한줄내용을 고객1명이라 가정하고 한줄의 정보(아이디값:비번값:이름값:주소값)를 파싱해서
		//   고객객체로 생성
		//4. List에 고객객체추가
		
		//5. 파일저장소 연결 닫기
		//6. List반환
		List<Customer> list = new ArrayList<>();
		FileInputStream fis = null;
		Scanner sc = null;
		try {
			fis = new FileInputStream(fileName);
			sc = new Scanner(fis);
			
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] arr = line.split(":", 4);			
				String id = arr[0];
				String pwd = arr[1];
				String name = arr[2];
				String addr = arr[3];
				Customer c = new Customer(id, pwd, name, addr);
				list.add(c);
			}	
			return list;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			//String msg = e.getMessage();
			String msg = "고객저장소 파일이 없습니다";
			throw new FindException(msg);
		} finally {
			if(sc != null) {
				sc.close();
			}	
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//return list;
		
	}

	@Override
	public Customer findById(String id) throws FindException {
		//1. 바이트단위로 파일저장소 연결
		
		//2. 파일내용을 반복하며 한줄씩 읽기
		//3. 한줄내용을 고객1명이라 가정하고 한줄의 정보(아이디값:비번값:이름값:주소값)를 파싱해서
		//   id가 같은 고객인경우 고객객체로 생성
		//4. 저장소 닫기
		//5. 고객객체 반환		
		FileInputStream fis = null;
		Scanner sc = null;		
		try {
			fis = new FileInputStream(fileName);
			sc = new Scanner(fis);
			
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String []arr = line.split(":", 4);
				String id1 = arr[0];
				if(id.equals(id1)) {
					String pwd = arr[1];  String name = arr[2];	String addr = arr[3];
					Customer c = new Customer(id, pwd, name, addr);
					return c;
				}
			}
			throw new FindException("아이디에 해당하는 고객이 없습니다");
		} catch (FileNotFoundException e) {
			String msg = "고객저장소 파일이 없습니다";
			throw new FindException(msg);
		} finally {
			if(sc != null) {
				sc.close();
			}	
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public List<Customer> findByName(String word) throws FindException {
		//1. 바이트단위로 파일저장소 연결
		
		//2. 파일내용을 반복하며 한줄씩 읽기
		//3. 한줄내용을 고객1명이라 가정하고 한줄의 정보(아이디값:비번값:이름값:주소값)를 파싱해서
		//  word를 포함한 이름을 같은 고객인경우 고객객체로 생성
		//4. 리스트에 고객객체추가
		
		//5. 저장소 닫기
		//6. 리스트 반환
		
		FileInputStream fis = null;
		Scanner sc = null;
		List<Customer> list = new ArrayList<>();
		try {
			fis = new FileInputStream(fileName);
			sc = new Scanner(fis);
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] arr = line.split(":");
				String name = arr[2];
				if(name.contains(word)) {
					Customer c = new Customer(arr[0], arr[1], name, arr[3]);
					list.add(c);
				}
			}
			if(list.size() == 0) {
				throw new FindException("단어를 포함한 이름의 고객이 없습니다");
			}
			return list;
		} catch (FileNotFoundException e) {
			String msg = "고객저장소 파일이 없습니다";
			throw new FindException(msg);
		} finally {
			if(sc != null) {
				sc.close();
			}	
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void modify(Customer c) throws ModifyException {
		try {
			List<Customer> list = findAll(); //저장소의 모든 고객 얻기
			int index = list.indexOf(c); //해당고객의 위치찾기
			if(index != -1) { //해당고객이 존재하면
				Customer c1 = list.get(index);//저장소의 고객객체얻기
				boolean flag = false;
				if(!"".equals(c.getPwd())) { //저장소의 고객객체내용 수정
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
				if(!flag) {
					throw new ModifyException("고객정보가 변경되지않았습니다");
				}
				
				File file = new File(fileName);
				if(file.delete()) { //파일 삭제하기
					add(list);
				}else {
					throw new ModifyException("파일이 제거되지 않았습니다");
				}
			}else { 
				throw new ModifyException("아이디에 해당하는 고객이 없습니다");
			}
		} catch (FindException e) {
			throw new ModifyException(e.getMessage());
		}
	}

	@Override
	public void remove(String id) {
		try {
			List<Customer> list = findAll();
			Customer c = new Customer();
			c.setId(id);
			if(list.remove(c)) { //리스트에서 고객 삭제
				File file = new File(fileName); 
				if(file.delete()) { //파일제거
					add(list);
				}else {
					System.out.println("파일이 제거되지 않았습니다");
				}
			}else {
				System.out.println("아이디에 해당하는 고객이 없습니다");
			}
		} catch (FindException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
//		try {
//			List<Customer> list = findAll();
//			Customer c = new Customer();
//			c.setId(id);
//			if(list.remove(c)) { //리스트에서 고객 삭제
//				File file = new File(fileName); 
//				if(file.delete()) { //파일제거
//					//FileWriter fw = new FileWriter(fileName); 
//					for(Customer c1: list) {
//						add(c1); //나머지 고객 추가
//					}
//					
//				}else {
//					System.out.println("파일이 제거되지 않았습니다");
//				}
//			}else {
//				System.out.println("아이디에 해당하는 고객이 없습니다");
//			}
//		} catch (FindException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		} catch (AddException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
	}

	@Override
	public void printInfo() {

	}
	
}
