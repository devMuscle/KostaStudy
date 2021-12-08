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
            div.productdetail>div.productdetail_info>div.modal{
            	width : 100px;
            	border: 1px solid;
            	background-color : gray;
            	display:none;
            }
        </style>
<!--  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->        
        <script>
        $(function(){
        	/*---장바구니 넣기 버튼클릭되었을때 START---*/
        	let $formObj = $('div.productdetail>div.productdetail_info>form');
        	$formObj.submit(function(){
        		let ajaxUrl = "./putcart";
        		let method = "get";
        		let data = $(this).serialize(); //prodNo=XXX&quantity=YYY
        		$.ajax({
        			url : ajaxUrl,
        			method : method,
        			data : data,
        			success:function(){
        				$('div.productdetail>div.productdetail_info>div.modal').show();
        			},
        			error : function(xhr){
        				alert('응답실패' + xhr.status);
        			}
        		});
        		return false;
        	});
        	/*---장바구니 넣기 버튼클릭되었을때 END--*/
        	
        	/*--모달div의 장바구니보기버튼 클릭되었을때 START--*/
        	$('div.productdetail>div.productdetail_info>div.modal>button.cartlist').click(function(){
        		//메뉴중 장바구니객체를 DOM트리에서 찾기
        		let $menuCartlistObj = $('header>nav>ul>li>a[href=cartlist]');
        		//alert($menuCartlistObj.attr("href"));
        		$menuCartlistObj.trigger('click');
        		return false;
        	});
        	/*--모달div의 장바구니보기버튼 클릭되었을때 END--*/
        	
        	/*--모달div의 상품버튼 클릭되었을때 START--*/
        	$('div.productdetail>div.productdetail_info>div.modal>button.productlist').click(function(){
        		//메뉴중 장바구니객체를 DOM트리에서 찾기
        		let $menuCartlistObj = $('header>nav>ul>li>a[href=productlist]');
        		//alert($menuCartlistObj.attr("href"));
        		$menuCartlistObj.trigger('click');
        		return false;
        	});
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