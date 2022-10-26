// 点击加减按钮
function updateBuyCount(id,buyCount){
    window.location.href = "cart?method=updateBuyCount&id="+id+"&buyCount="+buyCount;
}
// 输入购物数量
var inpts = document.querySelectorAll(".inp");
for (var i = 0; i < inpts.length; i++) {
    (function (m) {
        var newVar = inpts[m].value
        inpts[m].onblur = function(){
            // inpts[i].index=i
            // 删掉重写,input为空的话是0
            //0 - Number(inpts[m].value) <= -1
            if(Number(inpts[m].value) > 0){
                updateBuyCount(inpts[m].name,inpts[m].value)
            }else{
                alert("请输入一个大于0的数字!")
                inpts[m].value = newVar
            }
        }
    })(i);
}


// 如果购物车是空的,那么去结账按钮不能点击
//获取到所有tr
var cartTr = document.querySelectorAll(".cart_tr");
// 获取去结账按钮
var checkout = document.querySelector(".checkout");
checkout.onclick = function(){
    if(cartTr.length == 0){
        alert("您的购物车无商品!!!")
    }else{
        window.location.href = "order?method=checkout"
    }
}
