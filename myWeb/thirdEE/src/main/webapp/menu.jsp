<%@page import="com.my.customer.vo.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul>
<%
Customer c = (Customer)session.getAttribute("loginInfo");
if(c == null) { //로그인이 안된 경우
%>
	<li><a href="login.html">로그인</a></li>
	<li><a href="signup.html">가입</a></li>
<%
}else{
%>  <li><%=c.getName()%>님 반갑습니다.<a href="logout">로그아웃</a></li>
<%
}
%>
	<li><a href="productlist">상품</a></li>
	<li><a href="cartlist">장바구니</a></li>
<%
if(c != null) {
%>
	<li><a href="orderlist">주문목록</a></li>
<%
}
%>
</ul>