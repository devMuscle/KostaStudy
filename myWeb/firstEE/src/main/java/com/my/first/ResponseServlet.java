package com.my.first;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답형식지정 MIME
		response.setContentType("text/html;charset=UTF-8"); //text/html
		
		//응답츨력스트림얻기
		PrintWriter out = response.getWriter();
		
		//응답출력스트림에 출력
		out.print("<h1>");
		out.print("hello");
		out.print("안녕");
		out.print("</h1>");
	}

}
