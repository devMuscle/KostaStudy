package com.my.control;

import java.io.IOException;

import com.my.customer.service.CustomerService;
import com.my.customer.vo.Customer;
import com.my.exception.FindException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Login implements Controller {
	private CustomerService service = CustomerService.getInstance();
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String idValue = request.getParameter("id");
		String pwdValue = request.getParameter("pwd");
		System.out.println("LoginServlet의 doPost() id=" + idValue +", pwd=" + pwdValue);
		
		String resultMsg = "";
		
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo"); //초기화
		
		String path = "jsonresult.jsp";
		//2.비지니스로직 호출
		try {
			Customer c = service.login(idValue, pwdValue);
			System.out.println("로그인 성공");
			session.setAttribute("loginInfo", c);
			
			//3.응답결과만들기
			resultMsg = "로그인 성공";
			request.setAttribute("status", 1);
		}catch(FindException e) {
			System.out.println(e.getMessage());
			resultMsg = "로그인 실패"; //resultMSg = e.getMessage();
			request.setAttribute("status", 0);
		}
		request.setAttribute("msg", resultMsg);
		return path;
	}

}
