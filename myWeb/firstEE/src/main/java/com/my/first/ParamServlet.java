package com.my.first;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String fileName;
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		fileName = this.getInitParameter("fileName");
		ServletContext sc = this.getServletContext();
		String developer = sc.getInitParameter("developer");
		System.out.println("개발 총책임자" + developer);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("사용할 파일명:" + fileName);
		
	}

}
