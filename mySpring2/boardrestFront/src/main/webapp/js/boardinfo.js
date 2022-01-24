$(function(){
	
	$('div.info').click(function(){
		return false;
	});
	
	$('div.reply').click(function(){
		return false;
	});
	
	
	//--답글쓰기 링크 클릭 시작--
	$("div.info>a.reply").click(function(){
		$("div.reply").show();
		return false;
	});
	//--답글쓰기 링크 클릭 끝--
	
	//--수정 링크 클릭 시작--
	$("div.info>a.modify").click(function(){
		let $infoObj = $(this).parent(); //a객체의 부모객체
		
		let boardNo = $infoObj.find('div.data>span.no').html(); //글번호
		let boardContent = $infoObj.find('div.data>textarea.content').val(); //글내용
		let sendData = {};
		sendData['boardNo'] = boardNo;
		sendData['boardContent'] = boardContent;
		
		/*let jsonData = {status : 1, msg: '성공'};
		//let jsonData = {status : 0, msg: '수정실패'};
		if(jsonData.status == 1){ //수정 성공
			$('header>ul>li>a.list').trigger('click'); //게시판 목록
		}else{ //수정실패
			alert(jsonData.msg);
		}*/
		
		/*$.ajax({
			url : backContextPath + "/board/modify",
			data: sendData,
			method : "post", 
			success: function(jsonData){
				if(jsonData.status == 1){ //수정 성공
					$('header>ul>li>a.list').trigger('click'); //게시판 목록
				}else{ //수정실패
					alert(jsonData.msg);
				}
			},error:function(xhr){
				alert(xhr.status);
			}
		});*/
		//js객체를 json문자열로 변환
		let data = JSON.stringify(sendData);
		$.ajax({
			  "url": backContextPath + "/board/" + boardNo,
			  "method": "PUT",
			  "transformRequest": [
			    null
			  ],
			  "transformResponse": [
			    null
			  ],
			  "jsonpCallbackParam": "callback",
			  
			  "headers": {
			    "Accept": "application/json, text/plain, */*",
			    "Content-Type": "application/json;charset=utf-8"
			  },
			  "data": data, //JSON문자열
			  "timeout": {},
			  success: function(jsonData){
				if(jsonData.status == 1){ //수정 성공
					$('header>ul>li>a.list').trigger('click'); //게시판 목록
				}else{ //수정실패
					alert(jsonData.msg);
				}
			  },error:function(xhr){
				alert(xhr.status);
			  }
		});
		
		
		
		return false;
	});
	//--수정 링크 클릭 끝--
	
	//--삭제 링크 클릭 시작--
	$('div.info>a.remove').click(function(){
		let $infoObj = $(this).parent(); //a객체의 부모객체		
		let boardNo = $infoObj.find('div.data>span.no').html(); //글번호
		/*//let jsonData = {status : 1, msg: '성공'};
		let jsonData = {status : 0, msg: '삭제실패'};
		if(jsonData.status == 1){ //삭제 성공
			$('header>ul>li>a.list').trigger('click'); //게시판 목록
		}else{ //삭제실패
			alert(jsonData.msg);
		}*/
		
		/*$.ajax({
			url: backContextPath + "/board/remove",
			data : "boardNo=" + boardNo,
			success:function(jsonData){
				if(jsonData.status == 1){
					$('header>ul>li>a.list').trigger('click'); //게시판 목록
				}else{
					alert(jsonData.msg);
				}
			},error:function(xhr){
				alert(xhr.status);
			}
		});
		*/
		$.ajax({
			"method": "DELETE",
			"transformRequest": [
			  null
			],
			"transformResponse": [
			  null
			],
			"jsonpCallbackParam": "callback",
			"url":  backContextPath + "/board/" + boardNo,
			"headers": {
			  "Accept": "application/json, text/plain, */*"
			},
			"data": "",
			"timeout": {},
			success:function(jsonData){
				if(jsonData.status == 1){
					$('header>ul>li>a.list').trigger('click'); //게시판 목록
				}else{
					alert(jsonData.msg);
				}
			},error:function(xhr){
				alert(xhr.status);
			}
		});
		return false;
		
	});
	//--삭제 링크 클릭 끝--
	
	
	//--답글쓰기 창(div.reply)에서 답글쓰기버튼 클릭 시작--
	let $replyBtObj = $('div.reply>form>input[type=button]');
	$replyBtObj.click(function(){
		let parentNo = $('div.info>div.data>span.no').html();//글번호가 부모글번호역할
		let formData = $('div.reply>form').serialize();
		//답글제목		//답글내용
	//	let sendData = "parentNo=" + parentNo + "&" + formData;
	/*	//let jsonData = {status : 1, msg: '성공'};
		let jsonData = {status : 0, msg: '답글실패'};
		if(jsonData.status == 1){ //답글 성공
			$('header>ul>li>a.list').trigger('click'); //게시판 목록
		}else{ //답글실패
			alert(jsonData.msg);
		}*/
		
		/*$.ajax({
			url: backContextPath + '/board/reply',
			method: "post",
			data : sendData,
			success:function(jsonData){
				if(jsonData.status === 1){
					$('header>ul>li>a.list').trigger('click'); //게시판 목록
				}else{
					alert("답글결과:" + jsonData.msg);
				}
			},error:function(xhr){
				alert(xhr.status);
			}
		});*/
		let formDataArray = $('div.reply>form').serializeArray(); //serialize()
		//console.log(formDataArray); //[{name:"boardTitle", value:"답글제목"},
		                            // {name:"boardContent", value:"답글내용"},
                                    //{},...
                                    //]
		let sendData = {};
		for(let index=0; index<formDataArray.length; index++){
			sendData[formDataArray[index]['name']] = formDataArray[index]['value'];
		}
		sendData["parentNo"] = parentNo;
		let data = JSON.stringify(sendData);
		console.log(data);
		$.ajax({
			url: backContextPath + '/board/reply',
			//data : sendData,
			data : data,
			method : 'post',
			"transformRequest": [
			    null
			],
			"transformResponse": [
			    null
			],
			"jsonpCallbackParam": "callback",
  			"headers": {
				"Accept": "application/json, text/plain, */*",
				"Content-Type": "application/json;charset=utf-8"
  			},
  			"timeout": {},
			success:function(jsonData){
				if(jsonData.status === 1){
					$('header>ul>li>a.list').trigger('click'); //게시판 목록
				}else{
					alert(jsonData.msg);
				}
			},error:function(xhr){
				alert(xhr.status);
			}
		});
		return false;
	});
	//--답글쓰기 창(div.reply)에서 답글쓰기버튼 클릭 끝-- 
	
	//--첨부파일 클릭 시작--
	$('div.data>span.letters').on('click', 'span', function(){
		let fileName = $(this).attr('class');
		let href = backContextPath + '/board/download?fileName='+fileName;
		window.location.href=href;
		return false;
	});
	//--첨부파일 클릭 끝--
	
	
	
});