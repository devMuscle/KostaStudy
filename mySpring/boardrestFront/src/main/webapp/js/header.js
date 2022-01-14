//--메뉴 클릭 시작--
$("header").on("click", "ul>li>a", function(event) {
	$('header>ul>li>a').css("background-color", 'white');
	$(this).css("background-color", "blanchedalmond");
	var menu = $(event.target).attr("class");
	//alert("메뉴:" + $(event.target).attr("class"));
	console.log($(this));
	console.log("-----");
	console.log($(event.target));
	var $sectionObj = $("section");
	switch (menu) {
		case 'list'://게시판
			$sectionObj.load(frontContextPath + "/boardlist.html");
			break;
		case 'write'://글쓰기
			$sectionObj.load(frontContextPath + "/write.html");
			break;
	}
	return false;
});
//--메뉴 클릭 끝--
