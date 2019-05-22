<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<link href="css/prodList.css" rel="stylesheet" type="text/css">
<script src="${app }/js/jquery-1.4.2.js"></script>
<script type="text/javascript">
	function fnn(pid){
		var data = {"id":pid};
		var url = "/addToCart.action";
		$.post(url,data,function(result){
			alert(result);
		});
	};
</script>
</head>
<body>
	<jsp:include page="/_head.jsp"></jsp:include>
	<div id="content">
		<div id="search_div">
			<form method="post" action="#">
				<span class="input_span">商品名：<input type="text" name="name" />
				</span> <span class="input_span">商品种类：<input type="text"
					name="category" />
				</span> <span class="input_span">商品价格区间：<input type="text"
					name="minprice" /> - <input type="text" name="maxprice" />
				</span> <input type="submit" value="查 询">
			</form>
		</div>
		<div id="prod_content">
			<c:forEach items="${requestScope.list }" var="prod">
				<div class="prod_div" style="margin-right: 0px;">
					<img src="/prodImg.action?imgurl=${prod.imgurl }"></img>
					<div id="prod_name_div">${prod.name }</div>
					<div id="prod_price_div">￥${prod.price }元</div>
					<div>
						<div id="gotocart_div">
							<span onclick="fnn(${prod.id })" <%-- href="/addToCart.action?pid=${prod.id }" --%>>加入购物车</span>
						</div>
						<div id="say_div">133人评价</div>
					</div>
				</div>
			</c:forEach>
			<div style="clear: both"></div>
		</div>
	</div>
	<jsp:include page="/_foot.jsp"></jsp:include>
</body>
</html>
