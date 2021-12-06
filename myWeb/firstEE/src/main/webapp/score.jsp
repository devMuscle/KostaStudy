<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! int totalScore; //총점
	int totalCnt; //인원수
	DecimalFormat df = new DecimalFormat("#.0");
%>
<% 
String score = request.getParameter("score");
int scoreNum = Integer.parseInt(score);

totalScore += scoreNum;
totalCnt++;
double avgScore = (totalScore+0.0)/totalCnt;
%>
선택하신 별점은 <%= scoreNum %>점입니다<br>
영화 총점은 <%=totalScore %>점입니다<br>
참여한 인원수는 <%=totalCnt %>명입니다<br>
영화평점은 <%=df.format(avgScore)%>입니다.
</body>
</html>