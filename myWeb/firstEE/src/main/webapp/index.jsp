<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>selector.jsp</title>
<meta charset="UTF-8" />
<link rel="stylesheet" href="./css/header.css">
<link rel="stylesheet" href="./css/section.css">
<link rel="stylesheet" href="./css/footer.css">
<style>
header, section, footer {
	width: 100%;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="./js/menu.js"><</script>
<script>
      $(function () {
    	/*--메뉴가 클릭되었을때 START--*/
        menuClick();
        /*--메뉴가 클릭되었을때 END--*/
      });
    </script>
</head>
<body>
	<header>
		<!-- 머리말 -->
		<h1 class="logo">
			<!-- 로고이미지 -->
			<a href="">코스타벅스커피 코리아</a>
		</h1>
		<nav>
			<!-- 메뉴 -->
			<%--menu.jsp를 실행시 포함 --%>
			<jsp:include page="menu.jsp"></jsp:include>

		</nav>
	</header>

	<section>
		<!-- 본문 -->
		<div class="articles">
			<article>
				본문내용1
				<figure>
					<img class="drink1" src="./images/2021_christmas_drink1.png"
						alt="토피넛라테" />
					<img class="drink2" src="./images/2021_christmas_drink2.png"
						alt="골든위시라테" />
					<figcaption>크리스마스 음료</figcaption>
				</figure>
			</article>
			<article>본문내용2</article>
		</div>
		<aside>
			광고1, 광고2
			<div>
				<a href=""> <img
					src="https://image.istarbucks.co.kr/upload/banner/floatingbnr/1iqO76_20211115094854583.png"
					alt="2021년 2차 바리스타 공개채용" class="pc-badge" />
				</a>
			</div>
			<div>
				<a href=""> <img
					src="https://image.istarbucks.co.kr/upload/banner/floatingbnr/1B4Upb_20211029180059437.png"
					alt="PLCC 스타벅스 현대카드 11월 프로모션" class="pc-badge" />
				</a>
			</div>
		</aside>
		<!-- <article>공지사항</article> -->
	</section>

	<footer>
		<!-- 맺음말 -->
		<%@include file="./footer.jsp"%>
	</footer>
</body>
</html>
