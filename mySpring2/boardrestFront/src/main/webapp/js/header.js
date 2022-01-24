//--메뉴 클릭 시작--
$("header").on("click", "ul>li>a", function(event) {
	$('header>ul>li>a').css("background-color", 'white');
	$(this).css("background-color", "blanchedalmond");
	var menu = $(event.target).attr("class");

	var $sectionObj = $("section");
	switch (menu) {
		case 'login'://게시판
			$.ajax({
				url: backContextPath + "/login",
				method: "post",
				data: { id: "id2", pwd: "p2" },
				success: function() {
					location.href=frontContextPath;
				},
				error: function(xhr) {
					alert("로그인오류: " + xhr.status);
				}
			});
			break;
		case 'logout'://게시판
			$.ajax({
				url: backContextPath + "/logout",
				method: "post",
				data: {},
				success: function() {
					location.href=frontContextPath;
				},
				error: function(xhr) {
					alert("로그인오류: " + xhr.status);
				}
			});
			break;

		case 'list'://게시판
			$sectionObj.load(frontContextPath + "/boardlist.html");
			break;
		case 'write'://글쓰기
			$sectionObj.load(frontContextPath + "/write.html");
			break;
	}
	return false;
});

//--로그인 여부 확인 시작--
function checkLogin() {
	$.ajax({
		url: backContextPath + "/checkLogin",
		method: "get",
		success: function(jsonData) {
			if (jsonData == "") {
				loginedId = null;
				$("header ul>li>a.login").show();
				$("header ul>li>a.logout").hide();
				$("header ul>li>a.write").hide();
			} else {
				loginedId = jsonData.id;
				$("header ul>li>a.login").hide();
				$("header ul>li>a.logout").show();
				$("header ul>li>a.write").show();
			}
		}
	});
};
//--로그인 여부 확인 끝--


$(function() {
	checkLogin();
	window.setInterval(checkLogin, 10*1000); 
});
//--메뉴 클릭 끝--