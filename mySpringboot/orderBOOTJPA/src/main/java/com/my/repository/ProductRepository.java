package com.my.repository;

import org.springframework.data.repository.CrudRepository;

import com.my.product.vo.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

}
