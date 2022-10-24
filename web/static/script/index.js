function page(pageNo){
    // 如果价格区间input框内容不为空,那么我点击时,需要将最小值,最大值和页码传过去,
    // 让后端调用查询全部图书方法时传值
    if(minPrice.value != false && maxPrice.value != false){
        window.location.href = "index?minPrice="+minPrice.value+"&maxPrice="+maxPrice.value+"&pageNo="+pageNo;
    }else{
        // 如果都为空,我只需要传页码,因为后端最小值0,和查询价格最大值的方法,已备好
        window.location.href = "index?pageNo=" + pageNo;
    }
}
// 获取输入页数的input框
var toPage = document.querySelector("#toPage");
// 获取确定去哪也的按钮
var toPageBtn = document.querySelector("#toPageBtn");
// 获取总页数的值
var pageCount = document.querySelector("#pageCount");
// 获取价格区间确认按钮
var btnPrice = document.querySelector("#btn_price");
// 获取价格区间的最大值和最小值
var minPrice = document.querySelector("[name='minPrice']");
var maxPrice = document.querySelector("[name='maxPrice']");
console.log(maxPrice)

btnPrice.onclick = function(){
    if(minPrice.value != false && maxPrice.value != false){
        window.location.href = "index?minPrice="+minPrice.value+"&maxPrice="+maxPrice.value;
    }else{
        window.location.href = "index?method=resetPrice";
    }
}

toPage.oninput = function(){
    var minVal = 1;
    var maxVal = Number(pageCount.value);
    var valPage = Number(toPage.value);
    if(valPage > maxVal || valPage < minVal){
        toPageBtn.setAttribute("disabled",true)
    }else {
        toPageBtn.removeAttribute("disabled")
    }
}

toPageBtn.onclick = function(){
    page(toPage.value);
}


// 获取当前li(页码)
var active = document.querySelector(".active");
var e = document.querySelector(".e");
// 让当前页码和其上下兄弟显示
active.style.display = "block";
if(active.innerText == "1"){
    active.nextElementSibling.style = "block";
    active.nextElementSibling.nextElementSibling.style = "block";
    active.nextElementSibling.nextElementSibling.nextElementSibling.style = "block";
    active.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.style = "block";
}else if(active.innerText == e.innerText){
    active.previousElementSibling.style = "block";
    active.previousElementSibling.previousElementSibling.style = "block";
    active.previousElementSibling.previousElementSibling.previousElementSibling.style = "block";
    active.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.style = "block";
}else if(active.innerText == "2"){
    active.previousElementSibling.style = "block";
    active.nextElementSibling.style = "block";
    active.nextElementSibling.nextElementSibling.style = "block";
    active.nextElementSibling.nextElementSibling.nextElementSibling.style = "block";
}else if(Number(active.innerText) == Number(e.innerText)-1){
    active.nextElementSibling.style = "block";
    active.previousElementSibling.style = "block";
    active.previousElementSibling.previousElementSibling.style = "block";
    active.previousElementSibling.previousElementSibling.previousElementSibling.style = "block";
}else{
    active.previousElementSibling.previousElementSibling.style = "block";
    active.previousElementSibling.style = "block";
    active.nextElementSibling.style = "block";
    active.nextElementSibling.nextElementSibling.style = "block";
}

// 点击加入购物车事件
function addCart(bookId){
    window.location.href = "cart?method=addCart&bookId=" + bookId;
}
