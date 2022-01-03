/**
 * 주문하기 버튼 클릭
 */
function addOrderClick(){
    $('button.addorder').click(function(){
        let ajaxUrl = "./addorder";
        $.ajax({
            url: ajaxUrl,
            success:function(responseObj){
                if(responseObj.status == 0){//주문실패
                    alert(responseObj.msg);
                }else{//주문성공
                    //location.href="./selector.jsp"; //메인화면요청
                    //$('header>nav>ul>li>a[href=productlist]').trigger('click');//상품목록보기
                    //주문목록보기
                    $('header>nav>ul>li>a[href=orderlist]').trigger('click');
                }
            }
        });
        return false;
    });
}