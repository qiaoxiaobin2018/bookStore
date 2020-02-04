<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2020/2/2
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  String contextPath = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
%>
<html>
  <head>
    <title>主页</title>
    <base href="<%=basePath%>">
    <style type="text/css">
      * {
        font-size: 10pt;
      }
      body {
        text-align: center;
      }
      .table {
        width:1024px;
        height:100%;
        border:1px solid purple;
        border-collapse: collapse;
      }
      .table td {
        border:1px solid purple;
      }
      iframe {
        width: 100%;
        height: 100%;
      }
    </style>
  </head>
  <body>
  <table class="table" align="center">
    <tr style="background: antiquewhite;height: 120px; ">
      <td colspan="2" align="center">
         <iframe frameborder="0" src="<c:url value="/top.jsp"/> " name="top"></iframe>
      </td>
    </tr>
    <tr>
      <td width="120" style="padding: 5px;" align="center" valign="top">
        <iframe frameborder="0" width="120" src="<c:url value="/categoryServlet?method=findAll"/> " name="left"></iframe>
      </td>
      <td>
        <iframe frameborder="0" src="<c:url value="body.jsp"/> " name="body"></iframe>
      </td>
    </tr>
  </table>
  </body>
</html>
