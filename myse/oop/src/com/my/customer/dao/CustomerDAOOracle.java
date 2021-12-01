package com.my.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.my.customer.vo.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.sql.MyConnection;

public class CustomerDAOOracle implements CustomerDAOInterface{
	public void add(Customer customer) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null; //송신
		try {
			con = MyConnection.getConnection();
			String insertSQL = "INSERT INTO customer(id, pwd, name, address) VALUES(?, ?, ?, ?)";
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, customer.getId()); 
			pstmt.setString(2, customer.getPwd());
			pstmt.setString(3, customer.getName()); 
			pstmt.setString(4, customer.getAddress());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(pstmt, con);
		}
	}

	public List<Customer> findAll() throws FindException {
		List<Customer> list = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
			String selectSQL = "SELECT id, pwd, name, address FROM customer";
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectSQL);
				while(rs.next()) {
					String temp_id = rs.getString("id");
					String temp_pwd = rs.getString("pwd");
					String temp_name = rs.getString("name");
					String temp_address = rs.getString("address");
					Customer c = new Customer(temp_id, temp_pwd, temp_name, temp_address);
					list.add(c);
				}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException();
		} finally {
			MyConnection.close(rs, stmt, con);
		}
	}
	/**
	 * 아이디에 해당 고객을 저장소에서 찾아 반환한다
	 * @param id 아이디
	 * @return 고객객체
	 * @throws FindException 아이디에 해당 고객이 저장소에 없으면 "아이디에 해당하는 고객이 없습니다"메시지를 갖는 예외발생한다
	 */
	public Customer findById(String id) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
			String SelectSQL = "SELECT * FROM customer Where id=(id) VALUES(?)";
			pstmt = con.prepareStatement(SelectSQL);
			pstmt.setString(1, id); 
			rs = pstmt.executeQuery();
			return (Customer) rs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}
	/**
	 * 단어를 포함한 이름을 갖는 고객들을 반환한다
	 * @param word 단어
	 * @return 고객들
	 * @throws FindException 단어를 포함한 이름을 갖는 고객이 한명도 없으면 "단어를 포함한 이름의 고객은 없습니다"메시지를 갖는 예외가 발생한다
	 */
	public List<Customer> findByName(String word) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			while(rs.next()) {
			con = MyConnection.getConnection();
			String SelectSQL = "SELECT * FROM customer Where id=(name) VALUES(?)";
			pstmt = con.prepareStatement(SelectSQL);
			pstmt.setString(1, word); 
			rs = pstmt.executeQuery();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return null;
	}
	/**
	 * 아이디에 해당하는 고객정보(비밀번호, 이름, 주소)를 수정한다<br>
	 * 단, 정보가 기존내용과 같을 경우는 변경하지 않는다
	 * @param c 변경할 고객객체
	 * @throws ModifyException c고객객체의 아이디가 저장소에 없으면 예외 발생한다<br>
	 *                         기존내용과 같을 경우는 "고객정보가 변경되지않았습니다"메시지를 갖는 예외가 발생한다
	 */
	public void modify(Customer c) throws ModifyException {
	}
	public void remove(String id) {
	}
	public void printInfo() {
	}
	
	
	
}
