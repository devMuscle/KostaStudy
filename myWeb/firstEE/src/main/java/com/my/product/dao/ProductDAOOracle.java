package com.my.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.product.vo.Product;
import com.my.sql.MyConnection;

public class ProductDAOOracle implements ProductDAOInterface {

	@Override
	public List<Product> findAll() throws FindException {
		Connection con = null; //DB연결
		PreparedStatement pstmt = null; //SQL송신
		ResultSet rs = null; //결과 수신
		String selectAllSQL = "SELECT * FROM product ORDER BY prod_name ASC";
		List<Product> list = new ArrayList<>();
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectAllSQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String prodNo = rs.getString("prod_no");
				String prodName = rs.getString("prod_name");
				int prodPrice = rs.getInt("prod_price");
				Product p = new Product(prodNo, prodName, prodPrice);
				list.add(p);
			}
			if(list.size()==0) {
				throw new FindException("상품이 없어용");
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
		// TODO Auto-generated method stub
		return null;
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
	public void remove(String prodNo) throws FindException {
		// TODO Auto-generated method stub

	}

}
