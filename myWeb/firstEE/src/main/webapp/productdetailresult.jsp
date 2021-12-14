<%@page import="com.my.product.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Product p = (Product)request.getAttribute("p"); 
String prodNo = p.getProdNo();
String prodName = p.getProdName();
int prodPrice = p.getProdPrice();
%>    
<!DOCTYPE html>
<html>
    <head>
<!--  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->        
        <link rel="stylesheet" href="./css/productdetail.css">
        <script src="./js/productdetail.js"></script>
        <script>
        $(function(){
        	/*---장바구니 넣기 버튼클릭되었을때 START---*/
        	packagePut();
        	/*---장바구니 넣기 버튼클릭되었을때 END--*/
        	
        	/*--모달div의 장바구니보기버튼 클릭되었을때 START--*/
        	packageSee();
        	/*--모달div의 장바구니보기버튼 클릭되었을때 END--*/
        	
        	/*--모달div의 상품버튼 클릭되었을때 START--*/
        	productClick();
        	/*--모달div의 상품버튼 클릭되었을때 END--*/
        	
        })
        </script>
    </head>
    <body>
        <div class="productdetail">
            <div class="productdetail_img">
                <img src="./images/<%=prodNo%>.jpg" alt="<%=prodName%>">
            </div>
            <div class="productdetail_info">
                <h1><%=prodName %></h1>
                <hr>
                <form>
                    <input type="hidden" name="prodNo" value="<%=prodNo%>">
                    <ul>	
                    	<li>상품번호:<%=prodNo %></li>
                        <li>가격 : <%=prodPrice %></li>
                        <li>수량 : <input name="quantity" type="number" min="1" max="99" value="1"></li>
                        <li><input type="submit" value="장바구니넣기"></li>
                    </ul>
                </form>
                <div class ="modal">
                	<button class="cartlist">장바구니보기</button>&nbsp;&nbsp;
                	<button class="productlist">상품</button>
                </div>
            </div>
        </div>
    </body>
</html>