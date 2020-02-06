<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/6
  Time: 14:19
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
    <title>add</title>
    <base href="<%=basePath%>">
</head>
<body>
<h3 style="color: blueviolet">添加分类</h3><hr/>
<form action="<c:url value="/admin/adminCategoryServlet?method=add"/> " method="post">
    分类名称：<input type="text" name="cname" style="font-family: Consolas">
    <input type="submit" value="添加">
</form>
</body>
</html>
