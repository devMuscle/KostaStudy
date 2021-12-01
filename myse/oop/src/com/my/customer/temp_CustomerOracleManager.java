package com.my.customer;

import java.util.List;
import java.util.Scanner;
import com.my.customer.dao.CustomerDAOOracle;
import com.my.customer.vo.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;

public class temp_CustomerOracleManager {
	public static void main(String[] args) {
		CustomerDAOOracle daoO = new CustomerDAOOracle();
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("아이디:");
			String id = sc.nextLine();
			try {
				Customer c = daoO.findById(id);
				c.printInfo();
			}catch(FindException e) {
				System.out.println("고객조회 실패:" + e.getMessage());
			}
				break;
		}
	}
}
	

