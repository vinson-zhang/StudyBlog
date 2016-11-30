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
				<div class="head-photo">
					<img alt="默认头像，若需要自定义请 选择" title="默认头像，若需要自定义请 选择" 
						class="img-circle img-responsive" src="${basepath}/StudyBlog/user/headPhoto" />
				</div>
				<div class="notice-info">
						<span style="color: red;font-size: 12px;"></span>
				</div>
					
				<!--  
				<form action="${basepath}/StudyBlog/user/headPhoto" class="form-horizontal" id="head-photo-form" method="post" enctype="multipart/form-data" >
					<div class="form-group">
						<label for="h-photo" class="col-sm-3 control-label">头像：</label>
						 <div class="col-sm-9">
							<input type="file"  id="h-photo" name="h-photo" placeholder="头像" accept="image/gif, image/jpeg, image/png">
						</div>
						<input type="hidden" id="userid" name="userid" />
						<input type="submit" value="提交"/>
					</div>
				</form>
				-->  
				<form action="${basepath}/StudyBlog/user/signin" id="signin-form" class="form-horizontal" method="post" enctype="multipart/form-data">
					<div class="form-group">
						 <label for="h-photo" class="col-sm-3 control-label">头像：</label>
						 <div class="col-sm-9">
							<input type="file"  id="h-photo" name="h-photo"  placeholder="头像" accept="image/gif, image/jpeg, image/png">
						 </div>
						 <input type="hidden" id="path" name="path" />
					</div>
					<div class="form-group">
						<label for="nickname" class="col-sm-3 control-label">昵称:</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="nickname" id="nickname" placeholder="昵称">
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-3 control-label">邮箱：</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="email" id="email" placeholder="邮箱">
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-3 control-label">密码：</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" name="password" id="password" placeholder="密码">
						</div>
					</div>
					<div class="form-group">
						<label for="passwordConfirm" class="col-sm-3 control-label">确认密码：</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder="确认密码">
						</div>
					</div>
					<div class="form-group">
						<label for="verifyCode" class="col-sm-3 control-label">验证码：</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="verifyCode" id="verifyCode" placeholder="验证码">
						</div>
						<div class="col-sm-3 verify-code">
							<img alt="请刷新" src="/StudyBlog/user/getVerifyCodeImage" title="看不清楚请点击图片">
						</div>
						
					</div>
					<div class="form-group">
						<button type="button" id="btn-signin" class="btn btn-primary">注册</button>
						<button type="button" id="btn-reset" class="btn btn-primary">清空</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div></div>
</body>
</html>