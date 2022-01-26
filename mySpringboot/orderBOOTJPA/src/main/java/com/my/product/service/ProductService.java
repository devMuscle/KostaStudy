package com.my.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.my.exception.FindException;
import com.my.product.dao.ProductDAOInterface;
import com.my.product.dao.ProductDAOOracle;
import com.my.product.entity.Product;
import com.my.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired	
	private ProductRepository repository;
	
	public ProductService() {
		//dao = new ProductDAOOracle();
	}
	public ProductService(ProductRepository repository) {
		this.repository = repository;
		System.out.println("ProductService(" + repository + ")생성자호출됨");
	}
	public void setDao(ProductRepository repository) {
		this.repository = repository;
		System.out.println("ProductService의 setDao(" + repository +")호출됨");
	}
	
	public List<Product> findAll() throws FindException{
		return (List<Product>) repository.findAll();
	}
	
	
	public Product findByProdNo(String prodNo) throws FindException{
		return repository.findByProdNo(prodNo);
	}
	
}
