<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>测试页面4</title>
    <script type="text/javascript">
        function login() {
            var phone=document.getElementById("phone").value;
            var password=document.getElementById("password").value;
            $.ajax({
                url:"/user/login.do",
                type:"post",
                async:true,
                dataType:'json',
                data:{
                    "phone":"'"+phone+"'",
                    "password":"'"+password+"'"
                },
                success:function(data){
                    console.log(data)
                }
            })
        }
        function register() {
            window.location.href="/index.jsp";
        }
    </script>

</head>
<body>
<div>
    <input type="text" id="phone" value="输入phone"/><br>
    <input type="text" id="password" value="输入password"/><br>
    <input type="button" value="sign up" onclick="register()"/>
    <input type="button" value="sign in" onclick="login()" />
</div>
</body>

</html>