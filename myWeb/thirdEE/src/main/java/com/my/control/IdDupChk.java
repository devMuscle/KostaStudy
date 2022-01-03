package com.my.control;

import java.io.IOException;

import com.my.customer.service.CustomerService;
import com.my.exception.FindException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IdDupChk implements Controller {
	private CustomerService service = CustomerService.getInstance();
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String idValue = request.getParameter("id");
		System.out.println("입력받은 id값:" + idValue);

		String resultMsg = "";

		String path = "jsonresult.jsp";
		try {
			service.addupchk(idValue);
			System.out.println("중복된 아이디입니다");
			resultMsg = "중복된 아이디입니다";
			request.setAttribute("status", 0);
//			path = "fail";
		} catch (FindException e) {
			System.out.println("사용가능한 아이디입니다");
			resultMsg = "사용가능한 아이디입니다";
			request.setAttribute("status", 1);
//			path = "success";
		}
		return path;
	}

}
