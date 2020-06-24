<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>
<div id="right">
    <a href="/jsp/user/add.jsp" class="btn btn-success">添加</a>
    <form action="/user/list" method="post">
        用户名： <input type="text" name="username" value="${username}">
        <input type="submit" value="查询" class="btn btn-info">
    </form>
    <table class="table table-bordered">
        <tr>
            <td>序号</td>
            <td>用户名</td>
            <td>真实姓名</td>
            <td>年龄</td>
            <td>性别</td>
            <td>注册时间</td>
            <td>操作</td>
        </tr>

        <c:forEach var="user" items="${list}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td>${user.username}</td>
            <td>${user.realName}</td>
            <td>${user.age}</td>
            <td>
                <c:choose>
                    <c:when test="${user.sex==1}">男</c:when>
                    <c:when test="${user.sex==0}">女</c:when>
                    <c:otherwise>其它</c:otherwise>
                </c:choose>
            </td>
            <td>
                <fmt:formatDate var="registerTime" value="${user.registerTime}"
                                pattern="yyyy年MM月dd HH时mm分ss"></fmt:formatDate>
                    ${registerTime}
            </td>

            <td>
                <a href="/user/toUpdate?id=${user.id}" class="btn btn-primary">修改</a>
                <a href="/user/delete?id=${user.id}" class="btn btn-danger">删除</a>
            </td>
        </tr>
        </c:forEach>
    </table>
    当前页：${page}
    总页数：${pageCount}
    总记录数：${count}

    <a href="/user/list?page=${page-1}">上一页</a>
    <a href="/user/list?page=${page+1}">下一页</a>
</div>
</body>
</html>
