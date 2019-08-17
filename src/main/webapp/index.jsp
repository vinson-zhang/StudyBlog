<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<!DOCTYPE >
<html>
<head>
<%@include file="public/head.jspf"%>
<script type="text/javascript" src="${basePath}/StudyBlog/js/index.js"></script>
<link type="text/css" rel="styleshee" href="/css/index.css"></link>
<title>StudyBlog | 主页</title>
</head>
<body>
<%@include file="/public/navbar.jspf" %>
<!--

		<div id="navbar" class="navbar-collapse collapse">
			<div class="navbar-self">
				<div class="navbar-logo">
					<img src="${basePath}/StudyBlog/images/logo.png" />
				</div>
				<div class="navbar-left">
					<ul class="nav nav-pills">
						<li class="active">
							<a href="#">Home</a>
						</li>
						<li>
							<a href="#about">About</a>
						</li>
						<li>
							<a href="#contact">Contact</a>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
								Blog<span class="caret"></span>
							</a>
							<ul class="dropdown-menu" role="menu">
								<li>
									<a href="#">我的Blog</a>
								</li>
								<li>
									<a href="#">写Blog</a>
								</li>
								<li class="divider"></li>
								<li class="dropdown-header">查看Blog</li>
								<li>
									<a href="#">热门Blog </a>
								</li>
							</ul>
						</li>
					</ul>
				</div>

				<div class="navbar-right">
					<ul id="logined" class="nav navbar-nav" style="display: none;">
						<li>
							<span>欢迎：<a id="userinfo" class="btn" href="#">碧琳</a></span>
						</li>
						<li>
							<span><a class="btn" href="#">退出</a></span>
						</li>
					</ul>

					<ul id="nologined" class="nav navbar-nav">
						<li>
							<span><a class="btn" href="#">登录</a></span>
						</li>
						<li>
							<span><a class="btn" href="#">注册</a></span>
						</li>
					</ul>
				</div>
				
				<div id="login">
					  <div id="logined" style="display: none;">
					  	<span>欢迎：<a id="userinfo" class="btn" href="#">碧琳</a></span>
					  	<span><a class="btn" href="#">退出</a></span>
					  </div>
					  <div id="nologined" style="display: none;">
					  	<span><a class="btn" href="#">登录</a></span>
					  	<span><a class="btn" href="#">注册</a></span>
					  </div>
				</div>
				
			</div>
		</div>
		-->

		<div class="container">
			<!--图片轮播-->
			<div id="myCarousel" class="carousel slide">
				<!--轮播指标-->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>
				<!-- 轮播（Carousel）项目 -->
				<div class="carousel-inner">
					<div class="item active">
						<img src="/StudyBlog/images/carousel/one.jpg" alt="one">
					</div>
					<div class="item">
						<img src="/StudyBlog/images/carousel/two.jpg" alt="two">
					</div>
					<div class="item">
						<img src="/StudyBlog/images/carousel/three.jpg" alt="three">
					</div>
				</div>
				<!-- 轮播（Carousel）导航 -->
				<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
				<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
				<!-- 控制按钮 -->
				<div style="text-align:center;">
					<input type="button" class="btn start-slide" value="Start">
					<input type="button" class="btn pause-slide" value="Pause">
					<input type="button" class="btn prev-slide" value="Previous Slide">
					<input type="button" class="btn next-slide" value="Next Slide">
					<input type="button" class="btn slide-one" value="Slide 1">
					<input type="button" class="btn slide-two" value="Slide 2">
					<input type="button" class="btn slide-three" value="Slide 3">
				</div>
			</div>
		</div>
		</div>
	</body>

</html>