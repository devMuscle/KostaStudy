package com.my.product.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.product.entity.Product;

public interface ProductDAOInterface {
	/**
	 * 전체상품을 반환한다
	 * @return 상품객체
	 * @throws FindException 상품이 없는경우에 예외발생한다
	 *                       검색할 수 없는 경우 예외발생한다
	 */
	public List<Product> findAll() throws FindException;
	/**
	 * 상품번호에 해당하는 상품을 반환한다
	 * @param prodNo 상품번호
	 * @return 상품객체
	 * @throws FindException 상품이 없는경우에 예외발생한다
	 *                       검색할 수 없는 경우 예외발생한다
	 */
	public Product findByNo(String prodNo) throws FindException;
	/**
	 * 상품번호 또는 상품명에 해당하는 상품들을 반환한다
	 * @param word 상품번호 또는 상품명에 폼함될 단어
	 * @return 상품객체들
	 * @throws FindException 상품이 없는경우에 예외발생한다
	 *                       검색할 수 없는 경우 예외 발생한다
	 */
	public List<Product> findByNoOrName(String word) throws FindException;
	/**
	 * 
	 * @param product
	 * @throws FindException
	 */
	public void add(Product product) throws AddException;
	public void modify(Product product) throws ModifyException;
	public void remove(String prodNo) throws FindException, RemoveException;
	
	
}
