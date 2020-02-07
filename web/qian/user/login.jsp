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
    <title>登录</title>
    <base href="<%=basePath%>">
</head>
<body>
<h2 style="color: blueviolet">登录</h2>
<span style="color: blueviolet">${msg}</span>
<form action="<c:url value="/userServlet?method=login"/> " method="post" target="_top">
    用户名：<input type="text" name="username" value="${form.username}"/><br/>
    密 码：<input type="text" name="password" value="${form.password}"/><br/>
    <input type="submit" value="登录">
</form>
</body>
</html>
