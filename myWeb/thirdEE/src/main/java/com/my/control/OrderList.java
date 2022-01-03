package com.my.control;

import java.io.IOException;
import java.util.List;

import com.my.customer.vo.Customer;
import com.my.exception.FindException;
import com.my.order.service.OrderService;
import com.my.order.vo.OrderInfo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class OrderList implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
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
		return path;
	}

}
