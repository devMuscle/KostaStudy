package com.my;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import com.my.exception.FindException;
//import com.my.product.service.ProductService;
//import com.my.product.vo.Product;

public class SpringApplicationWithXml {

	public static void main(String[] args) {
		String configLocation = "config.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		
//		ProductDAOInterface dao = context.getBean("pDAO", ProductDAOInterface.class);
//		ProductDAOInterface dao1 = context.getBean("pDAO", ProductDAOInterface.class);
//		System.out.println(dao == dao1);
//		try {
//			Product p = dao.findByNo("C0001");
//			System.out.println(p);
//		}catch(FindException e) {
//			System.out.println(e.getMessage());
//		}

//		ProductService service = context.getBean("pService", ProductService.class);
//		try {
//			Product p = service.findByNo("C0001");
//			System.out.println(p);
//		}catch(FindException e) {
//			System.out.println(e.getMessage());
//		}
	}

}
