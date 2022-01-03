package com.my.control;

import java.io.IOException;

import com.my.customer.service.CustomerService;
import com.my.customer.vo.Customer;
import com.my.exception.AddException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Signup implements Controller {
	private CustomerService service = CustomerService.getInstance();
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String idValue = request.getParameter("id");
		String pwdValue = request.getParameter("pwd");
		String nameValue = request.getParameter("name");
		
		Customer c = new Customer(idValue, pwdValue, nameValue, "ad");
		
		String path = "jsonresult.jsp";
		String msg = "";
		try {
			service.signup(c);
//			path = "success";
			request.setAttribute("status", 1);
			msg = "가입성공";
			
		} catch (AddException e) {
			e.printStackTrace();
//			path = "fail";
			request.setAttribute("status", 0);
			msg = e.getMessage();
			
		}
		return path;

	}

}
