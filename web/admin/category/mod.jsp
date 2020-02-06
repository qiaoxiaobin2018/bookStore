<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/6
  Time: 16:16
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
    <title>mod</title>
    <base href="<%=basePath%>">
</head>
<body>
<h3 style="color: blueviolet">修改分类</h3><hr/>
<form style="font-family: Consolas" action="<c:url value="/admin/adminCategoryServlet?method=edit"/> " method="post" >
    <input type="hidden" value="${category.cid}" name="cid"><br/>
    分类名称：<input type="text" value="${category.cname}" name="cname">
    <input type="submit" value="修改">
</form>
</body>
</html>
