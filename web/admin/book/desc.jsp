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

<form style="margin: 30px" action="<c:url value=""/> " method="post">
    书名：<input type="text" name="bname" value="${book.bname}"/><br/>
    作者：<input type="text" name="author" value="${book.author}"/><br/>
    价格：￥<input type="text" name="price" value="${book.price}"/><br/>
    分类：<select name="cid">
        <c:forEach items="${categoryList}" var="category">
                <option value="${category.cid}" <c:if test="${category.cid eq book.category.cid}">selected="selected"</c:if> >
                    ${category.cname}
                </option>
        </c:forEach>
        </select>
    <input hidden="hidden" type="text" name="bid" value="${book.bid}"/><br/>
    <input type="submit" value="修改">
</form>
<a style="margin: 30px" href="<c:url value=""/> "><span style="color: darkorange">删除</span> </a>

</body>
</html>
