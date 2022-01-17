package com.example.demo.product.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.exception.AddException;
import com.example.demo.exception.FindException;
import com.example.demo.exception.ModifyException;
import com.example.demo.exception.RemoveException;
import com.example.demo.product.vo.Product;

@Repository("pDAO")
public class ProductDAOOracle implements ProductDAOInterface {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	//private Logger log = Logger.getLogger(ProductDAOOracle.class.getName());
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public ProductDAOOracle() {
		log.info("ProductDAOOracl 생성자 호출됨");
	}

	@Override
	public List<Product> findAll() throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			List<Product> list = session.selectList("com.my.product.ProductMapper.findAll");
			return list;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public Product findByNo(String prodNo) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Product p = session.selectOne("com.my.product.ProductMapper.findByNo", prodNo);
			if(p == null) {
				throw new FindException("상품이 없습니다");
			}
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException();
		} finally {
			if (session != null) {
				session.close();
			}
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
