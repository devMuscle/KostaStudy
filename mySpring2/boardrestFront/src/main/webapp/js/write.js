$(function () { //$(document).ready(function(){});와 같음
                //DOM트리가 완성되고 화면에 렌더링할 준비가 끝나면 
                //document에서 ready이벤트가 발생한다
				//그 이벤트가 발생했을때 처리할 콜백함수	
	//--이미지첨부파일 변경될때  미리보기 시작--
	$('section>div.write>form>div.data>input[name=imageFile]').change(function(){
		let file = this.files[0];
		$("div.image>img.preview").attr('src',URL.createObjectURL(file));
	});
	//--이미지첨부파일 변경될때  미리보기 끝--
	
	
	//--글쓰기 버튼 클릭 시작--
	let $btObj = $('div.write form input[type=button]');	
	$btObj.click(function () {
		let $writeFormObj = $("section>div.write form");
		let formData = new FormData($writeFormObj[0]);
		
		let obj = {};
		obj["fr"] = "bonjour";
		obj["en"] = "hello";
		obj["ko"] = "안녕하세요";
		//formData.append("greeting", new Blob([ JSON.stringify(obj) ], {type : "application/json"}));
		formData.append("greeting", JSON.stringify(obj));
		
		formData.forEach(function (value, key) {
			console.log(key + ":" + value);
		});
		
		let obj2 = formData.get("greeting");
		console.log(obj2);
		$.ajax({
			url: backContextPath + "/board/write",
			method: "post",
			processData: false, //파일업로드용 설정
			contentType: false, //파일업로드용 설정
			data: formData, //파일업로드용 설정
			
			cache:false, //이미지 다운로드용 설정
	        xhrFields:{  //이미지 다운로드용 설정
	            responseType: 'blob'
	        }, 
			success: function (responseData) {
				let $img = $('div.image>img.downloadview');
				let url = URL.createObjectURL(responseData);
				$img.attr('src', url); 
			},
			error: function (jqXHR, textStatus) {//응답실패
				alert("에러:" + jqXHR.status);
			}
		});
		return false;
	});
	//--글쓰기 버튼 클릭 끝--
});