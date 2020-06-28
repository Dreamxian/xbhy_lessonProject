<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form action="/login/login" method="post">
        账号：<input type="text" value=" " name="username"/><br/><br/>
        密码：<input type="password" name="password"/><br/><br/>
        记住我：<input type="checkbox" name="remember" value="1"/><br/><br/>
        <input type="submit" value="登录" name="">
        <a href="/jsp/login/forget.jsp">忘记密码</a>
    </form>

</body>
</html>

