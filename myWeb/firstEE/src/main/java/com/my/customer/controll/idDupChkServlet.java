package com.my.customer.controll;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.my.customer.service.CustomerService;
import com.my.exception.FindException;

import jakarta.servlet.RequestDispatcher;

/**
 * Servlet implementation class idDupChkServlet
 */
public class idDupChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String idValue = request.getParameter("id");
//		System.out.println("입력받은 id값:" + idValue);
//		
//		String resultMsg = "";
//		CustomerService service = new CustomerService();
//		
//		try {
//			service.addupchk(idValue);
//			System.out.println("중복된 아이디입니다");
//			resultMsg = "중복된 아이디입니다";
//		} catch (FindException e) {
//			System.out.println("사용가능한 아이디입니다");
//			resultMsg = "사용가능한 아이디입니다";
//		}
//				
//		String path = "result";
//		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//		request.setAttribute("msg", resultMsg);
//		dispatcher.forward(request, response);
		PrintWriter out = response.getWriter();
		
		//응답출력스트림에 출력
		out.print("애애애애앵");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
