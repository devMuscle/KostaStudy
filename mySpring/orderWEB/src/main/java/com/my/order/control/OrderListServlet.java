package com.my.order.control;

import java.io.IOException;
import java.util.List;

import com.my.customer.vo.Customer;
import com.my.exception.FindException;
import com.my.order.service.OrderService;
import com.my.order.vo.OrderInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer c = (Customer)session.getAttribute("loginInfo");
		String path = "jsonresult.jsp";
		if(c != null) {
			OrderService service = new OrderService();
			try {
				List<OrderInfo> list = service.findById(c.getId());
				path = "orderlistresult.jsp";
				request.setAttribute("list", list);
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("status", 0);
				request.setAttribute("msg", e.getMessage());
			}
		}else {
			request.setAttribute("status", 0);
			request.setAttribute("msg", "로그인하세요");
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}


}