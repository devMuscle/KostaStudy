/**
 * 상품상세 클릭
 */
function productDetail(){
    let $divObj = $('div.productlist>div');
    $divObj.click(function(){
        /* console.log(this); */
        let prodNo = $(this).attr('id');
        let ajaxUrl = './productdetail';
        /* $.ajax({
            url: ajaxUrl,
            method : 'get',
            data : {prodNo: prodNo},
            success:function(responseData){
                let $articlesObj = $('section>div.articles');
                $articlesObj.empty();
                $articlesObj.html(responseData);
            }
        }); */
        $('section>div.articles').load('./productdetail?prodNo='+prodNo);
        return false;
    });
}