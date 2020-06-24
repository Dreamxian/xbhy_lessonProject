<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改</title>
</head>
<script>


</script>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>
<div id="right">
    <form action="/" method="post">
        <input type="text" style="display:none" name="id" value="${user.id}"/>

        账号：<input type="text" name="username"  value="${user.username}" /><br/><br/>
        邮箱：<input type="text" name="email" value="${user.email}"/><br/><br/>
        姓名：<input type="text" name="realName" value="${user.realName}"/><br/><br/>
        年龄：<input type="text" name="age" value="${user.age}"/><br/><br/>
        性别：<input type="radio" name="gender" value="1" <c:if test="${user.sex==1}">checked</c:if>/>男
              <input type="radio" name="gender" value="0" <c:if test="${user.sex==0}">checked</c:if>/>女<br/><br/>
        简介：<textarea name="description">${user.description}</textarea><br/><br/>
        部门：<select id="deptId" name="deptId">
            <c:forEach var="dept" items="${deptList}">
                <option value="${dept.id}" <c:if test="${user.deptId==dept.id}">selected</c:if>>${dept.name}</option>
            </c:forEach>
            </select><br/><br/>
        <input type="submit" value="修改"/>
        <input type="reset" value="重置"/>
    </form>
</div>
</body>
</html>
