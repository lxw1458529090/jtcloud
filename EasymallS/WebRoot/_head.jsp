<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<link rel="stylesheet" href="css/head.css"/>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<head>
	<script src="${app }/js/jquery-1.4.2.js"></script>
	<script src="${app }/js/search.js"></script>
	<script type="text/javascript">
		function fn(){
			var a = window.confirm("真的要走了吗 >_<");
			if(a==true){
				/* alert("bye~"); */
				location.href="/logout.action";
			}/* else{
				alert("smile");
			} */
		}
		
	 	function myCart(){
			var url = "/myCart.action";
			$.get(url,function(msg){
				if(msg!="")
					alert(msg);
				else
					location.href="/listCart.action";
			});
		} 
	</script>
</head>
<div id="common_head">
	<div id="line1">
		<% if(session.getAttribute("user")==null){%>
		<div id="content">
			<a href="/login.jsp">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="/regist.jsp">注册</a>
		</div>
		<% }else{ %>
		<div id="content">
			欢迎 ${sessionScope.user.username } 回来&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javascript:void(0)" onclick="fn()">注销</a><!-- <span onclick="fn()">注销</span> -->
		</div>
		<% } %>
	</div>
	<div id="line2">
		<img id="logo" src="img/head/logo.jpg"/>
		<input type="text" name="" value="${requestScope.keyword }"/>
		<input type="button" value="搜 索"/>
		<span id="goto">
			<a id="goto_order" href="#">我的订单</a>
		    <%-- <a id="goto_cart" href="${app }/listCart.action">我的购物车</a> --%>
			<a id="goto_cart" href="javascript:void(0)" onclick="myCart()">我的购物车</a>
		</span>
		<img id="erwm" src="img/head/qr.jpg"/>
	</div>
	<div id="line3">
		<div id="content">
			<ul>
				<li><a href="${app }/index.jsp">首页</a></li>
				<li><a href="${app }/prodList.action?url=prod_list">全部商品</a></li>
				<li><a href="/kindProd.action?cname=手机数码">手机数码</a></li>
				<li><a href="/kindProd.action?cname=电脑平板">电脑平板</a></li>
				<li><a href="/kindProd.action?cname=家用电器">家用电器</a></li>
				<li><a href="/kindProd.action?cname=汽车用品">汽车用品</a></li>
				<li><a href="/kindProd.action?cname=食品饮料">食品饮料</a></li>
				<li><a href="/kindProd.action?cname=图书杂志">图书杂志</a></li>
				<li><a href="/kindProd.action?cname=服装服饰">服装服饰</a></li>
				<li><a href="/kindProd.action?cname=理财产品">理财产品</a></li>
			</ul>
		</div>
	</div>
</div>