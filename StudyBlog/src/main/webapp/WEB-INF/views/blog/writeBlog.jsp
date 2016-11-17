<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<%@include file="/public/head.jspf"%>
<script type="text/javascript" src="${basePath}/StudyBlog/js/blog/writeBlog.js"></script>
<link type="text/css" rel="styleshee" href="/css/blog/writeBlog.css"></link>
<title>StudyBlog | 主页</title>
</head>
<body>
	<%@include file="/public/navbar.jspf"%>
	<div class="container">
			<div class="row">
				<div class="col-xs-12">

					<form class="form-horizontal" role="form" id="blog">
						<div class="form-group">
							<label for="blog-name" class="col-sm-2 control-label">博客名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="blog-name" placeholder="请输入博客名称">
							</div>
						</div>
						<div class="form-group">
						    <label for="blog-content" class="col-sm-2 control-label">博客内容</label>
						    <div class="col-sm-10">
						    	<textarea class="form-control" rows="15" id="blog-content" placeholder="请输入博客内容"></textarea>
						    </div>
						</div>
						<div class="form-group">
						    <label class="col-sm-2 control-label" for="is-visible">是否外人可见</label>
						    <div class="col-sm-10">
							    <select id="is-visible" class="form-control">
							      <option value="1">可见</option>
							      <option value="0">不可见</option>
							    </select>
						    </div>
						</div>

						<button type="button" class="btn btn-default" onclick="addBlog()">提交</button>
						<button type="button" class="btn btn-default" onclick="isReset()">重置</button>
					</form>

				</div>
			</div>

		</div>

</body>
</html>