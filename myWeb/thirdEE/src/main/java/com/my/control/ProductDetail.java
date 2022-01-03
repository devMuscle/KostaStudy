package com.my.control;

import java.io.IOException;

import com.my.exception.FindException;
import com.my.product.service.ProductService;
import com.my.product.vo.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductDetail implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
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
		return path;
	}

}
