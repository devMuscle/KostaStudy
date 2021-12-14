package com.my.upload.control;

import java.io.File;
import java.io.IOException;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		InputStream is = request.getInputStream();
//		Scanner sc = new Scanner(is);
//		while(sc.hasNextLine()) {
//			String line = sc.nextLine();
//			System.out.println(line);
//		}
//		String saveDirectory = "c:\files";
//		MultipartRequest mr = new MultipartRequest(request, saveDirectory);
		DiskFileItemFactory fileItemFactory;
		fileItemFactory = new DiskFileItemFactory();
		String saveDirectory = "c:\\files";
		File f = new File(saveDirectory);
		fileItemFactory.setRepository(f);
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		fileUpload.parseRequest(request);
	}

}
