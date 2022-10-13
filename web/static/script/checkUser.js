// 获取用户名
var inp = document.querySelectorAll(".inp");
// 获取注册按钮
var btn = document.getElementsByClassName("btn")[0];
console.log(btn)
console.log(inp)
btn.onclick = function(e){
    var reg = /^[a-zA-Z0-9_@.-]{4,16}$/
    var rulePass = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/
    var email = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
    if(!reg.test(inp[0].value)){
        e.preventDefault()
        console.log("用户名格式错误")
    }
    if(!rulePass.test(inp[1].value)){
        e.preventDefault()
        console.log("密码格式错误")
    }
    if(inp[1].value != inp[2].value){
        e.preventDefault()
        console.log("密码不一致!")
    }

    if(!email.test(inp[3].value)){
        console.log(inp[3].value)
        e.preventDefault()
        console.log("邮箱不正确!")
    }
}