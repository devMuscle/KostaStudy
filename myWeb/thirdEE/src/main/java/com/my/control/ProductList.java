package com.my.control;

import java.io.IOException;
import java.util.List;

import com.my.exception.FindException;
import com.my.product.service.ProductService;
import com.my.product.vo.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductList implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ProductService service = new ProductService();
		String path = "";
		try {
			//비지니스로직호출
			List<Product> list = service.findAll();
			
			//응답할 결과 요청속성에 설정
			request.setAttribute("list", list);
			path = "productlistresult.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			path = "failresult.jsp";
		}	
		return path;
	}

}
