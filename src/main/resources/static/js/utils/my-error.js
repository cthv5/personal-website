function errorHandle(data) {
    var msg = data.message;
    console.log(msg);
    if (msg.indexOf("token") > 0) {
        alert("登陆失效,请重新登录...");
        window.location.href='/page/login/login.html';
    } else {
        window.location.href='/page/error/error.html';
    }
}