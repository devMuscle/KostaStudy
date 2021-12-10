function loginClick() {
  let $loginFormObj = $("div.login>form");
  $loginFormObj.submit(function () {
    let ajaxUrl = $(this).attr("action");
    let ajaxMethod = $(this).attr("method");
    let idValue = $(this).find("input[name=id]").val();
    let pwdValue = $(this).find("input[name=pwd]").val();
    $.ajax({
      url: ajaxUrl,
      method: ajaxMethod,
      data: { id: idValue, pwd: pwdValue },
      success: function (responseObj) {
        if (responseObj.status == 0) {
          //로그인실패
          alert(responseObj.msg);
        } else {
          //로그인성공
          location.href = "./";
        }
      },
      error: function (xhr) {
        alert("응답실패 status:" + xhr.status);
      },
    });
    return false; //기본이벤트핸들러 금지 + 이벤트전파 중지
  });
}
