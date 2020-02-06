<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/4
  Time: 20:48
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
    <title>订单详情</title>
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
<h3 style="color: purple" align="center">订单详情</h3>
<table border="1" width="100%" cellspacing="0" style="background: aliceblue">
    <tr style="border-color: aliceblue">
        <td colspan="6">
            订单编号：${order.oid}
        </td>
    </tr>
    <tr style="border-color: aliceblue">
        <td colspan="6">
            成交时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.ordertime}"/>
        </td>
    </tr>
    <tr style="border-color: aliceblue">
        <td colspan="6">
            金额：<font color="#ff7f50">￥${order.total}</font>
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
</table><br/>
<form action="<c:url value="orderServlet?method=pay"/> " method="post">
    <input type="hidden" name="oid" value="${order.oid}"/>
    收货地址：<input type="text" name="address"  value="山西省太原市小店区坞城路92号山西大学"/><br/>
    支付方式：<input type="radio" name="pay" value="1" onselect="true"><img src="<c:url value="/pay/weixin.JPG"/> "/>&nbsp;&nbsp;
    <input type="radio" name="pay" value="2"><img src="<c:url value="/pay/zhifubao.JPG"/> "/>&nbsp;&nbsp;
    <input type="radio" name="pay" value="3"><img src="<c:url value="/pay/yinglian.JPG"/> "/><br/>
    <input type="submit" value="提交订单">
</form>

</body>
</html>
