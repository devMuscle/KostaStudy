package com.my.listner;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class MySessionAttributeListener
 *
 */
public class MySessionAttributeListener implements HttpSessionAttributeListener {
	private int loginedCount; //로그인한 사용자 수
	
    /**
     * Default constructor. 
     */
    public MySessionAttributeListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent ev)  { 
    	String evName = ev.getName();
    	if(evName.equals("loginInfo")) {
    		loginedCount++;
    		System.out.println("로그인된 사용자수: " + loginedCount);
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent ev)  { 
    	String evName = ev.getName();
    	if(evName.equals("loginInfo")) {
    		loginedCount--;
    		System.out.println("로그인된 사용자수: " + loginedCount);
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
