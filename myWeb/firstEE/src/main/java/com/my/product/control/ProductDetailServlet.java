package com.my.product.control;

import java.io.IOException;

import com.my.exception.FindException;
import com.my.product.service.ProductService;
import com.my.product.vo.Product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodNo = request.getParameter("prodNo");
		ProductService service = new ProductService();
		String path = "";
		try {
			Product p = service.findByNo(prodNo);
			request.setAttribute("p", p);
			path = "productdetailresult.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			path = "failresult.jsp";
		}
		//VIEWER로 이동
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}