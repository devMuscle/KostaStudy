package com.my.first;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답형식지정
		response.setContentType("text/html;charset=UTF-8");
		//응답출력스트림얻기
		PrintWriter out = response.getWriter();
		out.print("결과-요청전달데이터t값:" + request.getParameter("t"));
	}

}
