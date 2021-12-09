package com.my.customer.controll;

import java.io.IOException;

import com.my.customer.service.CustomerService;
import com.my.customer.vo.Customer;
import com.my.exception.FindException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idValue = request.getParameter("id");
		String pwdValue = request.getParameter("pwd");
		System.out.println("LoginServlet의 doPost() id=" + idValue +", pwd=" + pwdValue);
		
		String resultMsg = "";
		CustomerService service;
		service = new CustomerService();
		
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo"); //초기화
		
		String path = "result";
		//2.비지니스로직 호출
		try {
			Customer c = service.login(idValue, pwdValue);
			System.out.println("로그인 성공");
			session.setAttribute("loginInfo", c);
			
			//3.응답결과만들기
			resultMsg = "로그인 성공";
			path = "success";
		}catch(FindException e) {
			System.out.println(e.getMessage());
			resultMsg = "로그인 실패"; //resultMSg = e.getMessage();
			path = "fail";
		}
		request.setAttribute("msg", resultMsg);
		
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
		//		CustomerDAOOracle dao;
//		dao = new CustomerDAOOracle();
//		try {
//			Customer c = dao.findById(idValue);
//			if(c.getPwd().equals(pwdValue)) {
//				System.out.println("로그인 성공");
//			}else {
//				System.out.println("로그인 실패");
//			}
//		} catch (FindException e) {
//			e.printStackTrace();
//			System.out.println("로그인 실패"); //e.getMessage()-아이디가 없어요
//		}
	}
}
	


