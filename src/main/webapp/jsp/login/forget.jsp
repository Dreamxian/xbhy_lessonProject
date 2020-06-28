<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码</title>
</head>
<script>
    function sendEmail() {
        $.ajax({
            url:"/email",
            type:"get",
            data:{"email":$("#email").val()},
            dataType:"text",
            success:function (data) {
                if(data==1){
                    alert("发送成功");
                }else {
                    alert("发送失败");
                }
            }
        })
    }

</script>
<body>
    <form action="/login/forget" method="post">
        用户名：<input type="text" value=" " name="username"/><br/><br/>
        新密码：<input type="text" name="newPs" value=""/><br/><br/>
        邮箱：<input type="text" name="email" value="" id="email"/>
        <input onclick="sendEmail()" type="button" value="发送验证码" name="" class="btn btn-primary"/><br/><br/>
        验证码：<input type="text" name="code"/><br/><br/>
        <input type="submit" value="修改" name="">
    </form>

</body>
</html>

