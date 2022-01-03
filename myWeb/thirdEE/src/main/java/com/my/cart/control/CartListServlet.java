package com.my.cart.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.my.exception.FindException;
import com.my.product.service.ProductService;
import com.my.product.vo.Product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CartListServlet
 */
public class CartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<String,Integer>cart = (Map)session.getAttribute("cart");
		if(cart == null || cart.size() == 0) {
			
		}else {
			//장바구니의 상품번호들 얻기
			Set<String>prodNos = cart.keySet();
			//상품번호별 상품정보 찾기
			Map<Product, Integer> responseMap = new HashMap<>();
			ProductService service = new ProductService();
			for(String prodNo : prodNos) {
				try {
					Product p = service.findByNo(prodNo); //상품번호별 상품정보 찾기
					int quantity = cart.get(prodNo); //삼품번호별 수량얻기
					responseMap.put(p, quantity); //응답용자료구조에 추가
				}catch(FindException e) {	
				}
			}
			request.setAttribute("cart", responseMap);
			System.out.println(responseMap);
		}
		
		//VIEW로 이동
		String path = "cartlistresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
