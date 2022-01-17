$(function() {
	$('div.info').click(function() {
		return false;
	});

	$('div.reply').click(function() {
		return false;
	});

	//--답글쓰기 링크 클릭 시작--
	$("div.info>a.reply").click(function() {
		$("div.reply").show();
	});
	//--답글쓰기 링크 클릭 끝

	//--수정 링크 클릭 시작--
	$("div.info>a.modify").click(function() {
		let $infoObj = $(this).parent();

		let boardNo = $infoObj.find("div.data>span.no").html();
		let boardContent = $infoObj.find("div.data>textarea.content").val();
		let sendData = {};
		sendData["boardNo"] = boardNo;
		sendData["boardContent"] = boardContent;

		$.ajax({
			url: backContextPath + "/board/modify",
			data: sendData,
			method: "get",
			success: function(jsonData) {
				alert(jsonData.status);
				if (jsonData.status == 1) { //수정 성공
					$("header>ul>li>a.list").trigger("click");
				} else { //수정 실패
					alert(jsonData.msg);
				}
			},
			error: function(xhr) {
				alert(xhr.status);
			}

		});
	});
	//--수정 링크 클릭 끝--

	//--삭제 링크 클릭 시작--
	$("div.info>a.remove").click(function() {
		let $infoObj = $(this).parent(); //a객체의 부모객체		
		let boardNo = $infoObj.find('div.data>span.no').html(); //글번호
		/*
		let jsonData = { status: 1, msg: '성공' };
		//let jsonData = { status: 0, msg: '삭제실패' };
		if (jsonData.status == 1) { //삭제 성공
			alert(jsonData.msg);
			$("header>ul>li>a.list").trigger("click");
		} else { //삭제실패
			alert(jsonData.msg);
		}
		*/
		$.ajax({
			url: backContextPath + "/board/remove",
			data: "boardNo=" + boardNo,
			method: "get",
			success: function(jsonData) {
				if (jsonData.status == 1) {
					$('header>ul>li>a.list').trigger('click'); //게시판 목록
				} else {
					alert(jsonData.msg);
				}
			}, error: function(xhr) {
				alert(xhr.status);
			}

		});

	});
	//--삭제 링크 클릭 끝 --

	//--답글쓰기 버튼 클릭 시작
	let $replyBtObj = $("div.reply>form>input[type=button]");
	$replyBtObj.click(function() {
		let parentNo = $('div.info>div.data>span.no').html(); //글번호가 부모글번호역할
		let formData = $("div.reply>form").serialize();
		//답글제목 //답글내용
		//let sendData = "parentNo=" + parentNo + "&" + formData ;
		let sendData = "loginedId=" + loginedId + "&parentNo=" + parentNo + "&" + formData;
		alert(sendData);
		/*
		let jsonData = { status: 1, msg: '성공' };
		//let jsonData = { status: 0, msg: '답글실패' };
		if (jsonData.status == 1) { //삭제 성공
			$("header>ul>li>a.list").trigger("click"); //게시판 목록
		} else { //삭제실패
			alert(jsonData.msg);
		}
		*/

		$.ajax({
			url: backContextPath + "/board/reply",
			data: sendData,
			method: "post",
			success: function(jsonData) {
				if (jsonData.status == 1) {
					$("header>ul>li>a.list").trigger("click");
				} else {
					alert(jsonData.status);
				}
			},
			error: function(xhr) {
				alert(xhr.status);
			}
		})
		return false;

	});
	//--답글쓰기 버튼 클릭 끝

	//--첨부파일 클릭 시작--
	$('div.data>span.letters').on('click', 'span', function() {
		alert("첨부파일 다운 클릭");
		let href = backContextPath + '/board/download?fileName=' + fileName;
		window.location.href = href;
		return false;
	});

});

