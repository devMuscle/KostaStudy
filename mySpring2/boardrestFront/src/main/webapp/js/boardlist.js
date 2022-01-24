//--게시글 목록 보여주기 함수 시작--
function showList(jsonData){
	let pageDTO = jsonData;	
	
	let url = pageDTO.url;
	let currentPage = pageDTO.currentPage;
	let totalCnt = pageDTO.totalCnt;
	let totalPage = pageDTO.totalPage;
	let startPage = pageDTO.startPage;
	let endPage = pageDTO.endPage;
	
	let list = pageDTO.list;
	//table tbody tr.row 원본객체만 제외하고 모두 삭제하기. 또는 table tbody tr.copy만 삭제하기
	//$("div.tbody>div.tr").not("div.row").remove();
	$("div.tbody>div.tr.copy").remove();
	
	//table tbody tr객체찾기
	let $trObj = $("div.tbody>div.tr.row");	
	$(list).each(function(index, element){
		let $trCopyObj = $trObj.clone();//복제본
		$trCopyObj.removeClass("row");
		$trCopyObj.addClass("copy");
		
		let levelStr = '';
		//&#10149;오른쪽화살표문자열
		for(let i=1; i<element.level; i++){
			levelStr += '&#10149';
		}
		let boardNo = element.boardNo;
		let boardTitle = element.boardTitle;
		let id = element.boardC.id;
		let boardViewcount = element.boardViewcount;
		let boardDt = element.boardDt;
		let boardDetail = element.boardDetail;
		$trCopyObj.find("div.no").html(levelStr + "<span>" + boardNo + "</span>");//게시글번호
		$trCopyObj.find("div.title").html(boardTitle);//게시글제목
		$trCopyObj.find("div.id").html(id);//작성자
		$trCopyObj.find("div.viewcount").html(boardViewcount);//조회수
		$trCopyObj.find("div.dt").html(boardDt);//작성일자
		$trCopyObj.find("div.detail").html(boardDetail);//게시글내용	
		$trCopyObj.show();
		$("div.tbody").append($trCopyObj); //복제본을 tbody에 추가
		
		//-- 이코드는 showList()함수 내부에서 게시글 목록보여주기 작업다음입니다.
		//--페이지그룹----
		let pageGroupHtml = "";
		if(startPage > 1){
			pageGroupHtml += '<span';
			pageGroupHtml += ' class="'+url+"/"+(startPage-1);
			pageGroupHtml += ' active';
			pageGroupHtml += '">';
			pageGroupHtml +=  'PREV'; 
			pageGroupHtml += '</span>&nbsp;&nbsp;';
		}
		for(let i=startPage; i<=endPage; i++){
			pageGroupHtml += '<span';
			pageGroupHtml += ' class="'+url+"/"+ i;
			if(i != currentPage){
				pageGroupHtml += ' active';
			}
			pageGroupHtml += '">';
			pageGroupHtml +=  i; 
			pageGroupHtml += '</span>&nbsp;&nbsp;';
		}
		if(endPage < totalPage){
			pageGroupHtml += '<span';
			pageGroupHtml += ' class="'+url+"/"+(endPage+1);
			pageGroupHtml += ' active';
			pageGroupHtml += '">';
			pageGroupHtml +=  'NEXT'; 
			pageGroupHtml += '</span>&nbsp;&nbsp;';
		}
		$('div.pagegroup').html(pageGroupHtml);
	});
}
//--게시글 목록 보여주기 함수 끝--

//--게시글 하나 클릭 시작--
$('div.boardlist>div.table').on('click', 'div.tr.copy', function () {
        
        var $trCurrentObj = $(this); //클릭이벤트가 발생된 현재행
        var $summaryObj = $trCurrentObj.find("div.summary"); //summary영역
        var $divDetail = $trCurrentObj.find('div.detail'); //글상세영역       
        if($divDetail.css('display')=='block'){
            $divDetail.css('display', 'none');
            $divDetail.empty();
            $summaryObj.css({"background-image": "url(./images/btn_arrow_down.png)"});
            $summaryObj.css({"background-position": "right center"});
            $summaryObj.css({"background-repeat": "no-repeat"});
		}else{
			 $divDetail.css('display', 'block');
            $summaryObj.css({"background-image": "url(./images/btn_arrow_up.png)"});
            $summaryObj.css({"background-position": "right center"});
            $summaryObj.css({"background-repeat": "no-repeat"});

            var no = $trCurrentObj.find('div.no>span').html(); //글번호찾기
            $divDetail.load(frontContextPath + "/boardinfo.html", 
                            function (){
				/*let jsonData = {
					boardNo : 1, 
		  			level : 1, //1-새글, 2-답글, 3-답글의답글, 4-답글의답글의 답글
		  			boardTitle: "게시글1", 
	      			boardC:{id:"id9", name:"오문정"}, 
	      			boardViewcount:10, 
	      			boardDt: "21/01/01", 
	      			boardContent:"게시글1의 내용"
				};
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
                }*/
				
				$.ajax({
					url : backContextPath + "/board/" + no,
					//data : "no=" + no, 
					method: 'get',
					success: function(jsonData){
						if(jsonData.status == 0){
							alert(jsonData.msg);
						}else{
							let repBoard = jsonData.repBoard;
							var $divInfo = $divDetail.find("div.info");
							$divInfo.find("div.data>span.no").html(repBoard.boardNo);
			                $divInfo.find("div.data>span.title").html(repBoard.boardTitle);
			                $divInfo.find("div.data>span.id").html(repBoard.boardC.id);
			                $divInfo.find("div.data>span.dt").html(repBoard.boardDt);
			                $divInfo.find("div.data>textarea.content").html(repBoard.boardContent);
			                $divInfo.find("div.data>span.viewcount").html(repBoard.boardViewcount);
			                $trCurrentObj.find("div.viewcount").html(repBoard.boardViewcount);
							if(loginedId == null){ //로그인안된 경우
								$divInfo.find("a.modify").hide();
			                    $divInfo.find("a.remove").hide();
 								$divInfo.find("a.reply").hide();
							}else if (repBoard.boardC.id != loginedId) { //로그인한 아이디가 쓴 글이 아니면 답글쓰기만 보여준다
						     	 $divInfo.find("div.data>textarea.content").attr("readonly", "readonly");
			                     $divInfo.find("a.modify").hide();
			                     $divInfo.find("a.remove").hide();
			                } else { //로그인한 아이디가 쓴 글이면 수정,삭제만 보여준다
			                     $divInfo.find("a.reply").hide();
			                }


							let letters = jsonData.letters;
							if(letters){
								let $spanLetters = $divInfo.find("div.data>span.letters");	
								let spanLettersHtml = "";
								$(letters).each(function(index, element){
			spanLettersHtml += '<span class="'+element+'">첨부'+(index+1) +'</span>&nbsp;';
								});
								$spanLetters.html(spanLettersHtml);
							};
							let image = jsonData.image;
							if(image){
								let $img = $divInfo.find("div.image>img");
								$.ajax({
									url: backContextPath + "/board/downloadimage", 
									method: "get",
									data: "imageFileName=" + image, 
									
									cache:false, //이미지 다운로드용 설정
							        xhrFields:{  //이미지 다운로드용 설정
							            responseType: 'blob'
							        }, 
									success: function (responseData) {
										let url = URL.createObjectURL(responseData);
										$img.attr('src', url); 
									},
									error: function (jqXHR) {//응답실패
										alert("에러:" + jqXHR.status);
									}
								});
							}
						}
					},
					error: function(xhr){
						alert(xhr.status);
					}
				});
			});
		}
		return false;
});
//--게시글 하나 클릭 끝--

//--페이지그룹중 페이지하나 클릭 시작--
$("div.pagegroup").on("click", "span.active",function(){
	let url = $(this).attr("class").split(/\s+/)[0]; //정규표현식 \s는 공백
	//alert(url);
	
	//table tbody tr.row 원본객체만 제외하고 모두 삭제하기. 또는 table tbody tr.copy만 삭제하기
	//$("div.tbody>div.tr").not("div.row").remove();
	//$("div.tbody>div.tr.copy").remove();
	
	$.ajax({
		url: backContextPath + url, //ex) /board/list/2
		method: 'get',
		success: function(jsonData){
			showList(jsonData);
		},
		error: function(xhr){
			alert(xhr.status);
		}
	});	
});
//--페이지그룹중 페이지하나 클릭 끝--

//--검색클릭 시작--
$("div.boardlist>div.search>div.searchInput>a").click(function () {
    var word = $("div.boardlist>div.search>div.searchInput>input[name=word]").val();
    var url = backContextPath + '/board/search';
    if (word.trim() != '') {
        url += '/' + word;
    }
    $.ajax({
        "method": "GET",
        "transformRequest": [
            null
        ],
        "transformResponse": [
            null
        ],
        "jsonpCallbackParam": "callback",
        "url": url,
        "headers": {
            "Accept": "application/json, text/plain, */*"
        },
        "timeout": {},
        success: function (jsonData) {
			showList(jsonData);
        }, error(jqXHR) {
            alert(jqXHR.status);
        }
    });
    return false;
});
//--검색 클릭 끝--


$(function(){
	$.ajax({
		url: backContextPath + '/board/list',
		method: 'get',
		success: function(jsonData){
			showList(jsonData);
		},
		error: function(xhr){
			alert(xhr.status);
		}
	});	
});

