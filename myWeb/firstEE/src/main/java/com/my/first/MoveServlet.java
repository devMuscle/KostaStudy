package com.my.first;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//요청전달데이터opt값 얻기
		String opt = request.getParameter("opt");
		if(opt == null) {
			out.print("<a href=\"move?t=aaa&opt=redirect\">리다이렉트</a><br>");
			out.print("<a href=\"move?t=aaa&opt=forward\">포워드</a><br>");
			out.print("<a href=\"move?t=aaa&opt=include\">인클루드</a>");
		}else if(opt.equals("redirect")) {
			String resultMsg = "성공";
			request.setAttribute("msg", resultMsg);
			
			response.sendRedirect("result");
		}else if(opt.equals("forward")) {
			out.print("before forward");
			String resultMsg = "성공";
			request.setAttribute("msg", resultMsg);
			
			//String path = "result";
			String path = "/result";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			out.print("after forward");
			
		}else if(opt.equals("include")) {
			out.print("before include");
			String path = "/result";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.include(request, response);
			out.print("afater include");
		}

	}

}
