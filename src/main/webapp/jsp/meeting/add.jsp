<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布会议</title>
</head>
<script>
    $(function () {
        $.ajax({
            url: "/dept/listAll",
            type: "get",
            data: {},
            dataType: "json",
            success: function (data) {
                var html = '<option>请选择</option>';
                for (var i = 0; i < data.length; i++) {
                    html = html + '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                }
                $("#deptId").append(html);
                //刷新页面ui
                $("#deptId").selectpicker("refresh");
            }
        });
        //根据部门id查询该部门下的所有用户
        $("#deptId").change(function () {
            $("#makeUsers").empty();
            var deptId = $(this).val();
            if (deptId == null) {
                return false;
            }
            $.ajax({
                url: "/user/findUserByDeptId",
                type: "get",
                data: {deptId: deptId},
                dataType: "json",
                success: function (data) {
                    var html = '<option>请选择</option>';
                    for (var i = 0; i < data.length; i++) {
                        html = html + '<option value="' + data[i].id + '">' + data[i].realName + '</option>';
                    }
                    $("#makeUsers").append(html);
                    //刷新页面ui
                    $("#makeUsers").selectpicker("refresh");
                }
            });
        });
    });
</script>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>

<div id="right">
    <form action="/meeting/addMeeting" method="post">
        标题：<input type="text" name="title"><br><br>
        选择部门：
        <select id="deptId" name="deptId" class="selectpicker" data-live-search="true">
        </select><br><br>
        <%--抄送人：--%>
        <%--<select multiple id="makeUsers" name="makeUsers" class="selectpicker" data-live-search="true">--%>
        <%--</select><br><br>--%>
        开始时间：<input type="datetime-local" name="startTime"><br><br>
        结束时间：<input type="datetime-local" name="endTime"><br><br>
        会议内容：<textarea rows="5" cols="70" name="content"></textarea><br><br>
        <input type="submit" value="发布">
        <input type="reset" value="重置">
    </form>
</div>

</body>
</html>