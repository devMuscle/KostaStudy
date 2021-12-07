<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session.jsp</title>
</head>
<body>
세션ID : <%=session.getId() %><br>
세션IS NEW : <%=session.isNew() %><br>
세션최종사용시간 : <%=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(session.getLastAccessedTime())) %>
</body>
</html>