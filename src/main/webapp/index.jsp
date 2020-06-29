<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<script src="${path}/static/js/jquery.js"></script>
<script>
    function changeImg() {
        $("#img").attr("src","/img/getCode?nocade="+new Date().getTime());
    }
</script>
<body>
    <form action="/login/login" method="post">
        账号：<input type="text" value=" " name="username"/><br/><br/>
        密码：<input type="text" name="password"/><br/><br/>
        验证码：<input type="text" value="" name="code"/>
        <img src="img/getCode" id="img" onclick="changeImg()"/><br/><br/>
        记住我：<input type="checkbox" name="remember" value="1"/><br/><br/>
        <input type="submit" value="登录" name="">
        <a href="/jsp/login/forget.jsp">忘记密码</a>
    </form>
</body>
</html>

