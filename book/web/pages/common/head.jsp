<%--
  Created by IntelliJ IDEA.
  User: jkl2073260375
  Date: 2022/3/20
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String basePath= request.getScheme()
                    +"://" +request.getServerName()
                    +":"+request.getServerPort()
                    +request.getContextPath()
                    +"/";
%>
    <base href="<%=basePath%>>" >
<%--    <base href="http://localhost:8080/book/">--%>
    <link type="text/css" rel="stylesheet" href="static/css/style.css" >
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>

