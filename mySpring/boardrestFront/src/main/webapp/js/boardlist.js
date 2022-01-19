$(function() {

	//ajax요청으로 응답된 JSON결과
	//JSON구성 {},[]
	//level 1-새글, 2-답글, 3-답글의 답글, 4-답글의답글의 답글

	//--게시글 목록 보여주기 함수 시작--//
	function showList(jsonData) {
		/*
		let jsonData =
			[{ boardNo: 1, level: 1, boardTitle: "게시글1", boardC: { id: "id1", name: "오문정" }, boardViewCount: 10, boardDt: "21/01/01", boardDetail: "게시글1의 내용" },
			{ boardNo: 2, level : 2, boardTitle: "re-1", boardC: { id: "id2", name: "잉옹앙" }, boardViewCount: 10, boardDt: "21/01/02", boardDetail: "게시글2의 내용" },
			{ boardNo: 4, level: 3, boardTitle: "re-re-1", boardC: { id: "id3", name: "핵펀치" }, boardViewCount: 10, boardDt: "21/01/04", boardDetail: "게시글4의 내용" },
			{ boardNo: 3, level: 1, boardTitle: "게시글3", boardC: { id: "id3", name: "김감자" }, boardViewCount: 10, boardDt: "21/01/03", boardDetail: "게시글3의 내용" }	];
		*/

		//table tbody tr객체찾기
		let $trObj = $("div.tbody>div.tr.row");
		$(jsonData).each(function(index, element) {
			let $trCopyObj = $trObj.clone();//복제본
			$trCopyObj.removeClass("row");
			$trCopyObj.addClass("copy");

			let levelStr = '';
			//&#10149;오른쪽화살표문자열
			for (let i = 1; i < element.level; i++) {
				levelStr += '&#10149';
			}
			let boardNo = element.boardNo;
			let boardTitle = element.boardTitle;
			let id = element.boardC.id;
			let boardViewCount = element.boardViewcount;
			let boardDt = element.boardDt;
			let boardDetail = element.boardDetail;
			$trCopyObj.find("div.no").html(levelStr + "<span>" + boardNo + "</span>");//게시글번호
			$trCopyObj.find("div.title").html(boardTitle);//게시글제목
			$trCopyObj.find("div.id").html(id);//작성자
			$trCopyObj.find("div.viewcount").html(boardViewCount);//조회수
			$trCopyObj.find("div.dt").html(boardDt);//작성일자
			$trCopyObj.find("div.detail").html(boardDetail);//게시글내용	
			$trCopyObj.show();

			$("div.tbody").append($trCopyObj); //복제본을 tbody에 추가
		});
	}
	//--게시글 목록 보여주기 함수 끝--//

	$.ajax({
		url: backContextPath + '/board/list',
		method: "get",
		success: function(jsonData) {
			showList(jsonData);
		},
		error: function(xhr) {
			alert(xhr.status);
		}
	});


	//--게시글 하나 클릭 이벤트 시작--//
	$('div.boardlist>div.table').on('click', 'div.tr.copy', function() {
		//console.log($(this));
		var $trCurrentObj = $(this); //클릭이벤트가 발생된 현재행
		var $summaryObj = $trCurrentObj.find("div.summary"); //summary영역
		var $divDetail = $trCurrentObj.find('div.detail'); //글상세영역       
		if ($divDetail.css('display') == 'block') {
			$divDetail.css('display', 'none');
			$divDetail.empty();
			$summaryObj.css({ "background-image": "url(./images/btn_arrow_down.png)" });
			$summaryObj.css({ "background-position": "right center" });
			$summaryObj.css({ "background-repeat": "no-repeat" });
		} else {
			$divDetail.css('display', 'block');
			$summaryObj.css({ "background-image": "url(./images/btn_arrow_up.png)" });
			$summaryObj.css({ "background-position": "right center" });
			$summaryObj.css({ "background-repeat": "no-repeat" });

			var no = $trCurrentObj.find('div.no>span').html(); //글번호찾기
			$divDetail.load(frontContextPath + "/boardinfo.html",
				function() {
					//					let jsonData = { boardNo: 1, level: 1, boardTitle: "게시글1", boardC: { id: "id9", name: "오문정" }, boardViewcount: 10, boardDt: "21/01/01", boardContent: "게시글1의 내용" };

					$.ajax({
						url: backContextPath + "/board/"+no,
						//data: "no=" + no,
						method: "get",
						success: function(jsonData) {
							var $divInfo = $divDetail.find("div.info");
							$divInfo.find("div.data>span.no").html(jsonData.boardNo);
							$divInfo.find("div.data>span.title").html(jsonData.boardTitle);
							$divInfo.find("div.data>span.id").html(jsonData.boardC.id);
							$divInfo.find("div.data>span.dt").html(jsonData.boardDt);
							$divInfo.find("div.data>textarea.content").html(jsonData.boardContent);
							$divInfo.find("div.data>span.viewcount").html(jsonData.boardViewcount);
							$trCurrentObj.find("div.viewcount").html(jsonData.boardViewcount);

							if (jsonData.boardC.id != loginedId) { //로그인한 아이디가 쓴 글이 아니면 답글쓰기만 보여준다
								$divInfo.find("div.data>textarea.content").attr("readonly", "readonly");
								$divInfo.find("a.modify").hide();
								$divInfo.find("a.remove").hide();
							} else { //로그인한 아이디가 쓴 글이면 수정,삭제만 보여준다
								$divInfo.find("a.reply").hide();
							}
						},
						error: function(xhr) {
							alert(xhr.status);
						}
					});
				});
		}
	});
	//--게시글 하나 클릭 이벤트 끝--//

});