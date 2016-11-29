<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<!DOCTYPE>
<html>
	<head>
		<%@include file="/public/head.jspf"%>
		<link type="text/css" rel="styleshee" href="/css/blog/writeBlog.css"></link>
		<title>StudyBlog | 临时的title</title>
	</head>

	<body>

		<%@include file="/public/navbar.jspf"%>

		<div class="container" style="border: 1px solid brown;">
			<div class="row">
				<div class="col-xs-9 blog-detail" style="border: 1px solid brown;">
					<div class="blog-title-bar col-sm-12">
						<div class="blog-title col-sm-12">
							${blog.name }
						</div>
						<div class="col-sm-12 blog-other">
							<div class="col-sm-4 blog-author">
								<span>作者：${blog.author.nickname}</span>
							</div>
							<div class="col-sm-4 blog-create-time">
								<span>创建时间:${createTime}</span>
							</div>
							<div class="col-sm-4 blog-hot">
								<span>点击量：${blog.readTimes}</span>
							</div>
						</div>
					</div>
					
					<div class="col-sm-12 blog-content">
						<hr style="border: 2px solid greenyellow;"/>
						<p>${blog.content}</p>
						<!-- 
							<p>I Love You!碧琳</p>
						 -->
					</div>
				</div>
				<div class="col-xs-3 blog-hot" style="border: 1px solid brown;">
					<ul>
						<li>one</li>
						<li>two</li>
						<li>three</li>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>