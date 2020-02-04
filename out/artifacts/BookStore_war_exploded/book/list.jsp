<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/3
  Time: 17:34
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
    <title>bookList</title>
    <base href="<%=basePath%>">
    <style type="text/css">
        body {
            font-size:10pt ;
        }
        .icon {
            margin:80px;
            border:solid 1px gray;
            width: 60px;
            height: 130px;
            text-align: center;
            float: left;
        }
    </style>
</head>
<body class="body">
<h3 align="center">书籍列表</h3>
<c:forEach items="${bookList}" var="book">
    <div class="icon">
        <a href="<c:url value="/bookServlet?method=load&bid=${book.bid}"/> "><img src="<c:url value="/${book.image}"/> "/></a><br/>
        <a href="<c:url value="/bookServlet?method=load&bid=${book.bid}"/> ">${book.bname}</a>
    </div>
</c:forEach>
</body>
</html>