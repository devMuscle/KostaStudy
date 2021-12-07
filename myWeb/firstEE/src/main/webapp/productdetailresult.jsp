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
        <style>
            div.productdetail{
                box-sizing: border-box;
                width : 100%;
                height: 300px;
            }
            div.productdetail>div.productdetail_img{
                width : 35%;
                float: left;
            }
            div.productdetail>div.productdetail_img img{
            	width : 100%;
            	}
            div.productdetail>div.productdetail_info{
                width : 60%;
                float: right;
            }
            div.productdetail>div.productdetail_info>form>ul{
                list-style-type: none;
                padding-left: 10px; 
            }
        </style>
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
                    <input type="hidden" name="prod_no" value="<%=prodNo%>">
                    <ul>
                    	<li>상품번호:<%=prodNo %></li>
                        <li>가격 : <%=prodPrice %></li>
                        <li>수량 : <input name="quantity" type="number" min="1" max="99" value="1"></li>
                        <li><input type="submit" value="장바구니넣기"></li>
                    </ul>
                </form>
            </div>
        </div>
    </body>
</html>