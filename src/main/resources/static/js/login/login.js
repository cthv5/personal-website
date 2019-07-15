$(function () {
    $('#my-login').click(function () {
        login();
    });
    reset();
});
function login() {
    var user_name = $('#my-user-name').val();
    var password = $('#my-user-password').val();
    if (String(user_name).trim().length === 0) {
        alert("用户名为空");
    } else if (String(password).trim().length === 0) {
        alert("密码为空");
    } else {
        password = hex_sha1(password);
        $.ajax({
            url : "/v1/login",
            type : "GET",
            data:{
                "userName":user_name,
                "pwd":password
            },
            dataType : "json",
            success : function(data) {
                if (data.returnCode == 0) {
                    window.location.href='/page/timeline.html';
                } else {
                    alert(data.message);
                }
            }
        });
    }
}
function isPc() {
    var flag = true;
    var userAgentInfo = navigator.userAgent;
    console.log(userAgentInfo);
    var Agents = ["Android", "iPhone",
        "SymbianOS", "Windows Phone",
        "iPad", "iPod"];
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}
function reset() {
    var winWidth = window.innerWidth;
    if (winWidth < 848) {
        console.log("reset login size");
        $('#login').css({
            'width':'50%',
            'left':'25%'
        });
    }
}
