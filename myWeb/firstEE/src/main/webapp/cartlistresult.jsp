<%@page import="java.util.Set"%>
<%@page import="com.my.product.vo.Product"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
</style>
<%
Map<Product, Integer>cart = (Map)request.getAttribute("cart"); 
String msg = "";
if(cart == null) {
	msg = "장바구니가 비었습니다";
%><%=msg %>
<%}else{
	Set<Product>products = cart.keySet();
%>
<script>
	$(function(){
		/*--주문하기 버튼클릭 START--*/
		$('button.addorder').click(function(){
			let ajaxUrl = "./addorder";
			$.ajax({
				url: ajaxUrl,
				success: function(responseObj){
					if(responseObj.status == 0){ //주문실패
						alert(responseObj.msg); 
					}else {//주문성공
						//location.href="./selector.jsp";
						//$('header>nav>ul>li>a[href=productlist]').trigger('click'); //상품목록보기
						//주문목록보기
					}
				}
			});
			return false;
		});
		/*--주문하기 버튼클릭 END--*/
	});
</script>
<h2>장바구니 목록</h2>
<table>
<tr><th>상품번호</th>
	<th>상품명</th>
	<th>상품가격</th>
	<th>수량</th>
</tr>
<%	for(Product p: products){
		int qt = cart.get(p);
%><tr><td><%=p.getProdNo() %></td>
  	  <td><%=p.getProdName() %></td>
      <td><%=p.getProdPrice() %></td>
      <td><%=qt %></td>
</tr>
<%  } //end for
%>
</table>
<button class="addorder">주문하기</button>
<%} //end if
%>
<html>
<head>
<meta charset="UTF-8">
<title>cartlistresult.jsp</title>
</head>
<body>

</body>
</html>