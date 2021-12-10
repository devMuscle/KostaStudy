<%@page import="com.my.product.vo.Product"%>
<%@page import="com.my.order.vo.OrderLine"%>
<%@page import="com.my.order.vo.OrderInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
table.orderlist{
	border:1px solid;
	border-collapse:collapse;
}
table.orderlist td, table.orderlist th{
	border:1px solid;
}
</style>
<%
List<OrderInfo> list = (List)request.getAttribute("list");
%>
<table class="orderlist">
<tr>
<th>주문번호</th>
<th>주문일자</th>
<th>상품번호</th>
<th>상품명</th>
<th>가격</th>
<th>수량</th>
</tr>
<%
for(OrderInfo info: list) {
	int orderNo = info.getOrderNo();
	java.util.Date orderDt = info.getOrderDt();
	List<OrderLine> lines = info.getLines();
%>
<tr>
<td rowspan="<%=lines.size()%>"><%=orderNo%></td>
<td rowspan="<%=lines.size()%>"><%=orderDt%></td>

<%
	for(OrderLine line : lines) {
		Product p = line.getOrderProduct();
		String prodNo = p.getProdNo();
		String prodName = p.getProdName();
		int prodPrice = p.getProdPrice();
		int orderQuantity = line.getOrderQuantity();
%> <td><%=prodNo%></td>
   <td><%=prodName%></td>
   <td><%=prodPrice%></td>
   <td><%=orderQuantity%></td>
   </tr>
<%	
	}	
}
%>
</table>