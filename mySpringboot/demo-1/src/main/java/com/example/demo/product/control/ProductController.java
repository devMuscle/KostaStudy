package com.example.demo.product.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.exception.FindException;
import com.example.demo.product.service.ProductService;
import com.example.demo.product.vo.Product;


@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	//상품전체목록
	@GetMapping("productlist")
	public ModelAndView list() {
		ModelAndView mnv = new ModelAndView();
		try {
			List<Product> list = service.findAll();
			mnv.addObject("list", list);
			mnv.setViewName("productlistresult");
			
		} catch (FindException e) {
			e.printStackTrace();
			mnv.addObject("msg", e.getMessage());
			mnv.setViewName("failresult");
		}
		return mnv;
	}
	
	//상품상세
	@GetMapping("productdetail")
	public String detail(String prodNo, Model model) {
		try {
			Product p = service.findByNo(prodNo);
			model.addAttribute("p", p);
			return "productdetailresult";
		}catch(FindException e) {
			model.addAttribute("msg", e.getMessage());
			return "failresult";
		}
	}
}