package com.my.product.service;

import java.util.List;

import com.my.exception.FindException;
import com.my.product.dao.ProductDAOOracle;
import com.my.product.vo.Product;

public class ProductService {
	public List<Product> findAll() throws FindException{
		ProductDAOOracle dao = new ProductDAOOracle();
		return dao.findAll();
	}
}
