<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
  	<style type="text/css">
  		body{
  			text-align: center;
  		}
		table {
			text-align: center;
		}
		th{
			background-color: silver;
		}
  	</style>
  	<script src="/js/jquery-1.4.2.js"></script>
  	<script type="text/javascript">
  		function fn(inp,id,num){
//   			var pnum = inp.value;
				var pnum = $(inp).val();
				alert(pnum);
  			if(num!=pnum){
  				var a = window.confirm("确认修改库存？");
  				if(a==true){
  					var url = "/updataPnum.action";
  					var data = {"id":id,"pnum":pnum};
  					$.post(url,data,function(msg){
						alert(msg);
	  				});
  				}else{
  					inp.value=num;
  				}
  			}
  		}
  		function del(id,cid){
  			var a = window.confirm("真的不要了？");
  			if(a==true)
  				location.href="/delProd.action?id="+id+"&cid="+cid;
  		}
  	</script>
  </head>
  <body>
  	<h1>商品管理</h1>
  	<a href="/backend/manageAddProd.jsp">添加商品</a>
  	<hr>
  	<table align="center" bordercolor="black" border="1" width="90%" cellspacing="0px" cellpadding="5px">
  	<tr>
  		<th>商品图片</th>
  		<th>商品id</th>
  		<th>商品名称</th>
		<th>商品种类</th>
		<th>商品单价</th>
		<th>库存数量</th>
		<th>描述信息</th>
		<th>操作</th>
  	</tr>
  	<c:forEach items="${requestScope.list }" var="prod" >
  		<tr>
  			<td><img width="120px" height="120px" src="/prodImg.action?imgurl=${prod.imgurl }"/></td>
  			<td>${prod.id }</td>
  			<td>${prod.name }</td>
  			<td>${prod.cname }</td>
  			<td>${prod.price }</td>
  			<td><input type="text" value="${prod.pnum }" style="width: 40px;text-align: right;" onblur="fn(this,${prod.id},${prod.pnum })"/> </td>
  			<td>${prod.description }</td>
  			<%-- <td><a href="/delProd.action?id=${prod.id }&cid=${prod.cid}">删除 ${errMsg }</a> </td> --%>
  			<%-- <td><span onclick="del(${prod.id},${prod.cid })">删除</span></td> --%>
  			<td><input type="submit" onclick="del(${prod.id},${prod.cid })" value="删除"/></td>
  		</tr>
  	</c:forEach>
  	</table>
  </body>
</html>
