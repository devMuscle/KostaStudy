$(function() {

	//ajax요청으로 응답된 JSON결과
	//JSON구성 {},[]
	//level 1-새글, 2-답글, 3-답글의 답글, 4-답글의답글의 답글

	//--게시글 목록 보여주기 함수 시작--//
	function showList(jsonData) {
		let pageDTO = jsonData;

		let url = pageDTO.url;
		let currentPage = pageDTO.currentPage;
		let totalCnt = pageDTO.totalCnt;
		let totalPage = pageDTO.totalPage;
		let startPage = pageDTO.startPage;
		let endPage = pageDTO.endPage;

		let list = pageDTO.list;
		//table tbody tr객체찾기
		let $trObj = $("div.tbody>div.tr.row");
		$(list).each(function(index, element) {
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

		//밑에 페이지 번호 보여주기 시작
		let pageGroupHtml = "";
		let j;

		for (j = startPage; j <= endPage; j++) {
			pageGroupHtml += '<a class=' + backContextPath + '/board/list/' + j;
			pageGroupHtml += ' name=pg>' + j + ' </a>';
		}

		if (j <= totalPage) {
			pageGroupHtml += '<a class=' + backContextPath + '/board/list/' + j;
			pageGroupHtml += ' name=pg>next</a>';
		}

		$pgObj = $("div.pagegroup");
		$pgObj.html(pageGroupHtml);
		//밑에 페이지 번호 보여주기 끝
	}
	
	//게시글 페이지 번호 클릭 이벤트 시작
	$('div.pagegroup').on('click', 'a', function() {
		$("div.tbody>div.tr.copy").remove();

		$.ajax({
			url: $(this).attr('class'),
			method: "get",
			success: function(jsonData) {
				showList(jsonData);
			},
			error: function(xhr) {
				alert(xhr.status);
			}
		});
	});
	//게시글 페이지 번호 클릭 이벤트 끝

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
					$.ajax({
						url: backContextPath + "/board/" + no,
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
	
	//--통합 검색 클릭 이벤트 시작--
	$("a.searchBtn").click(function(){
		let keyword = $("#word").val();
		alert(keyword);
	})

});