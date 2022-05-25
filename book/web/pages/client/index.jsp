<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<%@ include file="/pages/common/head.jsp"%>
	<script>
		$(function () {
			// 给加入购物车按钮绑定单击事件
			// $("button.addToCart").click(function () {
			// 	/*** 在事件响应的 function 函数 中，有一个 this 对象，这个 this 对象，是当前正在响应事件的 dom 对象 * @type {jQuery} */
			// 	let bookId = $(this).attr("bookId");
			// 	location.href = "http://localhost:8080/book/cartServlet?action=addItem&id=" + bookId;
			// });
			$("button.addToCart").click(function (){
				let bookId = $(this).attr("bookId");
				$.getJSON("http://localhost:8080/book/cartServlet","action=ajaxAddItem&id="+bookId
				,function (data){
					$(".cartTotalCount").text("您的购物车中有 " + data.totalCount + " 件商品");
					$(".cartLastname").text(data.lastName);
					//延时执行
					setTimeout(function () {
						location.reload();
						}, 1);
					//自动立即刷新页面
                    //location.reload()
					//延时3秒执行callback函数
					//setTimeout(function (callback){
					// 	  callback();
					//},3000)
				})
			});
		});
	</script>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<div>
				<c:if test="${empty (sessionScope.admin==null?sessionScope.user:sessionScope.admin)}">
				     <a href="pages/user/login.jsp">登录</a>
				     <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
				</c:if>
				<c:if test="${not empty (sessionScope.admin==null?sessionScope.user:sessionScope.admin)}">
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
				</c:if>
				<c:if test="${empty sessionScope.admin}">
					<a href="pages/cart/cart.jsp">购物车</a>
				</c:if>
				<c:if test="${empty sessionScope.user}">
					<a href="pages/manager/manager.jsp">后台管理</a>
				</c:if>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="${requestScope.page.url}" method="get">
					<input type="hidden" value="pageByPrice" name="action">
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<c:if test="${empty sessionScope.cart.items}">
					<span class="cartTotalCount"></span>
					<div>
						<span style="color:red" class="cartLastName">当前购物车为空</span>
					</div>
				</c:if>
				<c:if test="${not empty sessionScope.cart.items}">
					<span class="cartTotalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
					<div>
						您刚刚将<span style="color: red" class="cartLastName">${sessionScope.lastName}</span>加入到了购物车中
					</div>
				</c:if>

			</div>
			<c:forEach items="${requestScope.page.items}" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${book.imgPath}" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${book.name}</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author}</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">${book.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${book.sales}</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${book.stock}</span>
					</div>
					<div class="book_add">
						<button class="addToCart" bookId="${book.id}">加入购物车</button>
					</div>
				</div>
			</div>
			</c:forEach>
	</div>
		<%@include file="/pages/common/page_nav.jsp"%>
	</div>

        <%@ include file="/pages/common/foot.jsp"%>
</body>
</html>