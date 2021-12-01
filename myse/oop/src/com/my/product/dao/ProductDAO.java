package com.my.product.dao;

import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.product.vo.Product;

public class ProductDAO {
	private List<Product> products; //상품저장소
	public ProductDAO() {
		products = new ArrayList<>();
	}
	/**
	 * 상품을 저장소에 추가한다
	 * @param product 상품객체
	 * @throws AddException 상품번호가 중복된 경우 "이미 존재하는 상품입니다"메시지를 갖는 예외가 발생한다
	 */
	public void add(Product product) throws AddException {
		for(Product p: products) {
			if(p.equals(product)) {
				throw new AddException("이미 존재하는 상품입니다");
			}
		}
		products.add(product);
	}
	/**
	 * 상품 전체 검색한다
	 * @return 모든 상품들
	 * @throws FindException 저장소에 상품이 없으면 "상품이 없습니다" 메시지를 갖는 예외가 발생한다
	 */
	public List<Product> findAll() throws FindException{
		if(products.size()==0) {
			throw new FindException("상품이 없습니다");
		}
		return products;
	}
	/**
	 * 상품번호로 검색한다
	 * @param prodNo 상품번호
	 * @return 상품객체
	 * @throws FindException 상품이 없으면 "상품이 없습니다"메시지를 갖는 예외발생한다
	 */
	public Product findByProdNo(String prodNo) throws FindException {
		for(Product p : products) {
			if(p.getProdNo().equals(prodNo)) {
				return p;
			}
		}
		throw new FindException("상품이 없습니다");
	}
	/**
	 * 단어를 포함한 상품이름으로 검색한다
	 * @param word 단어
	 * @return 상품들
	 * @throws FindException 해당단어를 포함한 상품이 없으면 "상품이 없습니다"메시지를 갖는 예외발생한다
	 */
	public List<Product> findByProdName(String word) throws FindException {
		List<Product> list = new ArrayList<>();
		for(Product p : products ) {
			if(p.getProdNmae().contains(word)) {
				list.add(p);
			}
		}
		if(list.size()>0) {
			return list;
		}else {
			throw new FindException("상품이 없습니다");
		}
	}
	
	public static void main(String[] args) {
		ProductDAO dao = new ProductDAO();
		System.out.println("--상품추가--");
		try {
			dao.add(new Product("C0001", "아메리카노", 1000));
			dao.add(new Product("C0002", "ICE아메리카노", 1000));
			dao.add(new Product("C0003", "라테", 1500));
			dao.add(new Product("C0004", "ICE라테", 1500));
		}catch(AddException e) {
			e.printStackTrace();
		}
		
		
		//4. 다음코드를 실행하면 아래결과가 나타남
				//상품이 없습니다		
				try {
					System.out.println(dao.findByProdNo("X"));
				} catch (FindException e) {
					e.printStackTrace();
				}

	}
}
