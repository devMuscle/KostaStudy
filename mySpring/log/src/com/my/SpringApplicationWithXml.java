package com.my;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.my.exception.FindException;
import com.my.product.dao.ProductDAOInterface;
import com.my.product.service.ProductService;
import com.my.product.vo.Product;

public class SpringApplicationWithXml {
public static void main(String[] args) {
//		String configLocation = "config.xml";
//		ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);

		String configLocation = "C:\\MSA-Full-Stack-SW\\mySpring\\di\\config\\config.xml";
		ApplicationContext context = new FileSystemXmlApplicationContext(configLocation);
		
		ProductDAOInterface dao = context.getBean("pDAO", ProductDAOInterface.class);
		ProductDAOInterface dao1 = context.getBean("pDAO", ProductDAOInterface.class);
		System.out.println(dao == dao1); //true
		System.out.println(dao);
		try {
			Product p = dao.findByNo("C0001");
			System.out.println("dao결과 p=" + p);
		}catch(FindException e) {
			System.out.println(e.getMessage());
		}
		
		ProductService service = context.getBean("pService", ProductService.class);
		try {
			Product p = service.findByNo("C0001");
			System.out.println("service결과 p=" + p);
		}catch(FindException e) {
			System.out.println(e.getMessage());
		}
		//Object obj = context.getBean("p");
		//Product p2 = (Product)obj;
		
		Product p1 = context.getBean("p", Product.class);
		System.out.println("p1=" + p1);
		
		Product p2 = context.getBean("pp", Product.class);
		System.out.println("p2=" + p2);
		
		Product p3 = context.getBean("ppp", Product.class);
		System.out.println("p3=" + p3);
	}

}
