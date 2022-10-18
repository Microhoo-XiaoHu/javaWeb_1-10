// 获取登录按钮
var subBtn = document.querySelector("#sub_btn");
// 获取账号
var username = document.querySelector("#username");
// 获取密码
var password = document.querySelector("#password");
// 获取注销
var loginError = document.querySelector(".loginError");

function selectThis(name,password) {
    document.write("<form action=\"/UserServlet?user=login_success\" method=post name=form1 style='display:none'>");
    document.write("<input type=hidden name=username value='"+name+"'/>");
    document.write("<input type=hidden name=password value='"+password+"'/>");
    document.write("</form>");
    document.form1.submit();
}

subBtn.onclick = function(){
    var name = username.value;
    var word = password.value;
    var url = "/UserServlet?user=login_success&username=" + name + "&password=" + word;
    axios.post(url).then(resp => {
        if(resp.data == 0){
            loginError.style.visibility = "visible"
        }else {
            selectThis(name,word)
        }
    }).catch(err => {
        console.log(err)
    })
}