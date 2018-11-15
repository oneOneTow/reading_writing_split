<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>测试页面4</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript" src="/statics/js/app/common.js"></script>
    <script type="text/javascript" src="/statics/js/app/login.js"></script>
    <script type="text/javascript" src="/statics/js/app/model.js"></script>
    <script type="text/javascript" src="/statics/js/app/register.js"></script>
</head>
<body>
<div>
    <input type="text" id="phone" value="输入phone"/><br>
    <input type="text" id="password" value="输入password"/><br>
    <input type="button" value="sign up" onclick="redirectToReg()"/>
    <input type="button" value="sign in" onclick="login()" />
</div>
</body>

</html>