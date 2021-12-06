<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>include directive</h1>
<%@ include file="a.jsp" %>

<hr>
<h1>include tag</h1>
<jsp:include page="a.jsp"></jsp:include>
</body>
</html>