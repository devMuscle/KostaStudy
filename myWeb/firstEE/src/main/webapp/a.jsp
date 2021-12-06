<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%int i; 
i = 10;
i++;%>

지역변수 i값: <% out.print(i);%>,
<%= i %>
<%!int i; %>
<%=i %>
<%=this.i %>
</body>
</html>