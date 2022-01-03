package com.my.order.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.my.customer.vo.Customer;
import com.my.exception.AddException;
import com.my.order.service.OrderService;
import com.my.order.vo.OrderInfo;
import com.my.order.vo.OrderLine;
import com.my.product.vo.Product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int status = 0; //실패
		String msg = "";
		
		//1. 로그인여부
		Customer c = (Customer)session.getAttribute("loginInfo");
		if(c == null) {
			msg = "로그인하세요";
		}else {
			//2. 장바구니존재
			Map<String,Integer> cart = (Map)session.getAttribute("cart");
			if(cart == null) {
				msg = "장바구니가 비었습니다";
			} else {
				OrderInfo info = new OrderInfo();
				info.setOrderCustomer(c);
				List<OrderLine> lines = new ArrayList<>();
				for(String prodNo : cart.keySet()) {
					int qt = cart.get(prodNo);
					OrderLine line = new OrderLine();
					Product p = new Product();
					p.setProdNo(prodNo);
					line.setOrderProduct(p);
					line.setOrderQuantity(qt);
					lines.add(line);
				}
				info.setLines(lines);
				OrderService service = new OrderService();
				try {
					service.add(info);
					session.removeAttribute("cart");
					status = 1;
					msg = "주문성공";
				} catch (AddException e) {
					e.printStackTrace();
					msg = "주문실패:" + e.getMessage();
				}
			}
			
		}
		
		request.setAttribute("status", status);
		request.setAttribute("msg", msg);
		//VIEW로 이동
		String path = "jsonresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
		
	}

}
