// 获取用户名
var inp = document.querySelectorAll(".inp");
// 获取注册按钮
var btn = document.getElementsByClassName("btn")[0];
// 获取刷新验证码按钮
var button = document.querySelector(".button");
// 获取验证码图片
var img = document.querySelector(".img");
// 获取验证错误提示信息
var errMess = document.querySelectorAll(".errMess");



// 验证码字符
var chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

// 生成m-n的数字
function getRandom(m,n){
    return Math.floor(Math.random()*(n-m+1)+m);
}
// 验证码禁用文本选中
img.addEventListener("selectstart",function(e){
    e.preventDefault();
})
// 生成随机验证码
function run(){
    str = '';
    // 若验证码存在，则清除
    while(img.hasChildNodes()){
        img.removeChild(img.firstChild);
    }
    // 生成6位数的验证码
    for(var i=0;i<6;i++){
        var span = document.createElement('span');
        span.innerHTML = chars[getRandom(0,chars.length-1)]; //生成随机数，并取得对应值
        span.style.display = "inline-block";
        span.style.fontSize = getRandom(16,24)+"px";  //随机字体大小
        span.style.color = 'rgb('+getRandom(0,200)+','+getRandom(0,200)+','+getRandom(0,200)+')';  //随机字体颜色
        span.style.transform = 'translate('+getRandom(-5,5)+'px,'+getRandom(-5,5)+'px) rotate('+getRandom(-20,20)+'deg)'; //随机平移旋转
        str += span.innerHTML; //将str拼接，和input值对比
        img.appendChild(span);
    }
    return str;
}
var str = run(); //进入页面生成验证码
img.addEventListener("click",run);

button.addEventListener("click",function(e){
    str = run();
    e.preventDefault();
})


btn.onclick = function(e){
    var reg = /^[a-zA-Z0-9_@.-]{6,16}$/
    var rulePass = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/
    var email = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
    // 如果输入内容不满足条件则
    if(!reg.test(inp[0].value)){
        // 输入不对,显示提示
        errMess[0].style.visibility = "visible"
        // 阻止注册默认行为
        e.preventDefault()
        console.log("用户名格式错误")
    }else{
        errMess[0].style.visibility = "hidden"
    }

    if(!rulePass.test(inp[1].value)){
        errMess[1].style.visibility = "visible"
        e.preventDefault()
        console.log("密码格式错误")
    }else{
        errMess[1].style.visibility = "hidden"
    }

    if(inp[1].value != inp[2].value){
        errMess[2].style.visibility = "visible"
        e.preventDefault()
        console.log("密码不一致!")
    }else{
        errMess[2].style.visibility = "hidden"
    }

    if(!email.test(inp[3].value)){
        errMess[3].style.visibility = "visible"
        console.log(inp[3].value)
        e.preventDefault()
        console.log("邮箱不正确!")
    }else{
        errMess[3].style.visibility = "hidden"
    }

    if(inp[4].value.toLowerCase() != str.toLowerCase()){ // 输入的验证码转小写进行比较
        errMess[4].style.visibility = "visible"
        console.log("验证码错误!!")
        str = run();
        e.preventDefault()
    }else{
        errMess[4].style.visibility = "hidden"
    }
}