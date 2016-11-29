
$(function(){
	//alert("hello");
	init();
	//alert($.session.get("currentUser"));
});

/**
 * 初始化相关数据
 */
function init(){
	//fillUserInfo();
	getAllBlogs();
}

function fillUserInfo(){
	var user = $.session.get("currentUser");
	alert(user);
}

function getAllBlogs(){
	$.ajax({
		url:"/StudyBlog/blog/getAllBlogs",
		type:"POST",
		dataType:"json",
		success:function(data){
			/**
			 * java所认可的json数据与js所认可的不一样
			 * js的json数据外面多了一个括号
			 */
			data = eval("(" + data + ")");
			showBlogs(data.blogs);
		}
	});
}

/**
 * 展示blogs信息
 */
function showBlogs(blogs){
	var len = blogs.length;
	for(var i=0;i<len;i++){
		var tab = "<div class=blog-tab>"+
				 "<div class=blog-tab-main>"+
	                 "<p><a href='/StudyBlog/blog/blogDetail/"+blogs[i].id+"'>"+blogs[i].name+"</a></p>"+
	              "</div>";
		var tab1 =  "<div class=blog-tab-other>" +
						"<div class=blog-tab-author>" +
							"<span>作者："+blogs[i].author.nickname+"</span>" +
						"<div/>" +
					"</div></div>";
			
		$("#blogs").append(tab+tab1);
	}
}
