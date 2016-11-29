<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<!DOCTYPE>
<html>
<head>
<%@include file="/public/head.jspf"%>
<script type="text/javascript" src="${basePath}/StudyBlog/js/blog/myBlog.js"></script>
<link type="text/css" rel="stylesheet"
	href="${basePath}/StudyBlog/css/blog/myblog.css"></link>
<title>StudyBlog | 我的博客</title>
</head>
<body>
	<%@include file="/public/navbar.jspf" %>
	<div class="container">
		<div class="row">
			<div class="col-sm-3 left-bar" style="border: 1px solid brown;height: 500px;">
				<div class="userinfo col-sm-12" >
					<div class="col-sm-5 head-photo-div">
						<img class="head-photo img-circle"
							src="${basePath}/StudyBlog/user/headPhoto" />
					<!--  
						<img class="head-photo img-circle"
							src=${basePath}${currentUser.headPortrait} />
					-->
					</div>
					<div class="col-sm-7 head-photo-side">
						<ul class="">
							<li>昵称：${currentUser.nickname }</li>
							<li>blog数量：${sum }</li>
							<li>博龄：${blogAge }</li>
							<li>总浏览量：</li>
						</ul>

					</div>
				</div>
				
				<div class="hotblogs">
					<div class="hotblog">
						<span class="glyphicon glyphicon-thumbs-up"></span>
						<span><a href="${basePath}/StudyBlog/blog/blogDetail/2">博客1</a></span>
					</div>
					<div class="hotblog">
						<span class="glyphicon glyphicon-thumbs-up"></span>
						<span><a href="${basePath}/StudyBlog/blog/blogDetail/2">博客2</a></span>
					</div>
					<div class="hotblog">
						<span class="glyphicon glyphicon-thumbs-up"></span>
						<span><a href="${basePath}/StudyBlog/blog/blogDetail/3">博客3</a></span>
					</div>
				</div>

			</div>
			<div class="col-sm-9" id="blogs" style="border: 1px solid brown;height:500px;">
			</div>
		</div>

	</div>
</body>

</html>