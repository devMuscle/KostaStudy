<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.FileInputStream"%>
<%@page errorPage="err.jsp" %><%--현재jsp에서 예외발생하면 그 즉시 버퍼CLEAR, err.jsp로 forward하라 --%>
<%@page buffer="1024kb" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pagedirective.jsp</title>
</head>
<body>
<%-- 1~99999반복하여 값 출력 --%>
<%
for(int i=1; i<=99999; i++){
%><%=i%>		
<%
}
%>
<%-- a.txt파일 읽기 --%>
<%--
FileInputStream fis;
fis = new FileInputStream("a.txt");
--%>
<%--
FileInputStream fis;
try{
	fis = new FileInputStream("a.txt");
}catch(FileNotFoundException e){
	out.print("a.txt파일이 없습니다. 관리자에게 문의하세요");
}
--%>
<%--
FileInputStream fis;
try{
	fis = new FileInputStream("a.txt");
}catch(FileNotFoundException e){
	request.setAttribute("e", e);
	RequestDispatcher rd = request.getRequestDispatcher("err.jsp");
	rd.forward(request, response);
}
--%>
<%
FileInputStream fis;
fis = new FileInputStream("a.txt");
%>
</body>
</html>