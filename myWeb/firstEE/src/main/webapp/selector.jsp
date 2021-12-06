<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>selector.jsp</title>
    <meta charset="UTF-8" />
    <style>
      * {
        box-sizing: border-box;
      }
      header,
      section,
      footer {
        width: 100%;
      }
      header {
        background-color: #f6f5ef;
        border-bottom: 1px solid #e5e5e5;
        height: 100px;
      }
      header > h1 {
        width: 45px;
        height: 45px;
        float: left;
      }
      header > h1.logo > a {
        background-image: url(./images/logo.png);
        background-repeat: no-repeat;
        background-size: 45px auto;
        display: block;
        width: 100%;
        height: 100%;
        overflow: hidden;
        text-indent: -20000px;
      }
      header > nav {
        background-color: grey;
        margin: 21.4px auto;
        float: right;
      }
      header > nav > ul {
        list-style-type: none;
        padding-left: 0;
      }
      header > nav > ul > li {
        display: inline-block;
        width: 100px;
        text-align: center;
      }
      header > nav > ul > li > a {
        text-decoration: none;
      }
      header > nav > ul > li > a:hover {
        background-color: #f6f5ef;
      }
      section {
        height: 750px;
      }
      section > div.articles {
        width: 90%;
        height: 100%;
        border: 1px solid;
        overflow: auto;
        float: left;
      }
      section article {
        width: 100%;
        height: 357px;
        background-color: pink;
        border: white 1px solid;
      }
      section > div > article:first-child > figure {
        margin-top: 5px;
      }
      section > div > article:first-child > figure > img.drink1 {
        width: 200px;
      }
      section > div > article:first-child > figure > img.drink2 {
        width: 300px;
      }
      section > aside {
        width: 10%;
        float: right;
      }
      footer {
        background-color: black;
        color: white;
      }
      footer > aside a {
        color: white;
      }
      footer > aside > ul {
        list-style-type: none;
        padding-left: 0px;
      }
      footer > aside > ul > li {
        display: inline-block;
      }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
      $(function () {
        /*--메뉴가 클릭되었을때 START--*/
        let $menuObj = $("header>nav>ul>li>a");
        $menuObj.click(function () {
          let menuHref = $(this).attr("href"); //href속성값얻기
          console.log("메뉴 href=" + menuHref);

          let ajaxUrl = "";
          let ajaxMethod = "";
          switch (menuHref) {
            case "login.html":
              //AJAX요청,응답
              ajaxUrl = menuHref;
              ajaxMethod = "GET";

              $("section>div.articles").empty();
              $("section>div.articles").load(ajaxUrl);
              return false; //기본이벤트핸들러금지, 이벤트전파중지
            // break;
            case "signup.html":
              ajaxUrl = menuHref;
              ajaxMethod = "GET";
              $("section>div.articles").empty();
              $("section>div.articles").load(ajaxUrl);
              return false;
          }
        });
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
            <img
              class="drink1"
              src="./images/2021_christmas_drink1.png"
              alt="토피넛라테"
            />
            <img
              class="drink2"
              src="./images/2021_christmas_drink2.png"
              alt="골든위시라테"
            />
            <figcaption>크리스마스 음료</figcaption>
          </figure>
        </article>
        <article>본문내용2</article>
      </div>
      <aside>
        광고1, 광고2
        <div>
          <a href="">
            <img
              src="https://image.istarbucks.co.kr/upload/banner/floatingbnr/1iqO76_20211115094854583.png"
              alt="2021년 2차 바리스타 공개채용"
              class="pc-badge"
            />
          </a>
        </div>
        <div>
          <a href="">
            <img
              src="https://image.istarbucks.co.kr/upload/banner/floatingbnr/1B4Upb_20211029180059437.png"
              alt="PLCC 스타벅스 현대카드 11월 프로모션"
              class="pc-badge"
            />
          </a>
        </div>
      </aside>
      <!-- <article>공지사항</article> -->
    </section>

    <footer>
      <!-- 맺음말 -->
      <%@include file="./footer.jsp" %>
    </footer>
  </body>
</html>
