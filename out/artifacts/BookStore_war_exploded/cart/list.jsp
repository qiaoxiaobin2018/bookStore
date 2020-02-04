<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/4
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
%>
<html>
<head>
    <title>我的购物车</title>
    <base href="<%=basePath%>">
    <style type="text/css">
        * {
            font-size: 11pt;
        }
        div {
            margin: 20px;
            border:solid 2px gray;
            width: 60px;
            height: 130px;
            text-align: center;
        }
        li {
            margin:10px;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${empty sessionScope.cart or fn:length(sessionScope.cart.cartItems) eq 0}">
        <h3 style="color: purple" align="center">购物车为空！</h3>
    </c:when>
    <c:otherwise>
        <h3 style="color: purple" align="center">我的购物车</h3>
        <table width="100%" cellspacing="0" style="background: aliceblue">
            <tr>
                <td colspan="7" align="right" style="font-size: 15pt;font-weight: 900">
                    <a href="<c:url value="/cartServlet?method=clear"/> ">清空购物车</a>
                </td>
            </tr>
            <tr>
                <th align="center">图片</th>
                <th align="center">书名</th>
                <th align="center">作者</th>
                <th align="center">单价</th>
                <th align="center">数量</th>
                <th align="center">小计</th>
                <th align="center">操作</th>
            </tr>
            <c:forEach items="${sessionScope.cart.cartItems}" var="cartItem">
                <tr>
                    <td><img src="<c:url value="/${cartItem.book.image}"/> "></td>
                    <td><font color="purple">${cartItem.book.bname}</font> </td>
                    <td><font color="purple">${cartItem.book.author}</font> </td>
                    <td><font color="purple">￥${cartItem.book.price}</font> </td>
                    <td><font color="purple">${cartItem.count}</font> </td>
                    <td><font color="purple">￥${cartItem.subTotal}</font> </td>
                    <td><a href="<c:url value="/cartServlet?method=delete&bid=${cartItem.book.bid}"/> ">删除</a> </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="7" align="right" style="font-size: 15pt;font-weight: 900">
                    合计：<font color="#ff7f50">￥${sessionScope.cart.total}</font>
                </td>
            </tr>
            <tr>
                <td colspan="7" align="right" style="font-size: 15pt;font-weight: 900">
                    <a href="<c:url value=""/> ">立即付款</a>
                </td>
            </tr>
        </table>
    </c:otherwise>
</c:choose>


<%--<table width="100%" cellspacing="0" style="background: aliceblue">--%>
<%--    <tr>--%>
<%--        <td colspan="7" align="right" style="font-size: 15pt;font-weight: 900">--%>
<%--            <a href="<c:url value="/cartServlet?method=clear"/> ">清空购物车</a>--%>
<%--        </td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <th align="center">图片</th>--%>
<%--        <th align="center">书名</th>--%>
<%--        <th align="center">作者</th>--%>
<%--        <th align="center">单价</th>--%>
<%--        <th align="center">数量</th>--%>
<%--        <th align="center">小计</th>--%>
<%--        <th align="center">操作</th>--%>
<%--    </tr>--%>
<%--    <c:forEach items="${sessionScope.cart.cartItems}" var="cartItem">--%>
<%--        <tr>--%>
<%--            <td><img src="<c:url value="/${cartItem.book.image}"/> "></td>--%>
<%--            <td><font color="purple">${cartItem.book.bname}</font> </td>--%>
<%--            <td><font color="purple">${cartItem.book.author}</font> </td>--%>
<%--            <td><font color="purple">￥${cartItem.book.price}</font> </td>--%>
<%--            <td><font color="purple">${cartItem.count}</font> </td>--%>
<%--            <td><font color="purple">￥${cartItem.subTotal}</font> </td>--%>
<%--            <td><a href="<c:url value="/cartServlet?method=delete&bid=${cartItem.book.bid}"/> ">删除</a> </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--    <tr>--%>
<%--        <td colspan="7" align="right" style="font-size: 15pt;font-weight: 900">--%>
<%--            合计：<font color="#ff7f50">￥${sessionScope.cart.total}</font>--%>
<%--        </td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <td colspan="7" align="right" style="font-size: 15pt;font-weight: 900">--%>
<%--            <a href="<c:url value=""/> ">立即付款</a>--%>
<%--        </td>--%>
<%--    </tr>--%>
<%--</table>--%>
</body>
</html>
