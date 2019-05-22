<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<style type="text/css">
			h1{
				text-align: center;
			}
		</style>
	</head>
	<body>
		<h1>EasyMall_添加商品</h1>
		<hr>
			<form action="/addProd.action" method="post" enctype="multipart/form-data" >
				<table align="center" border="1" cellspacing="0px" cellpadding="5px">
					<tr>
						<td>商品名称</td>
						<td><input type="text" name="name"/></td>
					</tr>
					<tr>
						<td>商品单价</td>
						<td><input type="text" name="price"/></td>
					</tr>
					<tr>
						<td>商品种类</td>
						<td>
						<!-- <input type="text" name="cname"/> -->
						<select name="cname" size=1>
							<option value="--请选择--" selected="selected">请选择</option>
							<option value="手机数码">手机数码</option>
							<option value="电脑平板">电脑平板</option>
							<option value="家用电器">家用电器</option>
							<option value="汽车用品">汽车用品</option>
							<option value="食品饮料">食品饮料</option>
							<option value="图书杂志">图书杂志</option>
							<option value="服装服饰">服装服饰</option>
							<option value="理财产品">理财产品</option>
						</select>
						</td>
					</tr>
					<tr>
						<td>库存数量</td>
						<td><input type="text" name="pnum"/></td>
					</tr>
					<tr>
						<td>商品图片</td>
						<td><input type="file" name="fx"/></td>
						
					</tr>
					<tr>
						<td>描述信息</td>
						<td>
							<textarea rows="5" cols="30" name="description"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="添加商品"/></td>
					</tr>
				</table>
			</form>
		<hr>
	</body>
</html>
