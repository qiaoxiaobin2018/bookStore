<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/3
  Time: 16:19
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
    <title>body</title>
    <base href="<%=basePath%>">
</head>
<body>
<h3 style="color: purple" align="center">欢迎!</h3>
</body>
</html>
