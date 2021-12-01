package com.my.first;

import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class RequestServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tValue = request.getParameter("t");
		System.out.println(tValue);
		String pValue = request.getParameter("p");
	
		//http://localhost:8888/firstEE/request?c=java&c=sql&c=html
		String[] cArr = request.getParameterValues("c");
		if(cArr != null) {
			for(String c: cArr) {
				System.out.println(c);
			}
		}
		
		System.out.println(request.getServletPath());
		System.out.println(request.getContextPath());
		System.out.println(request.getProtocol());
		System.out.println(request.getRequestURI());
		System.out.println(request.getRequestURL());
		
		Enumeration<String> names = request.getHeaderNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getHeader(name);
			//Accept: 클라이언트WB가 해석할 수 있는 MIMEcontentType정보
			//User-Agent : 클라이언트컴퓨터의 OS정보, WB정보 
			System.out.println("요청헤더명:" + name +", 값:" + value);
		}
	}

}
