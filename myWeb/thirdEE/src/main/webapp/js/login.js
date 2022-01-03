/*
로그인이 클릭되었을때
 */
function loginClick() {
	let $loginFormObj = $("div.login>form");

	//localStroage에서 id값 찾기
	let savedIdValue = localStorage.getItem("savedid");
	if(savedIdValue != null) { //localStorage에 id값 있는경우
		$loginFormObj.find("input[name=id]").val(savedIdValue);
	}
	
	$loginFormObj.submit(function() {
		let ajaxUrl = $(this).attr("action");
		let ajaxMethod = $(this).attr("method");
		let idValue = $(this).find("input[name=id]").val();
		let pwdValue = $(this).find("input[name=pwd]").val();

		//아이디저장 체크박스객체찾기
		let $idSaveChkObj = $loginFormObj.find("div.others>input[type=checkbox]");
		if ($idSaveChkObj.prop('checked')) { //true인 경우 localStroage에 id값 저장
			localStorage.setItem("savedid", idValue);
		} else { //false인 경우 localStroage에 id값 삭제
			localStorage.removeItem("savedid");
		}
		$.ajax({
			url: ajaxUrl,
			method: ajaxMethod,
			data: { id: idValue, pwd: pwdValue },
			success: function(responseObj) {
				if (responseObj.status == 0) {
					//로그인실패
					alert(responseObj.msg);
				} else {
					//로그인성공
					location.href = "./";
				}
			},
			error: function(xhr) {
				alert("응답실패 status:" + xhr.status);
			},
		});
		return false; //기본이벤트핸들러 금지 + 이벤트전파 중지
	});
}
