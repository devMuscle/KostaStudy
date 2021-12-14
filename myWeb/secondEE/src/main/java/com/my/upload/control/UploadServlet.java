package com.my.upload.control;

import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDirectory = "C:\\MSA-Full-Stack-SW\\myWeb";
   		try {
   		    Part part = request.getPart("f");
   		   	String fileName = part.getSubmittedFileName();
   		   	System.out.println(fileName);
		    part.write(saveDirectory+"\\" + fileName);
   		       		    
   		} catch (Exception e) {
   		    e.printStackTrace();
   		}

	}

}
