<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/2
  Time: 17:04
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
    <title>提示</title>
    <base href="<%=basePath%>">
</head>
<body>
<h3 style="color: blueviolet">${msg}</h3>
<ul>
    <li><a href="<c:url value="/index.jsp"/> ">主页</a></li>
    <li><a href="<c:url value="/qian/user/login.jsp"/> ">登录</a></li>
    <li><a href="<c:url value="/qian/user/regist.jsp"/> ">注册</a></li>
</ul>
</body>
</html>
