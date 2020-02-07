<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/7
  Time: 9:42
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
<h3 style="color: blueviolet">添加图书</h3><hr/>
<span style="color: darkorange">${msg}</span>
<form action="<c:url value="admin/adminAddBookServlet"/> " method="post" enctype="multipart/form-data">
    书名：<input type="text" name="bname" value="${map.bname}"/><span style="color: darkorange">${mapErr.bname}</span>
    <br/>
    价格：￥<input type="text" name="price" value="${map.price}"/><span style="color: darkorange">${mapErr.price}</span>
    <br/>
    作者：<input type="text" name="author" value="${map.author}"/><span style="color: darkorange">${mapErr.author}</span>
    <br/>
    图片：<input type="file" name="image"/>* 图片大小不能超过 15 KB，大小不超过 200x150 ，格式为JPG<span style="color: darkorange">  ${mapErr.image}</span>
    <br/>
    分类：<select name="cid">
    <c:forEach items="${categoryList}" var="category">
        <option value="${category.cid}" <c:if test="${category.cid eq map.cid}">selected="selected"</c:if> >
                ${category.cname}
        </option>
    </c:forEach>
</select><br/>
    <input type="submit" value="添加"/>
</form>
</body>
</html>
