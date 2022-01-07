package com.my.cart.control;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.my.exception.FindException;
import com.my.product.service.ProductService;
import com.my.product.vo.Product;

@Controller
public class CartController {

	@Autowired
	private ProductService service;
	
	@GetMapping("cartlist")
	public ModelAndView cartList(Model model) {
		ModelAndView mnv = new ModelAndView();
		Map<String,Integer>cart = (Map)model.getAttribute("cart");
		if(cart == null || cart.size() == 0) {
	
		}else {
			//장바구니의 상품번호들 얻기
			Set<String>prodNos = cart.keySet();
			//상품번호별 상품정보 찾기
			Map<Product, Integer> responseMap = new HashMap<>();
			for(String prodNo : prodNos) {
				try {
					Product p = service.findByNo(prodNo); //상품번호별 상품정보 찾기
					int quantity = cart.get(prodNo); //삼품번호별 수량얻기
					responseMap.put(p, quantity); //응답용자료구조에 추가
				}catch(FindException e) {
				}
			}
			mnv.addObject("cart", responseMap);
			System.out.println(responseMap);
		}
		
		//VIEW로 이동
		String path = "cartlistresult.jsp";
		mnv.setViewName(path);
		return mnv;
	}
	
	@GetMapping("putcart")
	public void putCart(@RequestParam("prodNo") String prodNo,@RequestParam("quantity") String quantity, Model model) {
		//요청전달데이터 얻기
		int intQuantity = Integer.parseInt(quantity);
		
		//세션객체의 속성중 cart이름의 속성찾기
		Map<String,Integer>cart = (Map)model.getAttribute("cart");
		//cart이름의 속성없으면 cart이름(이름:cart 값:Map<String, Integer>)의 속성추가
		if(cart == null) {
			cart = new HashMap<String,Integer>();
			model.addAttribute("cart", cart);
		}
		
		Integer cartQuantity = cart.get(prodNo);
		if(cartQuantity != null) { //cart에 상품번호에 해당상품이 있다면 기존수량에 inQuantity를 누적 //cart에 상품번호에 해당상품이 없다면 cart의 값으로 prodNo, intQuantity추가
			intQuantity += cartQuantity;
		} 
			
		cart.put(prodNo, intQuantity);
		
		//----cart확인-----
		Set<String> prodNos = cart.keySet();
		for(String pNo: prodNos) {
			Integer qt = cart.get(pNo);
			System.out.println(pNo + ":" + qt);
		}
	}
}
