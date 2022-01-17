package com.example.demo.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.exception.FindException;
import com.example.demo.product.dao.ProductDAOInterface;
import com.example.demo.product.vo.Product;



@Service("pService")
public class ProductService {
	@Autowired	
	private ProductDAOInterface dao;
	
	public ProductService() {
		//dao = new ProductDAOOracle();
	}
	public ProductService(ProductDAOInterface dao) {
		this.dao = dao;
		System.out.println("ProductService(" + dao + ")생성자호출됨");
	}
	public void setDao(ProductDAOInterface d) {
		this.dao = d;
		System.out.println("ProductService의 setDao(" + d +")호출됨");
	}
	
	public List<Product> findAll() throws FindException{
		return dao.findAll();
	}
	public Product findByNo(String prodNo) throws FindException{
		return dao.findByNo(prodNo);
	}
}
