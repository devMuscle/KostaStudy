/**
 *
 */
function packagePut() {
  let $formObj = $("div.productdetail>div.productdetail_info>form");
  $formObj.submit(function () {
    let ajaxUrl = "./putcart";
    let method = "get";
    let data = $(this).serialize(); //prodNo=XXX&quantity=YYY
    $.ajax({
      url: ajaxUrl,
      method: method,
      data: data,
      success: function () {
        $("div.productdetail>div.productdetail_info>div.modal").show();
      },
      error: function (xhr) {
        alert("응답실패" + xhr.status);
      },
    });
    return false;
  });
}

function packageSee() {
  $("div.productdetail>div.productdetail_info>div.modal>button.cartlist").click(
    function () {
      //메뉴중 장바구니객체를 DOM트리에서 찾기
      let $menuCartlistObj = $("header>nav>ul>li>a[href=cartlist]");
      //alert($menuCartlistObj.attr("href"));
      $menuCartlistObj.trigger("click");
      return false;
    }
  );
}

function productClick() {
  $(
    "div.productdetail>div.productdetail_info>div.modal>button.productlist"
  ).click(function () {
    //메뉴중 장바구니객체를 DOM트리에서 찾기
    let $menuCartlistObj = $("header>nav>ul>li>a[href=productlist]");
    //alert($menuCartlistObj.attr("href"));
    $menuCartlistObj.trigger("click");
    return false;
  });
}
