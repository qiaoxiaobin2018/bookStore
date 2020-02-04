<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/2
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
%>
<html>
<head>
    <title>注册</title>
    <base href="<%=basePath%>">
</head>
<body>
<h2 style="color: blueviolet">注册</h2>
<span style="color: blueviolet">${msg}</span>
<form action="<c:url value="/userServlet?method=regist"/> " method="post">
    用户名：<input type="text" name="username" value="${form.username}"/><span style="color: blueviolet">${errorMap.usernameError}</span><br/>
    密 码：<input type="text" name="password" value="${form.password}"/><span style="color: blueviolet">${errorMap.passwordError}</span><br/>
    邮 件：<input type="text" name="email" value="${form.email}"/><span style="color: blueviolet">${errorMap.emailError}</span><br/>
    <input type="submit" value="注册">
</form>
</body>
</html>
