package com.my.control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Properties env;

	@Override
	public void init() throws ServletException {
		// 1.control.properties파일 실제 경로 찾기
		ServletContext sc = this.getServletContext();
		String controlConfigPath = sc.getRealPath("config\\control.properties");
		env = new Properties();
		try {
			env.load(new FileInputStream(controlConfigPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();

		String controlClassName = env.getProperty(servletPath);

		try {
			System.out.println(controlClassName + " " + servletPath);
			Class clazz = Class.forName(controlClassName);
			Object obj = clazz.newInstance();
			Controller control = (Controller) obj;
			String path = control.execute(request, response);
			System.out.println(path);
			if (path == null || path.equals("")) {
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
