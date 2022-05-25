<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%@ include file="/pages/common/head.jsp" %>
	<script>
		$(function (){
			$("#send").click(function (){
				alert("已提醒商家尽快发货!");
			});
		});
	</script>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="orderServlet?action=showAllOrders">我的订单</span>
		<%@ include file="/pages/common/success.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>修改状态</td>
				<td>详情</td>
			</tr>
			<c:forEach items="${sessionScope.orders}" var="order">
			<tr>
				<td>${order.createTime}</td>
				<td>${order.price}</td>
				<td>
					<c:choose>
						<c:when test="${order.status==0}">未发货</c:when>
						<c:when test="${order.status==1}">发货中</c:when>
						<c:when test="${order.status==2}">已签收</c:when>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${not empty sessionScope.admin}">
							<c:choose>
								<c:when test="${order.status==0}">
									<a href="orderServlet?action=sendOrders&orderId=${order.orderId}">是否发货?</a>
								</c:when>
								<c:when test="${order.status==1}">发货中</c:when>
								<c:when test="${order.status==2}">已签收</c:when>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${order.status==0}">
									<a href="orderServlet?action=receiverOrders&orderId=${order.orderId}" id="send">是否催促商家发货?</a>
								</c:when>
								<c:when test="${order.status==1}">
									<a href="orderServlet?action=receiverOrders&orderId=${order.orderId}">是否签收?</a>
								</c:when>
								<c:when test="${order.status==2}">已签收</c:when>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</td>
				<td><a href="orderServlet?action=showOrdersDetail&orderId=${order.orderId}">查看详情</a></td>
			</tr>
			</c:forEach>
		</table>
		
	
	</div>

	<%@ include file="/pages/common/foot.jsp" %>
</body>
</html>