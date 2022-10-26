function page(pageNo){
    window.location.href = "orderManager?method=orderManager&pageNo=" + pageNo;
}
// 获取输入页数的input框
var toPage = document.querySelector("#toPage");
// 获取确定去哪也的按钮
var toPageBtn = document.querySelector("#toPageBtn");
// 获取总页数的值
var pageCount = document.querySelector("#pageCount");

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
