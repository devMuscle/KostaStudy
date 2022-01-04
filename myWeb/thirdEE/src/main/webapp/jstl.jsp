<%@page import="java.util.ArrayList"%>
<%@page import="com.my.customer.vo.Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl.jsp</title>
</head>
<body>
<h1>JspStandardTagLib</h1>
EL기반의 조건문,반복문처리가능
jstl용 library설치되어야함(프로젝트경로 WEB-INF\lib)되어야함
<hr>
<%--
String opt = request.getParameter("opt");
if(opt==null || opt.equals(""){
}
 --%>
 <c:set var="opt" value="${param.opt}"/>
 
<c:if test="${empty opt}">작업을 선택하세요</c:if>

<%--
if(opt.equals("add")){
}else if(opt.equals("list")){
}else if(opt.equals("modify")){
}else if(opt.equals("remove")){
}
 --%>
 <hr>
 <c:choose>
 	<c:when test="${opt=='add'}">추가작업을 선택했습니다
 	</c:when>
 	<c:when test="${opt=='list'}">목록작업을 선택했습니다
 	</c:when>
 	<c:when test="${opt=='modify'}">수정작업을 선택했습니다
 	</c:when>
 	<c:when test="${opt=='remove'}">삭제작업을 선택했습니다
 	</c:when>
 	<c:otherwise>작업을 선택하세요
 	</c:otherwise>
 </c:choose>
<hr>
<% //컨트롤러가 해줄 일
List<Customer> list = new ArrayList<>();
list.add(new Customer("id1", "p1", "n1", null));
list.add(new Customer("id2", "p2", "n2", null));
list.add(new Customer("id3", "p3", "n3", null));
list.add(new Customer("id4", "p4", "n4", null));
list.add(new Customer("id5", "p5", "n5", null));
request.setAttribute("list", list);
%>
<%--
List<Customer> list = (List)request.getAttribute("list");
for(Customer c:list){
	out.print(c.getId());
}
 --%>
 <c:set var="list" value="${requestScope.list}"/>
 <table>
 <tr><th>번호(index:count)</th><th>아이디</th><th>이름</th></tr>
<c:forEach items="${list}" var="c" varStatus="status">
	<tr>	
		<td>${status.index} : ${status.count}</td><td>${c.id}</td><td>{c.name}</td>
	</tr>
</c:forEach>
</table>
<hr>

<%--
DecimalFormat df = new DecimalFormat("#,##0");
for(int i=1; i<=7; i++){
	out.print(df.format(i*1000));
}
 --%>
<c:forEach begin="1" end="7" var="i">
	<c:if test="${i>1}">,</c:if>
	<fmt:formatNumber pattern="#,##0" value="${i*1000}"/>
</c:forEach>

</body>
</html>