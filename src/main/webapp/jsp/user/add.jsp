<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加</title>
</head>
<script>
    $(function () {
        $.ajax({
            url: "/dept/listAll",
            type: "get",
            data: "",
            dataType: "json",
            success: function (data) {
                var html='<option value="-1">请选择</option>';
                for(var i=0; i< data.length ;i++){
                    html+='<option value="'+data[i].id+'">'+data[i].name+'</option>';
                }
                $("#deptId").append(html);
            }
        });
    });
    
    function checkUserName() {
        var name=$("#username").val();
        $.ajax({
            url:"/user/getUserByUserName",
            data:{"userName":name},
            type:"get",
            dataType:"text",
            success:function (data) {
                if(data==0){
                    $("#username").val("");
                    $("#span").attr("hidden",false);
                }else {
                    $("#span").attr("hidden",true);
                }
            }
        });
    }

</script>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>
<div id="right">
    <form action="/user/add" method="post">
        账号：<input type="text" name="username" id="username" value="" onblur="checkUserName()"/>
        <span id="span" style="color: red" hidden>账号存在，请重新输入</span><br/><br/>
        密码：<input type="password" name="password" value=""/><br/><br/>
        邮箱：<input type="text" name="email" value=""/><br/><br/>
        姓名：<input type="text" name="realName" value=""/><br/><br/>
        年龄：<input type="text" name="age" value=""/><br/><br/>
        性别：<input type="radio" name="gender" value="1"/>男
              <input type="radio" name="gender" value="0"/>女<br/><br/>
        简介：<textarea name="description"></textarea><br/><br/>
        部门：<select id="deptId" name="deptId">
            </select><br/><br/>
        <input type="submit" value="保存"/>
        <input type="reset" value="重置"/>
    </form>
</div>
</body>
</html>
