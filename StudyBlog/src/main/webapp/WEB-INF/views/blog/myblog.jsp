<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<!DOCTYPE>
<html>
<head>
<%@include file="/public/head.jspf"%>
<link type="text/css" rel="stylesheet"
	href="${basePath}/StudyBlog/css/blog/myblog.css"></link>
<title>StudyBlog | 我的博客</title>
</head>
<body>
	<%@include file="/public/navbar.jspf" %>
	<div class="container">
		<div class="row">
			<div class="col-sm-3" style="border: 1px solid brown;height: 500px;">
				<div class="userinfo col-sm-12" style="border: 1px solid brown;">
					<div class="col-sm-5 head-photo-div"
						style="border: 1px solid brown;">
						<img class="head-photo img-circle"
							src="${basePath}/StudyBlog/images/default/headPhoto/head.jpeg" />
					</div>
					<div class="col-sm-7 head-photo-side"
						style="border: 1px solid brown;">
						<ul class="">
							<li>昵称：</li>
							<li>blog数量：</li>
							<li>博龄：</li>
							<li>总浏览量：</li>
						</ul>

					</div>
				</div>
				<div class="hotblogs">
					<div class="hotblog">
						<p>博客1</p>
					</div>
					<div class="hotblog">
						<p>博客2</p>
					</div>
					<div class="hotblog">
						<p>博客3</p>
					</div>
				</div>

			</div>
			<div class="col-sm-9" style="border: 1px solid brown;height:500px;">
				<div class="hotblog">
						<p>博客1</p>
					</div>
					<div class="hotblog">
						<p>博客2</p>
					</div>
					<div class="hotblog">
						<p>博客3</p>
					</div>
			</div>
		</div>

	</div>
</body>

</html>