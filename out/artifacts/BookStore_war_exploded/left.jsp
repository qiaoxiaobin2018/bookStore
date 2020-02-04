<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/3
  Time: 16:18
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
    <style type="text/css">
        * {
            font-size: 10pt;
            text-align: center;
        }
        div {
            background: antiquewhite;
            margin: 3px;
            padding:3px;
        }
        a {
            text-decoration: purple;
        }
    </style>
</head>
<body>
<div>
    <a href="<c:url value="/bookServlet?method=findAll" />">全部分类</a>
</div>
<c:forEach items="${categoryList}" var="category">
    <div>
        <a href="<c:url value="/bookServlet?method=findByCategory&cid=${category.cid}" />"><font color="purple">${category.cname}</font></a>
    </div>
</c:forEach>

</body>
</html>
