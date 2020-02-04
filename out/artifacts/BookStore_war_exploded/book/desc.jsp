<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/3
  Time: 20:37
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
    <base target="body">
    <title>decs</title>
    <base href="<%=basePath%>">
</head>
<h3 style="color: purple" align="center">书籍详情</h3>
<img style="margin: 30px" src="<c:url value="/${book.image}"/>"/>
<ul>
    <li>书名：${book.bname}</li>
    <li>作者：${book.author}</li>
    <li>价格：<font color="#ff7f50">￥${book.price}</font> </li>
</ul>

<form style="margin: 30px" action="<c:url value="/cartServlet?method=add"/> " method="post">
    购买数量：<input type="text" value="1" name="count"/><br/>
    <input hidden="hidden" type="text" name="bid" value="${book.bid}"/><br/>
    <input type="submit" value="立即购买">
</form>

</body>
</html>
