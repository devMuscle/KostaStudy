package com.my.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.product.vo.Product;
import com.my.sql.MyConnection;

@Repository("pDAO")
public class ProductDAOOracle implements ProductDAOInterface {
	@Autowired
	private DataSource ds;
	private Logger log = Logger.getLogger(ProductDAOOracle.class.getName());
	public ProductDAOOracle() {
		System.out.println("ProductDAOOracl 생성자 호출됨");
	}
	@Override
	public List<Product> findAll() throws FindException {
		Connection con = null; //DB연결
		PreparedStatement pstmt = null; //SQL송신
		ResultSet rs = null; //결과 수신
		String selectAllSQL = "SELECT * FROM product ORDER BY prod_name ASC";
		List<Product> list = new ArrayList<>();
		try {
			//con = MyConnection.getConnection();
			con = ds.getConnection();
			pstmt = con.prepareStatement(selectAllSQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String prodNo = rs.getString("prod_no");
				String prodName = rs.getString("prod_name");
				int prodPrice = rs.getInt("prod_price");
				Product p = new Product(prodNo, prodName, prodPrice);
				list.add(p);
			}
			if(list.size() == 0) {
				throw new FindException("상품이 없습니다");
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public Product findByNo(String prodNo) throws FindException {
		log.info("ProductDAOOracle의 findByNo");
		Connection con = null; //DB연결
		PreparedStatement pstmt = null; //SQL송신
		ResultSet rs = null; //결과 수신
		String selectByNoSQL = "SELECT * FROM product WHERE prod_no=?";
		try {
			//con = MyConnection.getConnection();
			con = ds.getConnection();
			pstmt = con.prepareStatement(selectByNoSQL);
			pstmt.setString(1, prodNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String prodName = rs.getString("prod_name");
				int prodPrice = rs.getInt("prod_price");
				return new Product(prodNo, prodName, prodPrice);
			}
			throw new FindException("상품이 없습니다");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public List<Product> findByNoOrName(String word) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Product product) throws AddException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modify(Product product) throws ModifyException {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String prodNo) throws RemoveException {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args) throws FindException {
		ProductDAOOracle dao = new ProductDAOOracle();
		String prodNo = "C0001";
		Product p = dao.findByNo(prodNo);
		System.out.println(p);
	}

}
