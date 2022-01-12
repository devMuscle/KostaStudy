$(function() {
	//ajax요청으로 응답된 JSON결과
	//JSON구성 {},[]
	//level 1-새글, 2-답글, 3-답글의 답글, 4-답글의답글의 답글

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
			let $trCopyObj = $trObj.clone(); //복제본
			$trCopyObj.removeClass("row");
			$trCopyObj.addClass("copy");

			let levelStr = "";
			//&#10145; 오른쪽화살표
			for (let i = 1; i < element.level; i++) {
				levelStr += "&#10145;";
			}

			let boardNo = element.boardNo;
			let boardTitle = element.boardTitle;
			let boardId = element.boardC.id;
			let boardViewCount = element.boardViewCount;
			let boardDt = element.boardDt;
			let boardDetail = element.boardDetail;

			$trCopyObj.find("div.no").html(levelStr + "<span>" + boardNo + "</span>");
			$trCopyObj.find("div.title").html(boardTitle);
			$trCopyObj.find("div.id").html(boardId);
			$trCopyObj.find("div.viewcount").html(boardViewCount);
			$trCopyObj.find("div.dt").html(boardDt);

			$trCopyObj.find("div.detail").html(boardDetail);

			$trCopyObj.show();
			$("div.tbody").append($trCopyObj);
		});
	}

	$.ajax({
		url: backContextPath + '/board/list',
		method: "get",
		succes: function(jsonData) {
			showList(jsonData);
		},
		error: function(xhr) {
			alert(xhr.status);
		}
	});
});