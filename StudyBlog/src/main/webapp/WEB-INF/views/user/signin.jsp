<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/public/head.jspf"%>
<script type="text/javascript" src="${basepath}/StudyBlog/js/user/signin.js"></script>
<link type="text/css" rel="stylesheet"
	href="${basepath}/StudyBlog/css/user/signin.css"></link>
<title>StudyBlog | 注册</title>
</head>
<body>
	<div>
		<div class="panel panel-primary login-side">
			<div class="panel-heading">
				<p class="panel-title">注册</p>
			</div>
			<div class="panel-body">
				<form action="" role="form">
					<div class="form-group">
						<input type="text" class="form-control" id="username" placeholder="邮箱">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="password" placeholder="密码">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="passwordConfirm" placeholder="确认密码">
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-primary">注册</button>
						<button type="button" class="btn btn-primary">清空</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div></div>
</body>
</html>