$(function () {
    //用户名获得焦点时
    $("input[name='userAccount']").focus(function () {
        $("input[name='userAccount']").val("");
    });
    //用户名失去焦点时
    $("input[name='userAccount']").blur(function () {
        if ($(this).val() == "") {
            $("input[name='userAccount']").val("请输入用户名");
        }

    })
    //密码获得焦点时
    $("input[name='userPassword']").focus(function () {
        $("input[name='userPassword']").val("");
        $(this).attr("type", "password");
    });
    //密码失去焦点时
    $("input[name='userPassword']").blur(function () {
        if ($(this).val() == "") {
            $("input[name='userPassword']").val("请输入密码");
            $(this).attr("type", "text");
        }
    })
    //验证码获得焦点时
    $("input[name='yz']").focus(function () {
        $("input[name='yz']").val("");
    });
    //验证码失去焦点时
    $("input[name='yz']").blur(function () {
        if ($(this).val() == "") {
            $("input[name='yz']").val("请输入验证码");
        }
    });

    //tip出现时和未出现时，动态调节一下高度
    if ($(".tips").hasClass("undis")) {
        //alert($(".tips").hasClass("undis"));
        var h = 305;
        $(".login_bg").height(h);
    } else {
        var h2 = 345;
        $(".login_bg").height(h2);
    }

    $("#subButton").click(function () {
        $("#subLogin").submit();
    });

    $("#index_code").bind('keypress', function (event) {
        if (event.keyCode == "13") {
            $("#subLogin").submit();
        }
    });

})

