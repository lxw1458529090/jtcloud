<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<link href="css/cart.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<script src="${app }/js/jquery-1.4.2.js"></script>
		<script type="text/javascript">
			function addCount(id,price){
				var data = {"id":id};
				var url = "/addCount.action";
				$.post(url,data,function(msg){
					// alert(msg);
					var num = $("#inp_"+id).val();
					num++;
				    $("#inp_"+id).val(num);
				    var sum = $("#sum_"+id).html();
				    sum++;
				    sum--;
				    sum+=price;
					$("#sum_"+id).html(sum);
				});
			};
			function subCount(id){
				var data = {"id":id};
				var url = "/subCount.action";
				$.post(url,data,function(msg){
					alert(msg);
				});
			};
			function delCart(id){
				var flag = window.confirm("真的不要了吗？");
				if(flag==true)
					location.href="${app}/delCart.action?id="+id;
			}
			function checkedAll(){
				$("#wrap input[type=checkbox]").attr("checked","checked");
			}
		</script>
	</head>
	<body>
		<jsp:include page="${app }/_head.jsp"></jsp:include>
		<div id="wrap">
			<!-- 标题信息 -->
			<ul id="title">
				<li>
					<input name="allC" type="checkbox" value="" onclick="checkedAll()"/>
					<span id="title_checkall_text">全选</span>
				</li>
				<li class="li_prod">商品</li>
				<li>单价（元）</li>
				<li>数量</li>
				<li>小计（元）</li>
				<li>操作</li>
			</ul>

			<c:forEach items="${requestScope.cartList }" var="cart">
			<!-- 购物信息 -->
			<ul class="prods">
				<li>
					<input type="checkbox" class="allC" name="allC"/> 
				</li>
				<li class="li_prod">
					<img src="/prodImg.action?imgurl=${cart.imgurl }" width="90" height="90" class="prodimg" />
					<span class="prodname">${cart.pname }</span>
				</li>
				<li class="li_price">${cart.price }</li>
				<li>
					<%-- <a href="javascript:void(0)" class="delNum" onclick="subCount(${cart.id })" >-</a> --%>
					<a href="/subCount.action?id=${cart.id }" class="delNum" >-</a>
					<input id="inp_${cart.id }" class="buyNumInp" type="text" value="${cart.count }" >
					<a href="javascript:void(0)" class="addNum" onclick="addCount(${cart.id },${cart.price })" >+</a> 
					<%-- <span class="addNum" onclick="fn2(${cart.id })" >+</span> --%>
				</li>
				<li id="sum_${cart.id }" class="sum_price">${cart.sum }</li>
				<li><a  class="delProd" href="javascript:void(0)" onclick="delCart(${cart.id})">删除</a></li>
			</ul>
			</c:forEach>
			<!-- 总计条 -->
			<div id="total">
				<div id="total_1">
					<input type="checkbox" class="allC" name="allC"/> 
					<span>全选</span>
					<a id="del_a" href="javascript:void(0)">删除选中的商品</a>
					<div id="div_sum">
						<span id="span_1">总价：</span>
						<span>￥</span>
						<span id="span_2" class="total_sum_price">19990000</span>
					</div>
				</div>
				<div id="total_2">	
					<a id="goto_order" href="javascript:void(0)">去结算</a>
				</div>
			</div>
		</div>
		<jsp:include page="${app }/_foot.jsp"></jsp:include>
	</body>
</html>