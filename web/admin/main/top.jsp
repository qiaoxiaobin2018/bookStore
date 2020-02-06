<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/6
  Time: 11:07
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
    <title>top</title>
    <base href="<%=basePath%>">
</head>
<body>
<h2 style="color: deepskyblue" align="center">网上书店后台管理</h2>
<%--您好，${sessionScope.user.username}&nbsp;&nbsp;|&nbsp;&nbsp;--%>
管理员：乔志&nbsp;&nbsp;|&nbsp;&nbsp;
<%--<a href="<c:url value="/cart/list.jsp"/> " target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;--%>
<%--<a href="<c:url value="/orderServlet?method=myOrders"/> " target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;--%>
<a href="<c:url value="/userServlet?method=quit"/> " target="_parent">退出</a>
</body>
</html>
