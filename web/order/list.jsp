<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/5
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
%>
<html>
<head>
    <title>我的订单</title>
    <base href="<%=basePath%>">
    <style type="text/css>">
        * {
            font-size:11pt;
        }
        div {
            border: solid 2px gray;
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
<%--需要两层循环，多个订单，每个订单有多个订单子项--%>
<h3 align="center" style="color: purple">我的所有订单</h3>
<c:forEach items="${orderList}" var="order">
    <table border="1" width="100%" cellspacing="0" style="background: aliceblue">
        <tr style="border-color: aliceblue">
            <td colspan="6">
                订单编号：${order.oid}
            </td>
        </tr>
        <tr style="border-color: aliceblue">
            <td colspan="6">
                生成时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.ordertime}"/>
            </td>
        </tr>
        <tr style="border-color: aliceblue">
            <td colspan="6">
                订单金额：<font color="#ff7f50">￥${order.total}</font>
            </td>
        </tr>
        <tr style="border-color: aliceblue">
            <td colspan="6">
                <c:choose>
                    <c:when test="${order.state eq 1}">
                        订单状态：
                        <font color="red">待付款&nbsp;&nbsp;</font>
                        <a href="<c:url value="/orderServlet?method=load&oid=${order.oid}"/> "><font color="green">立即付款</font> </a>
                    </c:when>
                    <c:when test="${order.state eq 2}">
                        订单状态：
                        <font color="#00bfff">已付款，未发货&nbsp;&nbsp;</font>
                        <a href="<c:url value=""/> "><font color="green">提醒卖家发货</font> </a>
                    </c:when>
                    <c:when test="${order.state eq 3}">
                        订单状态：
                        <font color="#00bfff">已送达&nbsp;&nbsp;</font>
                        <a href="<c:url value="/orderServlet?method=confirm&oid=${order.oid}"/> "><font color="green">确认收货</font> </a>
                    </c:when>
                    <c:when test="${order.state eq 4}">
                        订单状态：
                        <font color="#00bfff">订单已完成&nbsp;&nbsp;</font>
                        <a href="<c:url value=""/> "><font color="green">有疑问？</font> </a>
                    </c:when>
                </c:choose>
            </td>
        </tr>
        <tr style="border-color: aliceblue">
            <th>图片</th>
            <th>书名</th>
            <th>单价</th>
            <th>作者</th>
            <th>数量</th>
            <th>小计</th>
        </tr>
        <c:forEach items="${order.orderItemList}" var="orderitem">
            <tr style="border-color: aliceblue">
                <td width="15%">
                    <div><img src="<c:url value="/${orderitem.book.image}"/> "/></div>
                </td>
                <td>${orderitem.book.bname}</td>
                <td>￥${orderitem.book.price}</td>
                <td>${orderitem.book.author}</td>
                <td>${orderitem.count}</td>
                <td>￥${orderitem.subtotal}</td>
            </tr>
        </c:forEach>
    </table><br/><br/><br/>
</c:forEach>
</body>
</html>
