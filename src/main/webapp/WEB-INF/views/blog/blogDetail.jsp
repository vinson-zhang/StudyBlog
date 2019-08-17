<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<!DOCTYPE>
<html>
	<head>
		<%@include file="/public/head.jspf"%>
		<link type="text/css" rel="stylesheet"
	href="${basePath}/StudyBlog/css/blog/blogDetail.css"></link>
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
			
			<!-- 评论开始 -->
			<div class="row">
				<div class="comments col-xs-9">
					<div class="comment-single">
						<div class="comment-head">
							<span class="comment-user">碧琳hello</span>
							<span class="comment-time">2015-12-15 15:29</span>
							<span class="comment-likes"> 
								<span class="glyphicon glyphicon-heart"></span>
								<span class="glyphicon glyphicon-heart-empty"></span>
								30
							</span>
							
						</div>
						
						<div class="comment-body">
							<span class="comment-words">写的不错！赞一个！</span>
						</div>
					</div>
				</div>
				<div class="write-comment col-xs-9">
				<div  style="text-align: left;background-color: #CCC;">写评论</div>
					<form action=""class="form-horizontal" method="post" >
						<div class="form-group">
							 <label for="new-comment" class="col-sm-1 control-label">评论：</label>
							 <div class="col-sm-10">
								 <textarea class="form-control" name="new-comment" id="new-comment" rows="3"></textarea>
							 </div>
						</div>
						<div class="form-group">
							<button type="button" id="btn-submit" class="btn btn-primary">提交</button>
							<button type="button" id="btn-reset" class="btn btn-primary">清空</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>