<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@ include file="/WEB-INF/include/base.jsp"%>
<script type="text/javascript">
	$(function(){
		$(".countId").change(function(){
			var count = this.value;
			var id = this.id;
			if (isNaN(count)) {
				alert("请输入数字");
				this.value = this.defaultValue;
			}else if ((count-0)<=0) {
				alert("请输入正整数");
				this.value = this.defaultValue;
			}else{
				
				this.defaultValue = this.value;
				//window.location = "CartServlet?method=updateCartItem&bookId="+id+"&countStr="+count;
				//改为AJAX请求
				var $td = $(this).parent().next().next();
				$.ajax({
					url:"CartServlet?method=updateCartItem",
					type:"post",
					data:{"bookId":id,"countStr":count},
					dataType:"json",
					success:function(res){
						var $totalCountSpan = $("#b_count");
						$totalCountSpan.text(res.totalCount);
						var $totalAmountSpan = $("#b_price");
						$totalAmountSpan.text(res.totalAmount);
						$td.text(res.amount);
					},
					error:function(){
						alert("出现异常了！");
					}
				});
			}
			
		});
	});
</script>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">购物车</span>
		<%@include file="/WEB-INF/include/welcome.jsp"%>
	</div>

	<div id="main">

		<c:choose>
			<c:when test="${empty cart.cartItemList }">
				<h2>没东西</h2>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>商品名称</td>
						<td>数量</td>
						<td>单价</td>
						<td>金额</td>
						<td>操作</td>
					</tr>

					<c:forEach items="${cart.cartItemList }" var="item">
						<tr>
							<td>${item.book.title }</td>
							<td><input id = "${item.book.id }" class = "countId" type="text" value="${item.count }" ></td>
							<td>${item.book.price }</td>
							<td>${item.amount }</td>
							<td><a href="CartServlet?method=delCartItem&bookId=${item.book.id }">删除</a></td>
						</tr>
					</c:forEach>




				</table>

				<div class="cart_info">
					<span class="cart_span">购物车中共有<span class="b_count" id="b_count">${cart.totalCount }</span>件商品
					</span> <span class="cart_span">总金额<span class="b_price" id="b_price">${cart.totalAmount }</span>元
					</span> <span class="cart_span"><a href="CartServlet?method=clear">清空购物车</a></span> <span
						class="cart_span"><a href="OrderServlet?method=checkOut">去结账</a></span>
				</div>
			</c:otherwise>
		</c:choose>



	</div>

	<div id="bottom">
		<span> 尚硅谷书城.Copyright &copy;2015 </span>
	</div>
</body>
</html>