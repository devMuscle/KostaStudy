<%@page import="com.my.product.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Product> list = 
    (List)request.getAttribute("list");
%>   
<%--<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productlistresult.jsp</title>
 --%>
<style>
        *{
            box-sizing: border-box;
        }
        
        div.productlist{
            width:100%;
        }
        div.productlist>div{
            /*width:24%;*/
            margin: 0.4%;
            float:left;
        }
        div.productlist>div>ul{
            list-style-type: none;
            padding-left: 0px;
        }
        div.productlist>div>ul>li{
            text-align:center;
        }
        div.productlist>div>ul>li>img{
            width:100%;
            /* max-width: 100%; */
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(function(){
        	let $divObj = $('div.productlist>div');
        	$divObj.click(function(){
        		/* console.log(this); */
        		let prodNo = $(this).attr('id');
        		let ajaxUrl = './productdetail';
        		/* $.ajax({
        			url: ajaxUrl,
        			method : 'get',
        			data : {prodNo: prodNo},
        			success:function(responseData){
        				let $articlesObj = $('section>div.articles');
        				$articlesObj.empty();
        				$articlesObj.html(responseData);
        			}
        		}); */
        		$('section>div.articles').load('./productdetail?prodNo='+prodNo);
        		return false;
        	});
        });
	</script>
<%-- </head>
<body> --%>
<div class="productlist">
<%for(Product p: list){ 
	String prodNo = p.getProdNo();
	String prodName = p.getProdName();
	//int prodPrice = p.getProdPrice();
%>
    <div id="<%=prodNo%>"><!--상품1개-->
        <ul>
            <li><img src="./images/<%=prodNo%>.jpg" alt="<%=prodName%>"></li>
            <li><span><%=prodName%></span></li>
        </ul>
    </div>
<%} %>    
</div>
<%--</body>
</html>
 --%>