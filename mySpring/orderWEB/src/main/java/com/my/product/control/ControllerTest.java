package com.my.product.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.my.product.service.ProductService;

//import jakarta.servlet.http.HttpServletRequest;

//@Controller("productdetail")
public class ControllerTest {

	private Logger logger = Logger.getLogger(getClass());

//	@Autowired
//	private ProductService service;

	//----컨트롤러메서드의 매개변수(request, response, session, 요청전달데이터 이름, 커맨드 클래스(JavaBean클래스))----//
//	@GetMapping("productdetail")
//	public ModelAndView detail(HttpServletRequest request) {
//		try {
//			String prodNo = request.getParameter("prodNo");
//			Product p = service.findByNo(prodNo);
//			ModelAndView mnv = new ModelAndView();
//			mnv.addObject("greeting", "hello"); //request.setAttribute와 같은 효과
//			mnv.addObject("p", p);
//			mnv.setViewName("productdetailresult.jsp"); //이동할 view이름
//			return mnv;
//		} catch (FindException e) {
//			return null;
//		}
//		
//	}
//
//	@GetMapping("productdetail")
//	public ModelAndView detail(String prodNo) {
//		try {
//			// String prodNo = request.getParameter("prodNo");
//			Product p = service.findByNo(prodNo);
//			ModelAndView mnv = new ModelAndView();
//			mnv.addObject("greeting", "hello"); // request.setAttribute와 같은 효과
//			mnv.addObject("p", p);
//			mnv.setViewName("productdetailresult.jsp"); // 이동할 view이름
//			return mnv;
//		} catch (FindException e) {
//			return null;
//		}
//
//	}

//	@GetMapping("productadd")
//	public ModelAndView add(String prodNo, String prodName, int prodPrice) {
//		
//		logger.warn(prodNo + ":" + prodName + ":" + prodPrice);
//		Product p = new Product(prodNo, prodName, prodPrice);
//		//service.add(p);
//		
//		return null;
//	}

//	http://localhost:8888/orderWEB/productadd?prodNo=1&prodName=2&prodPrice=3
//	http://localhost:8888/orderWEB/productadd?prodNo=1&prodPrice=3	
//  http://localhost:8888/orderWEB/productadd?prodNo=20	

//	@GetMapping("productadd")
//	public ModelAndView add(@RequestParam(name="prodNo") String no,
//							@RequestParam(name="prodName", required = false) String name,
//							@RequestParam(name="prodPrice", required = false, defaultValue = "0") int price) {
//		
//		logger.warn(no + ":" + name + ":" + price);
//		Product p = new Product(no, name, price);
//		//service.add(p);
//		
//		return null;
//	}

// http://localhost:8888/orderWEB/productadd?prodNo=1&prodName=2&prodPrice=3
// http://localhost:8888/orderWEB/productadd?prodNo=1&prodPrice=3

//	@GetMapping("productadd")
//	public ModelAndView add(Product p) {
//		logger.warn(p);
//		return null;
//	}

//  http://localhost:8888/orderWEB/productChk?c=coffe&c=tea&c=ing
//  http://localhost:8888/orderWEB/productChk	
//	@GetMapping("productChk")
//	public ModelAndView chk(@RequestParam(name = "c", required = false) String[] cArr) {
//		if (cArr != null) {
//			for (String c : cArr) {
//				logger.warn(c);
//			}
//		}
//		return null;
//	}
	
	//---- 컨트롤러 메서드의 반환형(ModelAndView, String, void ) ----//
//	http://localhost:8888/orderWEB/productdetail?prodNo=C0001	
//	@GetMapping("productdetail")
//	public String detail(String prodNo, Model model) {
//		try {
//			// String prodNo = request.getParameter("prodNo");
//			Product p = service.findByNo(prodNo);
//			
//			//request.setAttribute()과 같은 효과
//			model.addAttribute("p", p);
//			String viewName = "productdetail"; //FORWARD할 VIEW이름
//			return viewName;
//		} catch (FindException e) {
//			return null;
//		}
//
//	}
//	
//	@GetMapping("productdetail")
//	public void detail(String prodNo, Model model) {
//		try {
//			// String prodNo = request.getParameter("prodNo");
//			Product p = service.findByNo(prodNo);
//			
//			//request.setAttribute()과 같은 효과
//			model.addAttribute("p", p);
////			String viewName = "productdetail"; //FORWARD할 VIEW이름
////			return viewName;
//		} catch (FindException e) {
////			return null;
//		}
//
//	}
}
