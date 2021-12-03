package com.my.customer.controll;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.my.customer.service.CustomerService;
import com.my.customer.vo.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;

import jakarta.servlet.RequestDispatcher;

/**
 * Servlet implementation class SignupServlet
 */
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idValue = request.getParameter("id");
		String pwdValue = request.getParameter("pwd");
		String nameValue = request.getParameter("name");
		
		CustomerService service = new CustomerService();
		Customer c = new Customer(idValue, pwdValue, nameValue, "ad");
		
		String path = "result";
		
		try {
			service.signup(c);
			path="success";
			System.out.println("db에 입력성공");
		}catch(AddException e){
			System.out.println("db에 입력실패");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

}
