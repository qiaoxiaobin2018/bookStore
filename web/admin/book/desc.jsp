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
    <script type="text/javascript">
        function addMethod(method) {
            var btn = document.getElementById("method");
            btn.value = method;
        }
    </script>
</head>
<h3 style="color: purple" align="center">书籍详情</h3>
<img style="margin: 30px" src="<c:url value="/${book.image}"/>"/>

<form style="margin: 30px" action="<c:url value="/admin/adminBookServlet"/> " method="post">
    <input type="hidden" name="method" id="method"/>
    <input type="hidden" name="bid" value="${book.bid}"/>
    <input type="hidden" name="image" value="${book.image}"/>
    书名：<input type="text" name="bname" value="${book.bname}"/><br/>
    作者：<input type="text" name="author" value="${book.author}"/><br/>
    价格：￥<input type="text" name="price" value="${book.price}"/><br/>
    分类：<select name="cid">
        <c:forEach items="${categoryList}" var="category">
                <option value="${category.cid}" <c:if test="${category.cid eq book.category.cid}">selected="selected"</c:if> >
                    ${category.cname}
                </option>
        </c:forEach>
        </select><br/>
    <input type="submit" onclick="addMethod('edit');alert('修改成功！')" value="修改">&nbsp;&nbsp;
    <input type="submit" onclick="addMethod('delete');return confirm('确认删除吗？')" value="删除" style="color: crimson">
</form>

</body>
</html>
