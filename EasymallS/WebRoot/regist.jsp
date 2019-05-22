<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>欢迎注册EasyMall</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="css/regist.css"/>
		<script src="/js/jquery-1.4.2.js"></script>
		<script type="text/javascript">
		
			function fn(inp){
				var u = inp.value;
				var data ={"username":u};
				var url = "/hasUsername.action";
				$("#u").load(url,data);
			}
			$(function(){
				$("#img_ver").click(function(){
					var data = new Date().toString();
					var url = "/verify.action?time=" + data;
					$("#img_ver").attr("src",url);
				});
				
			});
			
			
		</script>
	</head>
	<body>
		<form action="/regist.action" method="POST">
			<h1>欢迎注册EasyMall</h1>
			<table>
				<tr>
					<td class="tds" style="text-align: center;color: red" colspan="2" >${errMsg }</td>
					
				</tr>
				<tr>
					<td class="tds">用户名：</td>
					<td>
						<input onblur="fn(this)" type="text" name="username" value="${user.username }"/><span style="color: red" id="u"></span>
					</td>
				</tr>
				<tr>
					<td class="tds">密码：</td>
					<td>
						<input type="password" name="password"/>
					</td>
				</tr>
				<tr>
					<td class="tds">确认密码：</td>
					<td>
						<input type="password" name="password2"/>
					</td>
				</tr>
				<tr>
					<td class="tds">昵称：</td>
					<td>
						<input type="text" name="nickname" value="${user.nickname }"/>
					</td>
				</tr>
				<tr>
					<td class="tds">邮箱：</td>
					<td>
						<input type="text" name="email" value="${user.email }"/>
					</td>
				</tr>
				<tr>
					<td class="tds">验证码：</td>
					<td>
						<input type="text" name="valistr"/>
						<img id="img_ver" src="/verify.action" width="" height="" alt="" />
					</td>
				</tr>
				<tr>
					<td class="sub_td" colspan="2" class="tds">
						<input type="submit" value="注册用户"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
