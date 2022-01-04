<%@page import="com.my.customer.vo.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
List<String> list = (List)request.getAttribute("list");
if(list==null){
	list = new ArrayList();
	request.setAttribute("list", list);
}
--%>
<%--
<jsp:useBean id="list" class="java.util.ArrayList" scope="request"/>
--%>

<%--
Customer loginInfo = (Customer)session.getAttribute("loginInfo");
if(loginInfo==null){
	loginInfo = new Customer();
	session.setAttribute("loginInfo", loginInfo);
}
--%>
<%-- 
<jsp:useBean id="loginInfo" class="com.my.customer.vo.Customer" scope="session" />
--%>
<%--=loginInfo.getName() --%>
<%-- <jsp:getProperty name="loginInfo" property="name" /> --%>

<%--
OrderInfo orderInfo = (orderInfo)request.getAttribute("orderInfo");
if(orderInfo == null){
	orderInfo = new OrderInfo();
	request.setAttribute("orderInfo", orderInfo);
--%>
<%--
<jsp:useBean id="orderInfo" class="com.my.order.vo.OrderInfo" scope="request" />
 --%>
<%--<%=orderInfo.getOrderNo() %> --%>
<%-- 
<jsp:getProperty name="orderInfo" property="orderNo" />
 --%>
<%-- <%=orderInfo.getOrderCustomer().getId()%> --%>
<%-- 아래작업은 못함 : has a 관계의 property를 getProperty태그로는 해결못함
<jsp:getProperty name="orderInfo" property="orderCustomer.id">
--%>

<%--Expression Language를 활용(has a 관계의 property를 표현) --%>
requestScope.orderInfo.orderCustomer.id = ${requestScope.orderInfo.orderCustomer.id}
<br>
<%--<%=request.getParameter("a")%> --%>
param.id = ${param.a}

<%--EL은 값이 null인 경우 ""로 변환해서 출력한다--%>
<br>
<%--request 객체 얻기--%>
<%--=request.getContextPath() --%>
pageContext.request.ContextPath = ${pageContext.request.contextPath}
</body>
</html>