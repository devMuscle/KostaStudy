package com.my.listner;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class MyServletContextListener
 *
 */
public class MyServletContextListener implements ServletContextListener {


    public MyServletContextListener() {
        // TODO Auto-generated constructor stub
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("ServletContext객체 소멸됨");
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
        System.out.println("ServletContext객체 생성됨");
    }
	
}
