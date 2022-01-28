package com.my.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.my.product.entity.Product;


public interface ProductRepository extends CrudRepository<Product, String> {
	
	Product findByProdNo(String no);
}
