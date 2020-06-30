<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会议</title>
</head>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>
<div id="right">
    <form>
        会议主题：<input type="text" name="title" id="" value=""/>
        <input type="submit" value="查询" name=""/>
    </form>
    <table class="table table-bordered">
        <tr>
            <td>主题</td>
            <td>部门</td>
            <td>状态</td>
            <td>开始时间</td>
            <td>会议内容</td>
        </tr>
        <c:forEach items="${list}" varStatus="status" var="meeting">
            <tr>
                <td><a href="/meeting/getMeetingById?id=${meeting.id}">${meeting.title}</a></td>
                <td>${meeting.deptName}</td>
                <td>
                    <c:choose>
                    <c:when test="${meeting.status==0}">未开始</c:when>
                    <c:when test="${meeting.status==1}">进行中</c:when>
                    <c:when test="${meeting.status==2}">已结束</c:when>
                        <c:otherwise></c:otherwise>
                    </c:choose>
                <td>
                    <fmt:parseDate var="startTime" value="${meeting.startTime}"
                                   pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
                    <fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                </td>
                <td>${meeting.content}</td>
            </tr>
        </c:forEach>
    </table>


</div>
</body>
</html>
