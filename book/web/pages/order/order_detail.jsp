<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单详情</title>
	<%@ include file="/pages/common/head.jsp" %>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单详情</span>
		<%@ include file="/pages/common/success.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>订单号</td>
				<td>商品号</td>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>用户号</td>
			</tr>
			<tr >
			    <td colspan="6" style="text-align: left">${requestScope.orderId}</td>
				<td>${requestScope.userId}</td>
			</tr>

			<tr>
				<c:forEach items="${sessionScope.orderItems}" var="orderItem">
				<td></td>
				<td>${orderItem.id}</td>
				<td>${orderItem.name}</td>
				<td>${orderItem.count}</td>
				<td>${orderItem.price}</td>
				<td>${orderItem.totalPrice}</td>
				<td></td>
			</tr>
			</c:forEach>

			<c:if test="${empty sessionScope.orderItems}">
				<%--如果订单为空的情况--%>
				<tr>
					<td colspan="5">
						<a href="index.jsp">亲，当前订单为空！快跟小伙伴们去浏览商品并抢购下单吧！！！</a>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
	<%@ include file="/pages/common/foot.jsp" %>
</body>
</html>