package com.my.cart.control;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.exception.FindException;
import com.my.product.service.ProductService;
import com.my.product.vo.Product;

@Controller
public class CartController {
	@Autowired
	private ProductService service;
	@GetMapping("cartlist")
	public String list (HttpSession session, Model model) {
		Map<String,Integer>cart = (Map)session.getAttribute("cart");
		if(cart == null || cart.size() == 0) {

		}else {
			//장바구니의 상품번호들 얻기
			Set<String>prodNos = cart.keySet();

			//응답용 자료구조
			Map<Product, Integer> responseMap = new HashMap<>();

			//ProductService service = new ProductService();
			//상품번호별 상품정보 찾기, 상품번호별 수량얻기
			for(String prodNo : prodNos) {
				try {
					Product p = service.findByNo(prodNo); //상품번호별 상품정보 찾기
					int quantity = cart.get(prodNo);//상품번호별 수량얻기
					responseMap.put(p, quantity); //응답용자료구조에 추가
				}catch(FindException e) {
				}
			}
			model.addAttribute("cart", responseMap);

		}
		String viewName = "cartlistresult.jsp";
		return viewName;
	}

	@GetMapping("putcart")
	public ResponseEntity  put(String prodNo, 
			@RequestParam(name="quantity")int intQuantity,
			HttpSession session) {

		//세션객체의 속성중 cart이름의 속성찾기
		Map<String,Integer>cart = (Map)session.getAttribute("cart");
		//cart이름의 속성없으면 cart이름(이름:cart, 값: Map<String, Integer>)의 속성추가
		if(cart == null) {
			cart = new HashMap<String,Integer>();
			session.setAttribute("cart", cart);
		}
		//cart에 상품번호에 해당상품이 있다면 기존수량에 intQuantity를 누적
		//cart에 상품번호에 해당상품이 없다면 cart의 값으로 prodNo, intQuantity추가
		Integer cartQuantity = cart.get(prodNo);
		if(cartQuantity != null) { //cart에 상품번호에 해당상품이 있다면 
			intQuantity += cartQuantity; 
		}
		//int cartQuantity = cart.get(prodNo);
		cart.put(prodNo, intQuantity);

		//-----cart확인-----
		Set<String> prodNos = cart.keySet(); 
		for(String pNo: prodNos) {
			Integer qt = cart.get(pNo);
			System.out.println(pNo + ":" + qt);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
