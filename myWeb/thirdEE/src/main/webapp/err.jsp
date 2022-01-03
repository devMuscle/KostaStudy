<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>err.jsp</title>
</head>
<body style="background-color:pink">
<%--Exception e = (Exception)request.getAttribute("e");--%>
<%--=e.getMessage() --%>
<%=exception.getMessage() %>
</body>
</html>