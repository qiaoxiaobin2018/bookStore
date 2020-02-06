<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/6
  Time: 13:05
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
    <title>category_list</title>
    <base href="<%=basePath%>">
    <style type="text/css">
        h2 {
            text-align: center;
            color: blueviolet;
        }
        body {
            background: beige;
        }
        table {
            font-family: Consolas;
            font-size: medium;
            border-color: darkorange;
            width: 60%;
        }
        #th {
            background: azure;
        }
    </style>
</head>
<body>
<h2>分类列表</h2>
<table align="center" border="1" cellspacing="0" cellpadding="0">
    <tr>
        <th>分类名称</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${categoryList}" var="category">
        <tr align="center">
            <td>${category.cname}</td>
            <td>
                <a href="<c:url value="/admin/adminCategoryServlet?method=load&cid=${category.cid}"/> ">修改</a>
                <a href="<c:url value="/admin/adminCategoryServlet?method=delete&cid=${category.cid}"/> " onclick="return confirm('确认删除吗？')">
                    <span style="color: darkorange">删除</span>
                </a>
            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
