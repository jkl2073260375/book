<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
  <span>欢迎
    <c:choose>
      <c:when test="${empty sessionScope.admin}"></c:when>
      <c:otherwise>${sessionScope.manager}</c:otherwise>
    </c:choose>
    <span class="um_span">
    ${sessionScope.admin==null?sessionScope.user.username:sessionScope.admin.username}
  </span>光临尚硅谷书城</span>
  <c:if test="${empty sessionScope.admin}">
    <a href="orderServlet?action=showUserOrders">我的订单</a>
  </c:if>
  <c:if test="${not empty sessionScope.admin}">
    <a href="orderServlet?action=showAllOrders">订单管理</a>
  </c:if>
  <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
  <a href="index.jsp">返回</a>
</div>

