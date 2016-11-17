<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/public/head.jspf"%>
<link type="text/css" rel="stylesheet"
	href="${basepath}/StudyBlog/css/user/login.css"></link>
<title>StudyBlog | 登录</title>
</head>
<body>
	<div>
		<div class="panel panel-primary login-side">
			<div class="panel-heading">
				<p class="panel-title">登录</p>
			</div>
			<div class="panel-body">
				<form action="" role="form">
					<div class="form-group">
						<input type="text" class="form-control" id="username" placeholder="用户名">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="password" placeholder="密码">
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-primary btn-block">登录</button>
					</div>
					<div class="form-group">
						<a id="findpassword" class="btn btn-link">忘记密码？</a>
						<a id="signin" class="btn btn-link" href="${basepath}/StudyBlog/user/toSigninPage" >没有账号</a>
					</div>
					
				</form>
			</div>
		</div>
	</div>
	<div></div>
</body>
</html>