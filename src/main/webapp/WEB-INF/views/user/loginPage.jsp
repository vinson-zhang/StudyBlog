<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/public/head.jspf"%>
<script type="text/javascript" src="${basepath}/StudyBlog/js/user/login.js"></script>
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
			<p style="color:red;font-size: 14px;" id="messageLogin">
				${messageLogin }
			</p>
			<div class="panel-body">
				<form action="/StudyBlog/user/login" role="form" method="post" id="userinfo">
					<div class="form-group col-sm-12">
						<input type="text" class="form-control" id="username" name="username" placeholder="用户名">
					</div>
					<div class="form-group col-sm-12">
						<input type="password" class="form-control" id="password" name="password" placeholder="密码">
					</div>
					<div class="form-group ">
					    <div class="col-sm-8">
							<input type="text" class="form-control" id="verifyCode" name="verifyCode" placeholder="验证码">
						</div>
						<div class="col-sm-4 verify-code">
							<img alt="请刷新" src="/StudyBlog/user/getVerifyCodeImage" title="看不清楚请点击验证码">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<button type="button" id="login-btn" onclick="login()" class="btn btn-primary btn-block">登录</button>
					</div>
					<div class="form-group col-sm-12">
						<a id="findpassword" class="btn btn-link">忘记密码？</a>
						<a id="signin" class="btn btn-link" href="${basePath}/StudyBlog/user/signin" >没有账号</a>
					</div>
					
				</form>
			</div>
		</div>
	</div>
	<div></div>
</body>
</html>