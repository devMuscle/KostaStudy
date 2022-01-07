package com.my.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.my.customer.vo.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.vo.OrderInfo;
import com.my.order.vo.OrderLine;
import com.my.product.vo.Product;
import com.my.sql.MyConnection;

public class OrderDAOOracle implements OrderDAOInterface {
	@Autowired
	private DataSource ds;
	
	@Override
	public void add(OrderInfo info) throws AddException {
		String insertInfoSQL =
"INSERT INTO order_info(order_no, order_id, order_dt) VALUES (order_seq.NEXTVAL, ?, SYSDATE)";
		String insertLineSQL = 
"INSERT INTO order_line(order_no, order_prod_no, order_quantity) VALUES (order_seq.CURRVAL,?, ?)";
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			//con = MyConnection.getConnection();//DB와 연결
			con = ds.getConnection();
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
		String selectByIdSQL = "SELECT info.order_no,\r\n"
				+ "         info.order_dt,\r\n"
				+ "         line.order_prod_no,\r\n"
				+ "         p.prod_name,\r\n"
				+ "        p.prod_price,\r\n"
				+ "         line.order_quantity\r\n"
				+ " FROM order_info info\r\n"
				+ "JOIN order_line line ON info.order_no=line.order_no\r\n"
				+ "JOIN product p ON line.order_prod_no = p.prod_no\r\n"
				+ "WHERE info.order_id=?\r\n"
				+ "ORDER BY info.order_no DESC, p.prod_name";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			//con = MyConnection.getConnection();
			con = ds.getConnection();
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setString(1, orderId);
			rs = pstmt.executeQuery();
			
			List<OrderInfo> infos = new ArrayList<>();
			OrderInfo info = null;
			List<OrderLine> lines = null;
			int old_order_no = 0; //주문번호는 최소 1부터 시작
			while(rs.next()) {
				int order_no = rs.getInt("order_no");
				java.sql.Date order_dt = rs.getDate("order_dt");
				String prodNo = rs.getString("order_prod_no");
				String prodName = rs.getString("prod_name");
				int prodPrice = rs.getInt("prod_price");
				int orderQuantity = rs.getInt("order_quantity");
				if(old_order_no != order_no) {
					info = new OrderInfo();
					info.setOrderNo(order_no);
					info.setOrderDt(order_dt);
					lines = new ArrayList<>();
					info.setLines(lines);
					infos.add(info);
					old_order_no = order_no;
				}
				OrderLine line = new OrderLine();
				line.setOrderNo(order_no);
				Product p = new Product();
				p.setProdNo(prodNo); p.setProdName(prodName); p.setProdPrice(prodPrice);
				line.setOrderProduct(p);
				line.setOrderQuantity(orderQuantity);
				lines.add(line);
			}
			if(infos.size() == 0) {
				throw new FindException("주문이 없습니다");
			}
			return infos;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
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
