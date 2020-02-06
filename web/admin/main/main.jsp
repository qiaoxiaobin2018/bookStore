<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/6
  Time: 11:07
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
    <title>main</title>
    <base href="<%=basePath%>">
    <style type="text/css">
        * {
            fint-size:10pt;
        }
        body {
            text-align: center;
            margin:0px;
        }
        .table {
            width: 100%;
            height: 100%;
            border:1px solid gray;
            border-collapse: collapse;
        }
        . table td {
            border:1px solid gray;
        }
        iframe {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
<table class="table" align="center">
    <tr style="background: azure;height: 120px;">
        <td colspan="2" align="center">
            <iframe frameborder="0" src="<c:url value="/admin/main/top.jsp"/> " name="top"></iframe>
        </td>
    </tr>
    <tr>
        <td width="256" style="padding: 5px;" align="center" valign="top">
            <iframe frameborder="0" width="120" src="<c:url value="/admin/main/left.jsp"/> " name="left"></iframe>
        </td>
        <td>
            <iframe frameborder="0" src="<c:url value="/admin/main/body.jsp"/> " name="body"></iframe>
        </td>
    </tr>
</table>
</body>
</html>
