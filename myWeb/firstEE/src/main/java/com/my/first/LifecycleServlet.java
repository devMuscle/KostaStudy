package com.my.first;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
public class LifecycleServlet extends HttpServlet implements Servlet {
       
	
    public LifecycleServlet() {
        super();
        //객체생성시 자동호출됨
        System.out.println("생성자호출됨");
//        ServletContext sc;
//        sc = this.getServletContext();//NullpointException 발생
//        String atxtRealPath = sc.getRealPath("a.txt");
//        System.out.println("a.txt파일의 실제경로:" + atxtRealPath);
    }

	public void init(ServletConfig config) throws ServletException {
		//객체생성시 자동호출됨 서블릿초기화역할
		System.out.println("init()호출됨");
		super.init(config);
		ServletContext sc;
        sc = this.getServletContext();
        String atxtRealPath = sc.getRealPath("a.txt");
        System.out.println("a.txt파일의 실제경로:" + atxtRealPath);
	}
	
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		//요청할때마다 자동호출됨
		System.out.println("serivce() 호출됨");
	}
	
	public void destroy() {
		//객체소멸시 자동호출됨
		System.out.println("destory() 호출됨");
	}
}
