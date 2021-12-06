package com.my.product.controll;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.my.exception.FindException;
import com.my.product.service.ProductService;
import com.my.product.vo.Product;

public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		try {
			List<Product> list = service.findAll();
		} catch (FindException e) {
			e.printStackTrace();
		}
	}

}
