package com.my.cart.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class PutCartServlet
 */
public class PutCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청전달데이터 얻기
		String prodNo = request.getParameter("prodNo");
		String quantity = request.getParameter("quantity");
		int intQuantity = Integer.parseInt(quantity);
		
		HttpSession session = request.getSession();
		
		//세션객체의 속성중 cart이름의 속성찾기
		Map<String,Integer>cart = (Map)session.getAttribute("cart");
		//cart이름의 속성없으면 cart이름(이름:cart 값:Map<String, Integer>)의 속성추가
		if(cart == null) {
			cart = new HashMap<String,Integer>();
			session.setAttribute("cart", cart);
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
