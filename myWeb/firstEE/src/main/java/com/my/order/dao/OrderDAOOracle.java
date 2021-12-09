package com.my.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.my.customer.vo.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.vo.OrderInfo;
import com.my.order.vo.OrderLine;
import com.my.product.vo.Product;
import com.my.sql.MyConnection;

public class OrderDAOOracle implements OrderDAOInterface {

	@Override
	public void add(OrderInfo info) throws AddException {
		String insertInfoSQL =
"INSERT INTO order_info(order_no, order_id, order_dt) VALUES (order_seq.NEXTVAL, ?, SYSDATE)";
		String insertLineSQL = 
"INSERT INTO order_line(order_no, order_prod_no, order_quantity) VALUES (order_seq.CURRVAL,?, ?)";
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();//DB와 연결
			con.setAutoCommit(false); //자동커밋해제
			
			//---주문기본추가 시작----
			pstmt = con.prepareStatement(insertInfoSQL);//SQL구문송신
			
			String orderId = info.getOrderCustomer().getId();
			pstmt.setString(1, orderId);
			pstmt.executeUpdate(); //SQL실행
			//---주문기본추가 끝----
			
			//--주문상세추가 시작--
			pstmt = con.prepareStatement(insertLineSQL);//SQL구문송신			
			List<OrderLine> lines = info.getLines();
			for(OrderLine line: lines) {
				String orderProdNo = line.getOrderProduct().getProdNo();
				int orderQuantity = line.getOrderQuantity();
				pstmt.setString(1, orderProdNo);
				pstmt.setInt(2, orderQuantity);
				pstmt.executeUpdate(); //SQL실행
			}
			//--주문상세추가 끝--
			con.commit();
		}catch(Exception e) {
			if(con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
				}
			}
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			MyConnection.close(pstmt, con);
		}
		
		
		
		
		
		

	}

	@Override
	public List<OrderInfo> findById(String orderId) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderInfo> findByDate(Date dt) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderInfo> findByDate(Date startDt, Date endDate) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {
		OrderDAOOracle dao = new OrderDAOOracle();
		OrderInfo info = new OrderInfo();
		String orderId = "id1";
		Customer c = new Customer(); c.setId(orderId);
		info.setOrderCustomer(c);
		
		List<OrderLine> lines = new ArrayList<>();
		OrderLine line = new OrderLine();
		Product p = new Product(); p.setProdNo("C0001");
		
		line.setOrderProduct(p);
		line.setOrderQuantity(1);
		lines.add(line);
		
		OrderLine line2 = new OrderLine();
		Product p2 = new Product(); p2.setProdNo("C0002");
		
		line2.setOrderProduct(p2);
		line2.setOrderQuantity(2);
		lines.add(line2);
		
		info.setLines(lines);
		
		try {
			dao.add(info);
		} catch (AddException e) {
			e.printStackTrace();
		}
	}

}
