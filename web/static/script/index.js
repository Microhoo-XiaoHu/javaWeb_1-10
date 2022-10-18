function page(pageNo){
    window.location.href = "index?pageNo=" + pageNo;
}
// 获取输入页数的input框
var toPage = document.querySelector("#toPage");
// 获取确定去哪也的按钮
var toPageBtn = document.querySelector("#toPageBtn");
// 获取总页数的值
var pageCount = document.querySelector("#pageCount");

toPage.oninput = function(){

    var minVal = 1;
    var maxVal = pageCount.value;
    if(toPage.value > maxVal || toPage.value < minVal){
        toPageBtn.disabled = true;
    }else {
        toPageBtn.disabled = false;
    }
}
toPageBtn.onclick = function(){
    page(toPage.value);
}
