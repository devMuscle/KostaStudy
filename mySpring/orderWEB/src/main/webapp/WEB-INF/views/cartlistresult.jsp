<%@page import="java.util.Set"%>
<%@page import="com.my.product.vo.Product"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
Map<Product, Integer>cart = (Map)request.getAttribute("cart");
String msg = "";
if(cart == null){ //장바구니가 없거나 비어있는 경우
	msg = "장바구니가 비었습니다";
%><%=msg %>
<%}else{
	Set<Product>products = cart.keySet();
%>
<link rel="stylesheet" href="./css/cartlist.css">
<style>
</style>
<script src="./js/cartlist.js"></script>
<script>
	$(function(){
		/*--주문하기 버튼클릭 START --*/
		addOrderClick();
		/*--주문하기 버튼클릭 END   --*/
	});
</script>
<h3>장바구니 목록</h3>
<table class="cartlist">	
<tr><th>상품번호</th>
    <th>상품명</th>
    <th>상품가격</th>
    <th>수량</th>
</tr>
<%	for(Product p: products){
		int qt = cart.get(p);
%>
<tr><td><%=p.getProdNo() %></td>
    <td><%=p.getProdName() %></td>
    <td><%=p.getProdPrice() %></td>
    <td><%=qt%></td>
</tr>		
<%	}//end for
%>
</table>
<button class="addorder">주문하기</button>
<%}//end if
%>