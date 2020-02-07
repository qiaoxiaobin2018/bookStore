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
    <base target="body"/>
    <title>left</title>
    <base href="<%=basePath%>">
</head>
<body style="background: antiquewhite">
<ul style="color: darkorange">
    <li><a href="<c:url value="/admin/adminCategoryServlet?method=findAll"/> ">查看所有分类</a></li>
    <li><a href="<c:url value="/admin/category/add.jsp"/> ">添加分类</a></li>
</ul>

<ul style="color: darkorange">
    <li><a href="<c:url value="/admin/adminBookServlet?method=findAll"/> ">查看所有图书</a></li>
    <li><a href="<c:url value="/admin/adminBookServlet?method=addPre"/> ">添加图书</a></li>
</ul>
</body>
</html>
