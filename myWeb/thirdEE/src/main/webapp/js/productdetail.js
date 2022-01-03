/**
 * 장바구니 넣기 버튼 클릭
 */
function putCartClick(){
    let $formObj = $('div.productdetail>div.productdetail_info>form');
    $formObj.submit(function(){
        let ajaxUrl = "./putcart";
        let method = "get";
        let data = $(this).serialize(); //prodNo=XXX&quantity=YYY
        $.ajax({
            url: ajaxUrl,
            method: method,
            data: data,
            success:function(){
                $('div.productdetail>div.productdetail_info>div.modal').show();
            },
            error:function(xhr){
                alert('응답실패:' + xhr.status);
            }
        });
        return false;
    });
}

/**
 * 모달div의 장바구니 보기 버튼 클릭
 */
function cartListClick(){
    $('div.productdetail>div.productdetail_info>div.modal>button.cartlist').click(function(){
        //메뉴중 장바구니객체를 DOM트리에서 찾기
        let $menuCartlistObj = $('header>nav>ul>li>a[href=cartlist]');
        //alert($menuCartlistObj.attr("href"));
        $menuCartlistObj.trigger('click');
        return false;	
    });
}

function productListClick(){
    $('div.productdetail>div.productdetail_info>div.modal>button.productlist').click(function(){
        //메뉴중 상품객체를 DOM트리에서 찾기
        let $menuProductlistObj = $('header>nav>ul>li>a[href=productlist]');
        $menuProductlistObj.trigger('click');
        return false;	
    });
}