package com.my.order.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.my.customer.vo.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.service.OrderService;
import com.my.order.vo.OrderInfo;
import com.my.order.vo.OrderLine;
import com.my.product.vo.Product;
@Controller
public class OrderController {
	@Autowired
	private OrderService service;
	@GetMapping("/addorder")
	public String add(HttpSession session, Model model) {
		String viewName = "jsonresult.jsp";
		
		int status = 0; //실패
		String msg = "";
		//1. 로그인여부
		Customer c = (Customer)session.getAttribute("loginInfo");
		if(c == null) {
			msg = "로그인하세요";
		}else {
			//2. 장바구니존재
			Map<String,Integer>  cart =(Map)session.getAttribute("cart");
			if(cart == null) {
				msg = "장바구니가 비었습니다";
			}else {
				OrderInfo info = new OrderInfo();
				info.setOrderCustomer(c);
				List<OrderLine> lines = new ArrayList<>();
				for(String prodNo : cart.keySet()) {
					int qt = cart.get(prodNo);
					OrderLine line = new OrderLine();
					Product p = new Product();
					p.setProdNo(prodNo);
					line.setOrderProduct(p);
					line.setOrderQuantity(qt);
					lines.add(line);
				}
				info.setLines(lines);
				
				try {
					service.add(info);
					session.removeAttribute("cart");
					status = 1;
					msg = "주문성공";
				} catch (AddException e) {
					e.printStackTrace();
					msg = "주문실패:" + e.getMessage();
				}
			}			
		}
		model.addAttribute("status", status);
		model.addAttribute("msg", msg);
		//VIEW로 이동
		return viewName;
	}
	
	@GetMapping("orderlist")
	public String list(HttpSession session, Model model) {
		Customer c = (Customer)session.getAttribute("loginInfo");
		String viewName = "jsonresult.jsp";
		if(c != null) {
			try {
				List<OrderInfo> list = service.findById(c.getId());
				viewName = "orderlistresult.jsp";
				model.addAttribute("list", list);
			} catch (FindException e) {
				e.printStackTrace();
				model.addAttribute("status", 0);
				model.addAttribute("msg", e.getMessage());
			}
		}else {
			model.addAttribute("status", 0);
			model.addAttribute("msg", "로그인하세요");
		}
		return viewName;
	}
}
