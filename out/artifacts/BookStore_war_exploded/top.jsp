<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/3
  Time: 14:55
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
    <base target="main"/>
    <title>TOP</title>
    <base href="<%=basePath%>">
    <style type="text/css">
        body {
            background: antiquewhite;
        }
        a {
            text-transform: none;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h2 style="text-align: center">网上书店</h2>
<div style="font-size: 10pt">
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <a href="<c:url value="qian/user/login.jsp"/> " target="_parent">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<c:url value="qian/user/regist.jsp"/> " target="_parent">注册</a>
        </c:when>
        <c:otherwise>
            您好，${sessionScope.user.username}&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<c:url value="/cart/list.jsp"/> " target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<c:url value=""/> " target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<c:url value="/userServlet?method=quit"/> " target="_parent">退出</a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
